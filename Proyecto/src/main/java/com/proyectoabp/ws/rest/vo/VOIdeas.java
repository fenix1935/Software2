package com.proyectoabp.ws.rest.vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOIdeas {
	private @JsonProperty("idea") String idea;
	private @JsonProperty("num2") String num2;
	
	
	
	public VOIdeas(String idea, String num2) {
		super();
		this.idea = idea;
		this.num2 = num2;
	}
	public VOIdeas() {
		
	}
	
	public String getIdea() {
		return idea;
	}
	public void setIdea(String idea) {
		this.idea = idea;
	}
	public String getNum2() {
		return num2;
	}
	public void setNum2(String num2) {
		this.num2 = num2;
	}
	
	
}
