package com.example.apbapp;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.vo.VOGruposEst;
import com.example.vo.VOHipotesis;
import com.example.vo.VOProblema;
import com.example.vo.VORespuesta;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VerRespuestas extends AppCompatActivity {
    TextView a;
    TextView b;
    TextView c;
    TextView d;
    public static ArrayList<VOProblema> g;
    public static ArrayList<VOHipotesis> g1;
    public static ArrayList<VORespuesta> g2;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesor_ver_respuestas);
        a=findViewById(R.id.aa);
        b=findViewById(R.id.contenido_g2);
        c=findViewById(R.id.contenido_g3);
        d=findViewById(R.id.contenido_g4);
        CursosEstudiante();
        switch (AdminGrupo.verso){
            case "1": CursosEstudiante();
            case "2":CursosEstudiante1();
            case "3":CursosEstudiante2();
        }
    }
    private void CursosEstudiante(){
        MainActivity m= new MainActivity();
        String estudiante=m.getVar1();
        Map<String,String> datos = new HashMap<>();
        datos.put("num1", "1");
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Activity/ProblemaGetAll").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //adapter.clear();
                            Gson gson= new Gson();
                            g= new ArrayList<VOProblema>();
                            Type userListType = (new TypeToken<ArrayList<VOProblema>>(){}).getType();
                            g= gson.fromJson(response, userListType);
                            //System.out.println(g.size());
                            //System.out.println(g.get(1).getNombreCurso());
                            for(int i=0; i<g.size();i++){
                                //adapter.add(g.get(i).getNombreG());
                                switch(i){
                                    case 0: a.setText("Grupo "+g.get(i).getNum1()+":"+g.get(i).getProblem());  break;
                                    case 1: b.setText("Grupo "+g.get(i).getNum1()+":"+g.get(i).getProblem());  break;
                                    case 2: c.setText("Grupo "+g.get(i).getNum1()+":"+g.get(i).getProblem());  break;
                                    case 3: d.setText("Grupo "+g.get(i).getNum1()+":"+g.get(i).getProblem());break;
                                    default: break;
                                }
                            }
                            //adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Toast.makeText(VerRespuestas.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(VerRespuestas.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void CursosEstudiante1(){
        MainActivity m= new MainActivity();
        String estudiante=m.getVar1();
        Map<String,String> datos = new HashMap<>();
        datos.put("num3", "1");
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Activity/HipotesisGetAll").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //adapter.clear();
                            Gson gson= new Gson();
                            g1= new ArrayList<VOHipotesis>();
                            Type userListType = (new TypeToken<ArrayList<VOHipotesis>>(){}).getType();
                            g1= gson.fromJson(response, userListType);
                            //System.out.println(g.size());
                            //System.out.println(g.get(1).getNombreCurso());
                            for(int i=0; i<g.size();i++){
                                //adapter.add(g.get(i).getNombreG());
                                switch(i){
                                    case 0: a.setText("Grupo "+g1.get(i).getNum3()+":"+g1.get(i).getHipo());  break;
                                    case 1: b.setText("Grupo "+g1.get(i).getNum3()+":"+g1.get(i).getHipo());  break;
                                    case 2: c.setText("Grupo "+g1.get(i).getNum3()+":"+g1.get(i).getHipo());  break;
                                    case 3: d.setText("Grupo "+g1.get(i).getNum3()+":"+g1.get(i).getHipo());  break;
                                    default: break;
                                }
                            }
                            //adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Toast.makeText(VerRespuestas.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(VerRespuestas.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void CursosEstudiante2(){
        MainActivity m= new MainActivity();
        String estudiante=m.getVar1();
        Map<String,String> datos = new HashMap<>();
        datos.put("num6", "1");
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Activity/RespuestaGetAll").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //adapter.clear();
                            Gson gson= new Gson();
                            g2= new ArrayList<VORespuesta>();
                            Type userListType = (new TypeToken<ArrayList<VORespuesta>>(){}).getType();
                            g2= gson.fromJson(response, userListType);
                            //System.out.println(g.size());
                            //System.out.println(g.get(1).getNombreCurso());
                            for(int i=0; i<g.size();i++){
                                //adapter.add(g.get(i).getNombreG());
                                switch(i){
                                    case 0: a.setText("Grupo "+g2.get(i).getNum6()+":"+g2.get(i).getRespuesta());  break;
                                    case 1: b.setText("Grupo "+g2.get(i).getNum6()+":"+g2.get(i).getRespuesta());  break;
                                    case 2: c.setText("Grupo "+g2.get(i).getNum6()+":"+g2.get(i).getRespuesta());  break;
                                    case 3: d.setText("Grupo "+g2.get(i).getNum6()+":"+g2.get(i).getRespuesta());break;
                                    default: break;
                                }
                            }
                            //adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Toast.makeText(VerRespuestas.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(VerRespuestas.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
