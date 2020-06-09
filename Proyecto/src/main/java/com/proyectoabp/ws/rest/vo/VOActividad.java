package com.proyectoabp.ws.rest.vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOActividad {
	private @JsonProperty("problematica") String problematica;
	private @JsonProperty("probNum") String probNum;

	public VOActividad(String problematica, String probNum) {
		super();
		this.problematica = problematica;
		this.probNum = probNum;
	}

	public String getProbNum() {
		return probNum;
	}
	public void setProbNum(String probNum) {
		this.probNum = probNum;
	}
	public VOActividad() {	
	}
	public String getProblematica() {
		return problematica;
	}
	public void setProblematica(String problematica) {
		this.problematica = problematica;
	}
}
