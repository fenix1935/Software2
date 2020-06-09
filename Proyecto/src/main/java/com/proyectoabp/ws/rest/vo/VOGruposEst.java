package com.proyectoabp.ws.rest.vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class VOGruposEst {
	private @JsonProperty("codigo1") String codigo1;
	private @JsonProperty("codigoGrupo") String codigoGrupo;
	private @JsonProperty("estudiante") String estudiante;
	private @JsonProperty("nota") String nota;
	private @JsonProperty("acceso") String acceso;
	private @JsonProperty("nombreG") String nombreG;
	
	public VOGruposEst(String codigo1, String codigoGrupo, String estudiante, String nota, String nombreG) {
		super();
		this.codigo1 = codigo1;
		this.codigoGrupo = codigoGrupo;
		this.estudiante = estudiante;
		this.nota = nota;
		this.nombreG=nombreG;
	}
	
	public VOGruposEst() {
		
	}
	public String getCodigo1() {
		return codigo1;
	}
	public void setCodigo1(String codigo1) {
		this.codigo1 = codigo1;
	}
	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	public String getEstudiante() {
		return estudiante;
	}
	public void setEstudiante(String estudiante) {
		this.estudiante = estudiante;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public String getAcceso() {
		return acceso;
	}
	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}
	public String getNombreG() {
		return nombreG;
	}
	public void setNombreG(String nombreG) {
		this.nombreG = nombreG;
	}
}
