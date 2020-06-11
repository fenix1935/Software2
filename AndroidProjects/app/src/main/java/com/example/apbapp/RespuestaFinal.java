package com.example.apbapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.vo.VOIdeas;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RespuestaFinal extends AppCompatActivity {
    ListView opciones;
    private ArrayAdapter<String> adapter;
    public static ArrayList<VOIdeas> gPi;
    TextView hipot;
    ListView ideas;
    EditText txt_agregar_hipotesis;

    public void IdeaGet(){
        MainActivity m= new MainActivity();
        //String profe= PrincipalEstudiantes.g.get(PrincipalEstudiantes.posicion).getCodigoGrupo();
        Map<String,String> datos = new HashMap<>();
        datos.put("num2", AsignacionGrupo.numG);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Activity/IdeaGet2").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            adapter.clear();
                            Gson gson= new Gson();
                            gPi= new ArrayList<VOIdeas>();
                            Type userListType = (new TypeToken<ArrayList<VOIdeas>>(){}).getType();
                            gPi= gson.fromJson(response, userListType);
                            //System.out.println(g.size());
                            //System.out.println(g.get(1).getNombreCurso());
                            for(int i=0; i<gPi.size();i++){
                                String a= gPi.get(i).getIdea();
                                adapter.add(gPi.get(i).getIdea());
                            }
                            adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Toast.makeText(RespuestaFinal.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(RespuestaFinal.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuesta);
        hipot= findViewById(R.id.txt_hipotesis);
        txt_agregar_hipotesis=findViewById(R.id.txt_agregar_hipotesis);
        opciones=(ListView)findViewById(R.id.IdeasHipotesis);
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        opciones.setAdapter(adapter);
        IdeaGet();
    }
    public void Subir(){
        //String texti= texto.getText().toString();
        Map<String,String> datos = new HashMap<>();
        datos.put("respuesta", txt_agregar_hipotesis.getText().toString());
        datos.put("num6", AsignacionGrupo.numG);
        JSONObject jsonData = new JSONObject(datos);
        System.out.println(jsonData);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Activity/RespuestaSubir")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado=response.getString("Status");
                            if(estado.equals("hecho")){
                                Toast.makeText(RespuestaFinal.this, "subido", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(RespuestaFinal.this, "no", Toast.LENGTH_SHORT).show();
                            }



                        } catch (JSONException e) {
                            Toast.makeText(RespuestaFinal.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(RespuestaFinal.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void hipotesis(View view){
        Intent intent = new Intent(RespuestaFinal.this, PresentadorIdeas.class);
        startActivity(intent);

        Subir();
        hipot.setText("");
        hipot.setText(txt_agregar_hipotesis.getText().toString());
        txt_agregar_hipotesis.setText("");

    }

}