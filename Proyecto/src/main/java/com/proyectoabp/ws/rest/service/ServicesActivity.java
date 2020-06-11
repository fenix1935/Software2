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
import com.proyectoabp.ws.mod.dao.DAOSesiones;
import com.proyectoabp.ws.rest.vo.VOActividad;
import com.proyectoabp.ws.rest.vo.VOCalificacion;
import com.proyectoabp.ws.rest.vo.VOHipotesis;
import com.proyectoabp.ws.rest.vo.VOIdeas;
import com.proyectoabp.ws.rest.vo.VOPalabras;
import com.proyectoabp.ws.rest.vo.VOProblema;
import com.proyectoabp.ws.rest.vo.VORespuesta;
import com.proyectoabp.ws.rest.vo.VOSesiones;

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
	@POST
    @Path("/ProblemaGet")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response sesion(VOProblema vo) {
		//System.out.println(vo.getProfesorsito());
        DAOActivity dao = new DAOActivity();        
        try {        	
				String aux = (String) dao.GetProblema(vo);
				return Response.status(Response.Status.CREATED).entity(aux).build();
			
		} catch (Exception e) {
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
	@POST
    @Path("/IdeaGet")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response sesion1(VOIdeas vo) {
		//System.out.println(vo.getProfesorsito());
        DAOActivity dao = new DAOActivity();        
        try {        	
				String aux = (String) dao.GetIdea(vo);
				return Response.status(Response.Status.CREATED).entity(aux).build();
			
		} catch (Exception e) {
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
	@POST
    @Path("/HipotesisGet")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response sesion2(VOHipotesis vo) {
		//System.out.println(vo.getProfesorsito());
        DAOActivity dao = new DAOActivity();        
        try {        	
				String aux = (String) dao.GetHipotesis(vo);
				return Response.status(Response.Status.CREATED).entity(aux).build();
			
		} catch (Exception e) {
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
	@POST
    @Path("/PalabrasGet")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response sesion3(VOPalabras vo) {
		//System.out.println(vo.getProfesorsito());
        DAOActivity dao = new DAOActivity();        
        try {        	
				String aux = (String) dao.GetPalabras(vo);
				return Response.status(Response.Status.CREATED).entity(aux).build();
			
		} catch (Exception e) {
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
	@POST
    @Path("/IdeaGet2")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response sesion5(VOIdeas vo) {
		//System.out.println(vo.getProfesorsito());
        DAOActivity dao = new DAOActivity();        
        try {        	
				String aux = (String) dao.GetIdea2(vo);
				return Response.status(Response.Status.CREATED).entity(aux).build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	//RESPUESTA
	@POST
    @Path("/RespuestaSubir")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response a6(VORespuesta vo) {
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
	@POST
    @Path("/RespuestaGet")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response sesion6(VORespuesta vo) {
		//System.out.println(vo.getProfesorsito());
        DAOActivity dao = new DAOActivity();        
        try {        	
				String aux = (String) dao.GetRespuesta(vo);
				return Response.status(Response.Status.CREATED).entity(aux).build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	//CALIFICACION
	@POST
    @Path("/CalificacionSubir")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response a7(VOCalificacion vo) {
        DAOActivity dao = new DAOActivity();        
        try {
			if(dao.cali(vo)!=false) {
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
    @Path("/CalificacionGet")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response sesion7(VOCalificacion vo) {
		//System.out.println(vo.getProfesorsito());
        DAOActivity dao = new DAOActivity();        
        try {        	
				String aux = (String) dao.GetCali(vo);
				return Response.status(Response.Status.CREATED).entity(aux).build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
	
	// CALIFICACION FINAL
	@POST
    @Path("/CalificacionSubir2")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response a8(VOCalificacion vo) {
        DAOActivity dao = new DAOActivity();        
        try {
			if(dao.cali2(vo)!=false) {
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
    @Path("/CalificacionGet2")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response sesion8(VOCalificacion vo) {
		//System.out.println(vo.getProfesorsito());
        DAOActivity dao = new DAOActivity();        
        try {        	
				String aux = (String) dao.GetCali2(vo);
				return Response.status(Response.Status.CREATED).entity(aux).build();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return Response.status(Response.Status.CREATED).entity("{\"Status\": \"Error\"}").build();
    }
}

