package com.proyectoabp.ws.rest.vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOActividad {
	private @JsonProperty("problematica") String problematica;
	private @JsonProperty("url") String url;

	public VOActividad(String problematica, String url) {
		super();
		this.problematica = problematica;
		this.url=url;
		//this.probNum = probNum;
	}

	
	public VOActividad() {	
	}
	
	
	public String getProblematica() {
		return problematica;
	}
	public void setProblematica(String problematica) {
		this.problematica = problematica;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
	
}
