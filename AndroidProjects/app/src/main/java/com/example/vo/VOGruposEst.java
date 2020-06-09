package com.example.vo;

public class VOGruposEst
{
    private String codigo1;
    private String codigoGrupo;
    private String estudiante;
    private String nota;
    private String nombreG;

    public VOGruposEst(String codigo1, String codigoGrupo, String estudiante, String nota, String nombreG) {
        this.codigo1 = codigo1;
        this.codigoGrupo = codigoGrupo;
        this.estudiante = estudiante;
        this.nota = nota;
        this.nombreG = nombreG;
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

    public String getNombreG() {
        return nombreG;
    }

    public void setNombreG(String nombreG) {
        this.nombreG = nombreG;
    }
}
