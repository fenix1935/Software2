package com.proyectoabp.ws.rest.vo;

import java.util.ArrayList;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class random {
 public static void main(String args[]) {
	 //Esto se lo manda a android Studio
	 Gson gson= new Gson();
	 ArrayList<VOGrupos> grupos= new ArrayList<VOGrupos>();
	 grupos.add(new VOGrupos("1","Cocina","Leyva"));
	 grupos.add(new VOGrupos("2","Futbol","Leyva"));
	 grupos.add(new VOGrupos("3","Soft1","Leyva"));
	 
	 String h= gson.toJson(grupos);
	 
	 System.out.println(h);
	 
	 //Esto es lo que recibe android
	 
	 ArrayList<VOGrupos> g= new ArrayList<VOGrupos>();
	 Type userListType = (new TypeToken<ArrayList<VOGrupos>>(){}).getType();
	 g= gson.fromJson(h, userListType);  
	 System.out.println(g.size());
	 System.out.println(g.get(1).getNombreCurso());
	 //System.out.println(g1.getNamesito());
 }
}
