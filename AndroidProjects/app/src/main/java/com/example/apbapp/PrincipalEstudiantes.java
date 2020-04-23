package com.example.apbapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.apbapp.MainActivity;

import java.util.HashMap;
import java.util.Map;

public class PrincipalEstudiantes extends AppCompatActivity {

    Spinner opciones;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estudiantes_principal);

        opciones = (Spinner)findViewById(R.id.spinnerCursosEstudiante);
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);
       // VerificarEst();
        //listado, ver archivo Array en carpeta values

    }

    public void botonIrAgregarCurso(View view){
        Intent intent = new Intent(PrincipalEstudiantes.this, AgregarCurso.class);
        startActivity(intent);

    }
    public void boton1(View view){
        //String text= opciones.getSelectedItem().toString();
        //Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(PrincipalEstudiantes.this, Lobby.class);
        //startActivity(intent);
        MainActivity m= new MainActivity();
        String ja= m.getVar1();
        Toast.makeText(this, ja, Toast.LENGTH_SHORT).show();
    }
/*
    private void VerificarEst(){
        String email= Usuario.getText().toString();
        String passw= Contrasena.getText().toString();
        passw = codificarContrasena(passw);
        Map<String,String> datos = new HashMap<>();
        datos.put("usuario", email);
        datos.put("password", passw);
        JSONObject jsonData = new JSONObject(datos);
        System.out.println(jsonData);
        AndroidNetworking.post("http://192.168.0.15:8080/Proyecto/restJR/Usuario/loginUsuario")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado= response.getString("Status");
                            //Toast.makeText(MainActivity.this, estado, Toast.LENGTH_SHORT).show();
                            if(estado.equals("Estudiante")) {
                                Toast.makeText(MainActivity.this, estado + " logued", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, PrincipalEstudiantes.class);
                                startActivity(intent);
                            }else if(estado.equals("Profesor")){
                                Toast.makeText(MainActivity.this, estado + " logued", Toast.LENGTH_SHORT).show();
                                Intent intent3 = new Intent(MainActivity.this, PrincipalProfesor.class);
                                startActivity(intent3);

                            } else if(estado.equals("401")){
                                Toast.makeText(MainActivity.this, "Error en usuario o contraseña", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(MainActivity.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MainActivity.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });

    }
    */

}
