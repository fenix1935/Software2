package com.proyectoabp.ws.rest.service;


import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.proyectoabp.ws.mod.dao.DAOGrupo;
import com.proyectoabp.ws.mod.dao.DAOUsuario;
import com.proyectoabp.ws.rest.vo.VOGrupos;
import com.proyectoabp.ws.rest.vo.VOUsuario;

@Path("/Usuario")
public class ServiceLogin {
	@POST
    @Path("/RegistrarUsuario")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response registrarUsuario(VOUsuario vo) {
        DAOUsuario dao = new DAOUsuario();        
        try {
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
    @Produces({MediaType.APPLICATION_JSON})
    public Response loginUsuario(VOUsuario vo) {
        DAOUsuario dao = new DAOUsuario();        
        try {        	
			if(dao.isUser(vo) != false) {
				VOUsuario aux = dao.getUser(vo);
				String tipo = aux.getTipo();				
				String nombre= aux.getUsuario();
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \""+tipo+"\",\"Nombre\": \""+nombre+"\"}").build();
			}else {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"401\"}").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	
}
