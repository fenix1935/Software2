package com.example.vo;

public class VOActividad {
    private  String problematica;
    private String url;

    public VOActividad(String problematica, String url) {
        super();
        this.problematica = problematica;
        this.url = url;
    }
    public VOActividad() {

    }
    public String getProblematica() {
        return problematica;
    }

    public void setProblematica(String problematica) {
        this.problematica = problematica;
    }

    public String getLink() {
        return url;
    }
    public void setLink(String url) {
        this.url = url;
    }



}
