package com.proyectoabp.ws.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.proyectoabp.ws.mod.dao.DAOCurso;
import com.proyectoabp.ws.rest.vo.VOCurso;

@Path("/Estudiante")
public class ServiceCurso {
	@POST
    @Path("/AddCurso")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
	public Response AddCurso(VOCurso vo) {
		DAOCurso dao = new DAOCurso();
		try {
			if (dao.addCurso(vo)!=false) {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"hecho\"}").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
	}
}
