package com.example.apbapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.vo.VOSesion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lobby extends AppCompatActivity {
TextView co;
TextView G1;
    TextView G2;
    TextView G3;
    TextView G4;
    public static ArrayList<VOSesion> g2;
    private int prueba1=0;
    private int prueba2=0;
    private int prueba3=0;
    private int prueba4=0;
    private final int TIEMPO = 2000; // 1 Second
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lobby);

        co= findViewById(R.id.textoGrupoCodigo);
        co.setText(PrincipalProfesor.cursoE);
        G1=findViewById(R.id.textoGrupo1);
        G2=findViewById(R.id.textoGrupo2);
        G3=findViewById(R.id.textoGrupo3);
        G4=findViewById(R.id.textoGrupo4);
        grupoGet1();
        G1.setText("Grupo 1: "+prueba1+"/4");
        G2.setText("Grupo 2: "+prueba2+"/4");
        G3.setText("Grupo 3: "+prueba3+"/4");
        G4.setText("Grupo 4: "+prueba4+"/4");
        //Toast.makeText(this, "La suma es:"+g2.get(0).getGnum(), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "tamaño "+g2.get(0).getGnum(), Toast.LENGTH_SHORT).show();
    ejecutarTarea2();
    }
    public void ejecutarTarea2() {
        handler.postDelayed(new Runnable() {
            public void run() {
               /* if(estado1.equals("1")){
                    Intent intent = new Intent(AsignacionGrupo.this, AsignacionTema.class);
                    startActivity(intent);
                }*/
                //textoNombreGrupoAsignado2.setText(PrincipalEstudiantes.cursoCode+" "+state);
                // función a ejecutar
                //Toast.makeText(PrincipalProfesor.this, "asd", Toast.LENGTH_SHORT).show(); // función para refrescar la ubicación del conductor, creada en otra línea de código
               // update(PrincipalEstudiantes.posicion);
                grupoGet1();
                prueba1=0;
                prueba2=0;
                prueba3=0;
                prueba4=0;
                for(int i=0; i<g2.size();i++){
                    int cont= Integer.parseInt(g2.get(i).getGnum());
                    switch (cont){
                        case 1:
                            prueba1++;
                            G1.setText("Grupo 1: "+prueba1+"/4");
                            break;
                        case 2:
                            prueba2++;
                            G2.setText("Grupo 2: "+prueba2+"/4");
                            break;
                        case 3:
                            prueba3++;
                            G3.setText("Grupo 3: "+prueba3+"/4");
                            break;
                        case 4:
                            prueba4++;
                            G4.setText("Grupo 4: "+prueba4+"/4");
                            break;

                    }
                }
                //pasar1();
                handler.postDelayed(this, TIEMPO);
            }

        }, TIEMPO);
    }
    public void inicio(){
        String email = MainActivity.var1;
        String grup = PrincipalProfesor.g.get(PrincipalProfesor.posicion).getCodigo();
        //pass = codificarContrasena(pass);
        Map<String, String> datos = new HashMap<>();
        datos.put("estudianteS", email);
        datos.put("grupoS", grup);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Sesion/Inicio").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado = response.getString("Status");
                            //Toast.makeText(Registro.this, estado, Toast.LENGTH_SHORT).show();
                            if (estado.compareTo("Hecho") == 0) {
                                Toast.makeText(Lobby.this, "Iniciado", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(Lobby.this, "No se pudo iniciar", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(Lobby.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(Lobby.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void verify(){
        String email = MainActivity.var1;
        String grup = PrincipalProfesor.g.get(PrincipalProfesor.posicion).getCodigo();
        //pass = codificarContrasena(pass);
        Map<String, String> datos = new HashMap<>();
        //datos.put("estudianteS", email);
        datos.put("grupoS", grup);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Sesion/SesionIs").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado = response.getString("Status");
                            //Toast.makeText(Registro.this, estado, Toast.LENGTH_SHORT).show();
                            if (estado.compareTo("hecho") == 0) {
                                Intent intent3 = new Intent(Lobby.this, AsignacionTema.class);
                                startActivity(intent3);

                            } else {
                                Toast.makeText(Lobby.this, "No se ha iniciado esta clase", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(Lobby.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(Lobby.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void grupoGet1(){
        MainActivity m= new MainActivity();
        String profe= PrincipalProfesor.g.get(PrincipalProfesor.posicion).getCodigo();
        Map<String,String> datos = new HashMap<>();
        datos.put("grupoS", profe);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Sesion/SesionDatos").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //adapter.clear();
                            Gson gson= new Gson();
                            g2= new ArrayList<VOSesion>();
                            Type userListType = (new TypeToken<ArrayList<VOSesion>>(){}).getType();
                            g2= gson.fromJson(response, userListType);
                            //System.out.println(g.size());
                            //System.out.println(g.get(1).getNombreCurso());


                        } catch (Exception e) {
                            Toast.makeText(Lobby.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(Lobby.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void botoninicio(View view){
        inicio();
    }
    public void botonempezar(View view){
verify();


    }
}
