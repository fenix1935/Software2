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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
        if(r1.isChecked()==true){
            tipo="Estudiante";
        }else if(r2.isChecked()==true){
                tipo="Profesor";
        } else {
            tipo = "";
        }
        if(!Contrasena.getText().toString().equals(Contrasena2.getText().toString())){
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }else if(tipo.equals("")){
                Toast.makeText(this, "Elija el tipo de usuario", Toast.LENGTH_SHORT).show();
        } else {
            guardarEstudiante();
        }
    }
    private void guardarEstudiante(){

        String email = Correo.getText().toString();
        String pass = Contrasena.getText().toString();
        pass = codificarContrasena(pass);
        Map<String,String> datos = new HashMap<>();
        datos.put("usuario", email);
        datos.put("password", pass);
        datos.put("tipo", tipo);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post("http://192.168.0.15:8080/Proyecto/restJR/Usuario/RegistrarUsuario").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado= response.getString("Status");
                            //Toast.makeText(Registro.this, estado, Toast.LENGTH_SHORT).show();
                            if (estado.compareTo("hecho")==0){
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

    private String codificarContrasena(String pass){
        String hash=null;
        String password=pass;
        try{
            MessageDigest sha256=MessageDigest.getInstance("SHA-256");
            sha256.update(password.getBytes("UTF-8"));
            byte[] digest = sha256.digest();
            StringBuffer sb = new StringBuffer();
            for(byte b : digest) {
                if (b > 0 && b < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(b & 0xff));
            }
            hash=sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return hash;
    }
}




