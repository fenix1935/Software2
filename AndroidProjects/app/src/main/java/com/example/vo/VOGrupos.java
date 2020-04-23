package com.example.vo;


public class VOGrupos {
    private String codigoAcceso;
    private String nombreCurso;
    private String profesor;


    public VOGrupos(String codigoAcceso, String nombreCurso, String profesor) {
        super();
        this.codigoAcceso = codigoAcceso;
        this.nombreCurso = nombreCurso;
        this.profesor = profesor;
    }

    public VOGrupos() {
        //constructor Code
    }

    public String getCodigoAcceso() {
        return codigoAcceso;
    }


    public void setCodigoAcceso(String codigoAcceso) {
        this.codigoAcceso = codigoAcceso;
    }


    public String getNombreCurso() {
        return nombreCurso;
    }


    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }


    public String getProfesor() {
        return profesor;
    }


    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }


}
