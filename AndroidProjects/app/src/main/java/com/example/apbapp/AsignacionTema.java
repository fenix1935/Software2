package com.example.apbapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.vo.VOActividad;
import com.example.vo.VOSesion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AsignacionTema extends AppCompatActivity {
    LinearLayout tablita;
    ListView opciones;
    private ArrayAdapter<String> adapter;
    public static ArrayList<VOActividad> gP;
    public static String poss;
    public static int posss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topico_profesor);

        opciones=(ListView)findViewById(R.id.table111);
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        opciones.setAdapter(adapter);

        grupoGet1();
        opciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int posss=position;
                //String pos2= String.valueOf(pos);

                pasarGeneral();
                elegir();

               // Intent intent = new Intent(AsignacionTema.this, AsignacionGrupo.class);
                //startActivity(intent);
                //int saber= position;

                 //Toast.makeText(AsignacionTema.this, "POS "+position, Toast.LENGTH_SHORT).show();
            }
        });


    }





    public void grupoGet1(){
        MainActivity m= new MainActivity();
        //String profe= PrincipalEstudiantes.g.get(PrincipalEstudiantes.posicion).getCodigoGrupo();
        Map<String,String> datos = new HashMap<>();
        datos.put("problematica", "1");
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Actividad/Problema").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                           // adapter.clear();
                            Gson gson= new Gson();
                            gP= new ArrayList<VOActividad>();
                            Type userListType = (new TypeToken<ArrayList<VOActividad>>(){}).getType();
                            gP= gson.fromJson(response, userListType);
                            //System.out.println(g.size());
                            //System.out.println(g.get(1).getNombreCurso());
                            for(int i=0; i<gP.size();i++){
                                String a= gP.get(i).getProblematica();
                                adapter.add(gP.get(i).getProblematica());
                            }
                            adapter.notifyDataSetChanged();


                        } catch (Exception e) {
                            Toast.makeText(AsignacionTema.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(AsignacionTema.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void pasarGeneral(){
        String email = MainActivity.var1;
        String grup = PrincipalProfesor.g.get(PrincipalProfesor.posicion).getCodigo();
        //pass = codificarContrasena(pass);
        Map<String, String> datos = new HashMap<>();
        //datos.put("estudianteS", email);
        datos.put("gnum", "11");
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Sesion/Empezar").
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
                                //Toast.makeText(AsignacionTema.this, "Iniciado", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AsignacionTema.this, Admin.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(AsignacionTema.this, "No se pudo iniciar", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(AsignacionTema.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(AsignacionTema.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void elegir(){
        String email = MainActivity.var1;
        String grup = PrincipalProfesor.g.get(PrincipalProfesor.posicion).getCodigo();
        //pass = codificarContrasena(pass);
        Map<String, String> datos = new HashMap<>();
        //datos.put("estudianteS", email);
       datos.put("problematica", gP.get(posss).getProblematica());
        datos.put("url", gP.get(posss).getLink());
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Actividad/ProblemaElegido").
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
                                Toast.makeText(AsignacionTema.this, "Iniciando Clase", Toast.LENGTH_SHORT).show();
                               // Intent intent = new Intent(AsignacionTema.this, Admin.class);
                                //startActivity(intent);
                            } else {
                                //Toast.makeText(AsignacionTema.this, "No se pudo iniciar", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(AsignacionTema.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(AsignacionTema.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    public void iniciar(View view){
       // actualizar();
        pasarGeneral();
        elegir();
/*
        poss=opciones.getSelectedItem().toString();

       int jff=opciones.getSelectedItemPosition();


        Toast.makeText(this, "num: "+jff, Toast.LENGTH_SHORT).show();
        /*
 */
        Intent intent = new Intent(AsignacionTema.this, Admin.class);
        startActivity(intent);
    }
    public void agregar(View view){
        Intent intent = new Intent(AsignacionTema.this, CrearTema.class);
        startActivity(intent);
    }
}
