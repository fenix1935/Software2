package com.proyectoabp.ws.rest.vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class VORespuesta {
	
	private @JsonProperty("respuesta") String respuesta;
	private @JsonProperty("num6") String num6;
	public VORespuesta(String respuesta, String num6) {
		super();
		this.respuesta = respuesta;
		this.num6 = num6;
	}
	
	public VORespuesta() {
		
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getNum6() {
		return num6;
	}

	public void setNum6(String num6) {
		this.num6 = num6;
	}
	
	
	

}
