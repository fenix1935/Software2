package com.example.vo;

public class VOSesion {
    private String estudianteS;
    private String grupoS;
    private String Gnum;
    public VOSesion(String estudianteS, String grupoS, String Gnum) {
        super();
        this.estudianteS = estudianteS;
        this.grupoS = grupoS;
        this.Gnum=Gnum;
    }
    public VOSesion() {

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
}
