package com.example.apbapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static String var1;
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
        AndroidNetworking.post("http://192.168.1.3:8080/Proyecto/restJR/Usuario/loginUsuario")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Error")){
                            Toast.makeText(MainActivity.this, "Error en usuario o contraseña", Toast.LENGTH_SHORT).show();
                        }else {
                            try {
                                String estado = "";
                                SharedPreferences preferences = getSharedPreferences("beans", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("Token",response);
                                editor.commit();

                                String[] resp = response.split("\\.");
                                byte[] temp = Base64.getDecoder().decode(resp[1]);
                                String cadena = new String(temp);
                                JSONObject job = new JSONObject(cadena);
                                estado = job.getString("tipo");

                                if (estado.equals("Estudiante")) {
                                    Toast.makeText(MainActivity.this, estado + " logued", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, PrincipalEstudiantes.class);
                                    startActivity(intent);
                                } else if (estado.equals("Profesor")) {
                                    Toast.makeText(MainActivity.this, estado + " logued", Toast.LENGTH_SHORT).show();
                                    Intent intent3 = new Intent(MainActivity.this, PrincipalProfesor.class);
                                    startActivity(intent3);

                                }

                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
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
