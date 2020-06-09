package com.proyectoabp.ws.rest.service;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.proyectoabp.ws.mod.dao.DAOActividad;
import com.proyectoabp.ws.mod.dao.DAOSesiones;
import com.proyectoabp.ws.rest.vo.VOActividad;
import com.proyectoabp.ws.rest.vo.VOSesiones;

@Path("/Actividad")
public class ServiceActividad {
	@POST
    @Path("/Problema")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response sesion(VOActividad vo) {
		//System.out.println(vo.getProfesorsito());
        DAOActividad dao = new DAOActividad();        
        try {        	
				String aux =(String) dao.getProblematica(vo);
				return Response.status(Response.Status.CREATED).entity(aux).build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	@POST
    @Path("/Update")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response SaberUsuario(VOActividad vo) {
        DAOActividad dao = new DAOActividad();        
        try {
			if(dao.updatePro(vo)!=false) {
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
    @Path("/GET")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response SaberUsuario1(VOActividad vo) {
        DAOActividad dao = new DAOActividad();        
        try {
			
				String aux =(String) dao.getPro(vo);
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \""+aux+"\"}").build();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
}
