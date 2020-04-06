package com.example.apbapp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity {


    EditText Correo;
    EditText Contrasena;
    EditText Contrasena2;
    RadioButton r1;
    RadioButton r2;
    RadioGroup Estado;
    CheckBox terminos;
    String tipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_usuario);
        Correo = findViewById(R.id.Correo);
        Contrasena = findViewById(R.id.contra);
        Contrasena2 = findViewById(R.id.contra2);

        r1= findViewById(R.id.radioButtonEstudiante);
        r2= findViewById(R.id.radioButtonProfesor);
    }



    @SuppressLint("ResourceType")
    public void botonRegistrar(View view){
        //Intent intent = new Intent(Registro.this, MainActivity.class);
        //startActivity(intent);
        if(r1.isChecked()==true){
            tipo="Estudiante";
        }else if(r2.isChecked()==true){
                tipo="Profesor";
        } else {
            tipo = "";
        }
        if(!Contrasena.getText().toString().equals(Contrasena2.getText().toString())){
            Toast.makeText(this, "Las cntraseñas no coincide", Toast.LENGTH_SHORT).show();
        }else if(tipo.equals("")){
                Toast.makeText(this, "Elija el tipo de usuario", Toast.LENGTH_SHORT).show();
        } else {
            guardarEstudiante();
        }
    }
    private void guardarEstudiante(){

        String email= Correo.getText().toString();
        String pass= Contrasena.getText().toString();
        Map<String,String> datos= new HashMap<>();
        datos.put("usuario", email);
        datos.put("password", pass);
        datos.put("tipo", tipo);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post("http://192.168.1.8:8080/Proyecto/restJR/Usuario/RegistrarUsuario").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado= response.getString("Status");
                            Toast.makeText(Registro.this, estado, Toast.LENGTH_SHORT).show();
                            if (estado.compareTo(estado)==0){
                                Intent intent = new Intent(Registro.this, MainActivity.class);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(Registro.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(Registro.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });

    }
}




