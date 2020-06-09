package com.proyectoabp.ws.rest.vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOSesiones {
	
	private @JsonProperty("codeito") String codeito;
	private @JsonProperty("estudianteS") String estudianteS;
	private @JsonProperty("grupoS") String grupoS;
	private @JsonProperty("Gnum") String Gnum;
	
	
	
	public VOSesiones(String estudianteS, String grupoS, String Gnum) {
		super();
		this.estudianteS = estudianteS;
		this.grupoS = grupoS;
		this.Gnum=Gnum;
	}
	public VOSesiones() {
		
	}
	public String getEstudianteS() {
		return estudianteS;
	}
	public void setEstudianteS(String estudianteS) {
		this.estudianteS = estudianteS;
	}
	public String getGrupoS() {
		return grupoS;
	}
	public void setGrupoS(String grupoS) {
		this.grupoS = grupoS;
	}
	public String getGnum() {
		return Gnum;
	}
	public void setGnum(String gnum) {
		Gnum = gnum;
	}
	public String getCodeito() {
		return codeito;
	}
	public void setCodeito(String codeito) {
		this.codeito = codeito;
	}
	
}
