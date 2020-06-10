package com.proyectoabp.ws.rest.vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOPalabras {
	
	private @JsonProperty("palabras") String palabras;
	private @JsonProperty("num4") String num4;
	
	
	public VOPalabras(String palabras, String num4) {
		super();
		this.palabras = palabras;
		this.num4 = num4;
	}
	
	public VOPalabras() {
		
	}


	public String getPalabras() {
		return palabras;
	}

	public void setPalabras(String palabras) {
		this.palabras = palabras;
	}

	public String getNum4() {
		return num4;
	}

	public void setNum4(String num4) {
		this.num4 = num4;
	}
	

}
