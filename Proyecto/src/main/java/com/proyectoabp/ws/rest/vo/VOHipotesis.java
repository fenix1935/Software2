package com.proyectoabp.ws.rest.vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOHipotesis {
	private @JsonProperty("hipo") String hipo;
	private @JsonProperty("num3") String num3;
	public VOHipotesis(String hipo, String num3) {
		super();
		this.hipo = hipo;
		this.num3 = num3;
	}
	
	public VOHipotesis(){
		
	}

	public String getHipo() {
		return hipo;
	}

	public void setHipo(String hipo) {
		this.hipo = hipo;
	}

	public String getNum3() {
		return num3;
	}

	public void setNum3(String num3) {
		this.num3 = num3;
	}
	
	
}
