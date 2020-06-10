package com.proyectoabp.ws.rest.service;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.proyectoabp.ws.mod.dao.DAOActividad;
import com.proyectoabp.ws.mod.dao.DAOActivity;
import com.proyectoabp.ws.rest.vo.VOActividad;
import com.proyectoabp.ws.rest.vo.VOHipotesis;
import com.proyectoabp.ws.rest.vo.VOIdeas;
import com.proyectoabp.ws.rest.vo.VOPalabras;
import com.proyectoabp.ws.rest.vo.VOProblema;
import com.proyectoabp.ws.rest.vo.VORespuesta;

@Path("/Activity")
public class ServicesActivity {
	
	//PROBLEMA
	@POST
    @Path("/ProblemaSubir")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response a1(VOProblema vo) {
        DAOActivity dao = new DAOActivity();        
        try {
			if(dao.SubirProblema(vo)!=false) {
					return Response.status(Response.Status.CREATED).entity("{\"Status\": \"hecho\"}").build();
				
			}else {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	// IDEAS
	
	@POST
    @Path("/IdeaSubir")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response a2(VOIdeas vo) {
        DAOActivity dao = new DAOActivity();        
        try {
			if(dao.SubirIdea(vo)!=false) {
					return Response.status(Response.Status.CREATED).entity("{\"Status\": \"hecho\"}").build();
				
			}else {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	// HIPOTESIS
	@POST
    @Path("/HipotesisSubir")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response a3(VOHipotesis vo) {
        DAOActivity dao = new DAOActivity();        
        try {
			if(dao.SubirHipo(vo)!=false) {
					return Response.status(Response.Status.CREATED).entity("{\"Status\": \"hecho\"}").build();
				
			}else {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
	}
	
	
	// PALABRAS CLAVE
	@POST
    @Path("/PalabrasSubir")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response a4(VOPalabras vo) {
        DAOActivity dao = new DAOActivity();        
        try {
			if(dao.SubirPalabras(vo)!=false) {
					return Response.status(Response.Status.CREATED).entity("{\"Status\": \"hecho\"}").build();
				
			}else {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
	}
	//IDEAS 2
	
	@POST
    @Path("/IdeaSubir2")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response a5(VOIdeas vo) {
        DAOActivity dao = new DAOActivity();        
        try {
			if(dao.SubirIdea2(vo)!=false) {
					return Response.status(Response.Status.CREATED).entity("{\"Status\": \"hecho\"}").build();
				
			}else {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	//RESPUESTA
	@POST
    @Path("/RespuestaSubir")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response a5(VORespuesta vo) {
        DAOActivity dao = new DAOActivity();        
        try {
			if(dao.Respuesta(vo)!=false) {
					return Response.status(Response.Status.CREATED).entity("{\"Status\": \"hecho\"}").build();
				
			}else {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
}

