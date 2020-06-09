package com.proyectoabp.ws.rest.service;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jettison.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import com.google.protobuf.Type;
import com.proyectoabp.ws.mod.dao.DAOGrupo;
import com.proyectoabp.ws.mod.dao.DAOUsuario;
import com.proyectoabp.ws.rest.vo.VOGrupos;
import com.proyectoabp.ws.rest.vo.VOGruposEst;
import com.proyectoabp.ws.rest.vo.VOUsuario;



@Path("/Grupos")
public class ServiceGrupos {
	@POST
    @Path("/RegistrarGrupo")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response registrarGrupo(VOGrupos vo) {
        DAOGrupo dao = new DAOGrupo();        
        try {	
			if(dao.addGroup(vo)!=false) {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"hecho\"}").build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	
	@POST
    @Path("/CursosProfe")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response cursosProfe(VOGrupos vo) {
		//System.out.println(vo.getProfesorsito());
        DAOGrupo dao = new DAOGrupo();        
        try {        	
			if(dao.isGroupProfe(vo.getProfesor()) != false) {
				String aux = (String) dao.getGroupProfe(vo.getProfesor());
				return Response.status(Response.Status.CREATED).entity(aux).build();
			}else {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"401\"}").build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
    
	@POST
    @Path("/AgregarCurso")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response cursosAgregar(VOGruposEst vo) {
		//System.out.println(vo.getProfesorsito());
        DAOGrupo dao = new DAOGrupo();        
        try {        	
				VOGrupos aux =  dao.getGroup(vo.getAcceso());
				//String acceso= vo.getCodigoAcceso();
				//String name= vo.get
				if(dao.addGroupEstudiante(aux.getCodigo(), vo.getEstudiante())!=false) {
				return Response.status(Response.Status.CREATED).entity("{\"Status\": \"hecho\"}").build();
				}

		} catch (Exception e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	@POST
    @Path("/CursosEstudiante")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response cursosEstudiante(VOGruposEst vo) {
		//System.out.println(vo.getProfesorsito());
        DAOGrupo dao = new DAOGrupo();        
        try {        	
				String aux = (String) dao.getGroupStudent(vo.getEstudiante());
				return Response.status(Response.Status.CREATED).entity(aux).build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
    
}
