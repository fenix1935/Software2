package com.example.apbapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Admin extends AppCompatActivity {

public static String selector="Problematica";
public static String num="11";
TextView cambio;
Button v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_profesor);
        cambio=findViewById(R.id.textView23);
        v=findViewById(R.id.AdminSiguiente);
    }
    public void siguiente(View view){
        String ver= cambio.getText().toString();
        switch(ver){
            case "Problematica": cambio.setText("Lluvia de ideas"); num="12"; pasarGeneral(); break;
            case "Lluvia de ideas": cambio.setText("Hipotesis");  num="13"; pasarGeneral();break;
            case "Hipotesis": cambio.setText("Palabras Clave"); num="14";pasarGeneral();break;
            case "Palabras Clave": cambio.setText("Lluvia de ideas 2"); num="15";pasarGeneral();break;
            case "Lluvia de ideas 2": cambio.setText("Respuesta Final"); num="16";pasarGeneral();break;
            case "Respuesta Final": v.setText(""); break;
        }
    }
    public void menu(View view){
        Intent intent = new Intent(Admin.this, AdminGrupo.class);
        startActivity(intent);
    }
    public void pasarGeneral(){
        String email = MainActivity.var1;
        String grup = PrincipalProfesor.g.get(PrincipalProfesor.posicion).getCodigo();
        //pass = codificarContrasena(pass);
        Map<String, String> datos = new HashMap<>();
        //datos.put("estudianteS", email);
        datos.put("gnum", num);
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
                                //Intent intent = new Intent(Admin.this, Admin.class);
                                //startActivity(intent);
                            } else {
                                Toast.makeText(Admin.this, "No se pudo iniciar", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(Admin.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(Admin.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                    }
                });
    }



}
