package com.example.apbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static String var1;
    public static String port="http://192.168.0.4";
    EditText Usuario;
    EditText Contrasena;

    public void setVar1(String var1){
        this.var1=var1;
    }

    public String getVar1(){
        return this.var1;
    }

    public void modificarValorVar1(){
        this.var1 = "cambiado valor de var1";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Usuario = findViewById(R.id.textoUsuarioRegistro);
        Contrasena = findViewById(R.id.textoContrasenaRegistro);




    }
    //transfiere usuario a la ventana de registro
    public void botonIrRegistro(View view){
        Intent intent = new Intent(MainActivity.this, Registro.class);
        startActivity(intent);
    }

    //autentica el usuario como profesor o estudiante, o lo rechaza
    public void botonIniciarSesion(View view){
        //valida el usuario (complementario, a espera de BBDD)
        var1= Usuario.getText().toString();
        VerificarEst();
    }
    private void VerificarEst(){
        String email= Usuario.getText().toString();
        String passw= Contrasena.getText().toString();
        passw = codificarContrasena(passw);
        Map<String,String> datos = new HashMap<>();
        datos.put("usuario", email);
        datos.put("password", passw);
        JSONObject jsonData = new JSONObject(datos);
        System.out.println(jsonData);
        AndroidNetworking.post(port+":8080/Proyecto/restJR/Usuario/loginUsuario")
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
                               // finish();
                            }else if(estado.equals("Profesor")){
                                    Toast.makeText(MainActivity.this, estado + " logued", Toast.LENGTH_SHORT).show();
                                    Intent intent3 = new Intent(MainActivity.this, PrincipalProfesor.class);
                                    startActivity(intent3);
                                //    finish();

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

/**
 intent.putExtra("test",2); //envia a la clase
 getIntent().getExtras().getString("test"); //recibe en la otra clase
 **/
