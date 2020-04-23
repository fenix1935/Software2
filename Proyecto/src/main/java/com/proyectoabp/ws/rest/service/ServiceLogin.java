package com.proyectoabp.ws.rest.service;

<<<<<<< HEAD

=======
import java.math.BigInteger;
import java.security.MessageDigest;
>>>>>>> origin/MarcoCastellanos
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

<<<<<<< HEAD
import com.proyectoabp.ws.mod.dao.DAOGrupo;
=======
import org.json.JSONObject;

>>>>>>> origin/MarcoCastellanos
import com.proyectoabp.ws.mod.dao.DAOUsuario;
import com.proyectoabp.ws.rest.vo.VOGrupos;
import com.proyectoabp.ws.rest.vo.VOUsuario;

@Path("/Usuario")
public class ServiceLogin {
	
	private static final String key = "d79843f3186d1d0b5782cf8b01f8a63ae620341e46d4bb4a4158badee5d83645";
	
	@POST
    @Path("/RegistrarUsuario")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response registrarUsuario(VOUsuario vo) {
        DAOUsuario dao = new DAOUsuario();        
        try {
        	vo.setPassword(getSHA256(vo.getPassword()));        	
			if(dao.addUser(vo)!=false) {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"hecho\"}").build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	
	@POST
    @Path("/loginUsuario")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public Response loginUsuario(VOUsuario vo) {
        DAOUsuario dao = new DAOUsuario();      
        String mytoken = "";
        try {        	
<<<<<<< HEAD
			if(dao.isUser(vo) != false) {
				VOUsuario aux = dao.getUser(vo);
				String tipo = aux.getTipo();				
				String nombre= aux.getUsuario();
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \""+tipo+"\",\"Nombre\": \""+nombre+"\"}").build();
=======
        	vo.setPassword(getSHA256(vo.getPassword()));
        	VOUsuario temp = dao.getUser(vo);
			if(temp != null) {
				String h = "{\"Alg\":SHA256,\"type\":MWT}";
				JSONObject header = new JSONObject(h);	
				
				Date dt = new Date();
				int aux = (int) (dt.getTime() + 900000);
				String p = "{\"usuario\":"+temp.getUsuario()+",\"tipo\":"+temp.getTipo()+
						",\"Expira\":"+aux+"}";
				JSONObject paylod = new JSONObject(p);
				
				String head = header.toString();
				String pay = paylod.toString();
				head = Base64.getEncoder().encodeToString(head.getBytes());
				pay = Base64.getEncoder().encodeToString(pay.getBytes());
				
				String cadena = head + pay + key;
				String firma = getSHA256(cadena);
				firma = Base64.getEncoder().encodeToString(firma.getBytes());
				// se arma el token
				mytoken = head + "." + pay + "." + firma;
				
								
				return Response.status(Response.Status.CREATED).entity(mytoken).build();
>>>>>>> origin/MarcoCastellanos
			}else {
				return Response.status(Response.Status.CREATED).entity("Error").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("Error").build();
    }
	
	public static String getSHA256(String input) {

		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toReturn;
	}
	
}
