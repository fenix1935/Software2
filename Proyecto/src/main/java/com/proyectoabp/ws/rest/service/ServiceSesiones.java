package com.proyectoabp.ws.rest.service;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.proyectoabp.ws.mod.dao.DAOGrupo;
import com.proyectoabp.ws.mod.dao.DAOSesiones;
import com.proyectoabp.ws.mod.dao.DAOUsuario;
import com.proyectoabp.ws.rest.vo.VOGruposEst;
import com.proyectoabp.ws.rest.vo.VOSesiones;
import com.proyectoabp.ws.rest.vo.VOUsuario;

@Path("/Sesion")
public class ServiceSesiones {
	@POST
    @Path("/RegistrarSesion")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response registrarUsuario(VOSesiones vo) {
        DAOSesiones dao = new DAOSesiones();        
        try {
			if(dao.isSesion(vo)!=false) {
				if(dao.addSesion(vo)!=false) {
					return Response.status(Response.Status.CREATED).entity("{\"Status\": \"hecho\"}").build();
				}
				else {
					return Response.status(Response.Status.CREATED).entity("{\"Status\": \"401\"}").build();
					
				}
			}else {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	
	@POST
    @Path("/SesionIs")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response SaberUsuario(VOSesiones vo) {
        DAOSesiones dao = new DAOSesiones();        
        try {
			if(dao.isSesion(vo)!=false) {
					return Response.status(Response.Status.CREATED).entity("{\"Status\": \"hecho\"}").build();
				
			}else {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	
	@POST
    @Path("/SesionDatos")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response sesion(VOSesiones vo) {
		//System.out.println(vo.getProfesorsito());
        DAOSesiones dao = new DAOSesiones();        
        try {        	
				String aux = (String) dao.getSesiones(vo);
				return Response.status(Response.Status.CREATED).entity(aux).build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	@POST
    @Path("/SesionDelete")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response sesionDelete(VOSesiones vo) {
		//System.out.println(vo.getProfesorsito());
        DAOSesiones dao = new DAOSesiones();        
        try {  
				if( dao.deleteSesion(vo)!= false) {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Hecho\"}").build();
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	@POST
    @Path("/Estado")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response Estado(VOSesiones vo) {
        DAOSesiones dao = new DAOSesiones();        
        try {
			if(dao.isSesion(vo)!=false) {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"hecho\"}").build();

			}else {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	@POST
    @Path("/Inicio")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response sesionInicio(VOSesiones vo) {
		//System.out.println(vo.getProfesorsito());
        DAOSesiones dao = new DAOSesiones();        
        try {  
				if( dao.addSesionInicial(vo)!= false) {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Hecho\"}").build();
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	
	
	@POST
    @Path("/Empezar")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response empiezo(VOSesiones vo) {
		//System.out.println(vo.getProfesorsito());
        DAOSesiones dao = new DAOSesiones();        
        try {  
				if( dao.pasarSesion(vo)!= false) {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Hecho\"}").build();
				}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
		@POST
	    @Path("/SesionNumero")
	    @Consumes({MediaType.APPLICATION_JSON})
	    @Produces({MediaType.APPLICATION_JSON})
	    public Response s(VOSesiones vo) {
	        DAOSesiones dao = new DAOSesiones();        
	        
	        try {
	        	String h= (String) dao.isSesion1(vo);
	        	return Response.status(Response.Status.CREATED).entity("{\"Status\": \""+h+"\"}").build();
	        
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
	    }
		@POST
	    @Path("/SesionNumero1")
	    @Consumes({MediaType.APPLICATION_JSON})
	    @Produces({MediaType.APPLICATION_JSON})
	    public Response s1(VOSesiones vo) {
	        DAOSesiones dao = new DAOSesiones();        
	        
	        try {
	        	if(dao.isSesion2(vo)!=false) {
	        	return Response.status(Response.Status.CREATED).entity("{\"Status\": \"hecho\"}").build();
	        	}
	        
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
	    }

		@POST
	    @Path("/Conseguir")
	    @Consumes({MediaType.APPLICATION_JSON})
	    @Produces({MediaType.APPLICATION_JSON})
	    public Response si(VOSesiones vo) {
	        DAOSesiones dao = new DAOSesiones();        
	        
	        try {
	        	String h= (String) dao.conseguir(vo);
	        	return Response.status(Response.Status.CREATED).entity("{\"Status\": \""+h+"\"}").build();
	        
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
	    }
		
}


