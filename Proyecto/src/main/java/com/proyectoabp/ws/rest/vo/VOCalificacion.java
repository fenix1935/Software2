package com.proyectoabp.ws.rest.vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOCalificacion {
	private @JsonProperty("cali") String cali;
	private @JsonProperty("namS") String namS;
	public VOCalificacion(String cali, String namS) {
		super();
		this.cali = cali;
		this.namS = namS;
	}
	
	public VOCalificacion() {
		
	}

	public String getCali() {
		return cali;
	}

	public void setCali(String cali) {
		this.cali = cali;
	}

	public String getNamS() {
		return namS;
	}

	public void setNamS(String namS) {
		this.namS = namS;
	}
	
	
}
