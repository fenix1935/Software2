package com.example.apbapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.vo.VOGrupos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PrincipalProfesor extends AppCompatActivity {

    Spinner opciones;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docentes_principal);

        opciones = (Spinner)findViewById(R.id.spinnerCursosProfesor);
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);
        CursosProfe();
    }
    public void botonCrear(View view) {
        Intent intent = new Intent(PrincipalProfesor.this, CrearCurso.class);
        startActivity(intent);
    }
    private void CursosProfe(){
        MainActivity m= new MainActivity();
        String profe=m.getVar1();
        Map<String,String> datos = new HashMap<>();
        datos.put("profesor", profe);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post("http://192.168.0.15:8080/Proyecto/restJR/Grupos/CursosProfe").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Gson gson= new Gson();

                            ArrayList<VOGrupos> g= new ArrayList<VOGrupos>();
                            Type userListType = (new TypeToken<ArrayList<VOGrupos>>(){}).getType();
                            g= gson.fromJson(response, userListType);
                            System.out.println(g.size());
                            System.out.println(g.get(1).getNombreCurso());
                            for(int i=0; i<g.size();i++){
                                adapter.add(g.get(i).getNombreCurso());
                            }
                            adapter.notifyDataSetChanged();


                        } catch (Exception e) {
                            Toast.makeText(PrincipalProfesor.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(PrincipalProfesor.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });

    }


}
