package com.proyectoabp.ws.rest.vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOProblema {
	private @JsonProperty("problem") String problem;
	private @JsonProperty("num1") String num1;
	public VOProblema(String problem, String num1) {
		super();
		this.problem = problem;
		this.num1 = num1;
	}
	
	public VOProblema() {
		
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getNum1() {
		return num1;
	}

	public void setNum1(String num1) {
		this.num1 = num1;
	}
	
}
