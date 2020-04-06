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

    //autentica el usuario como profesor o estudienta, o lo rechaza
    public void botonIniciarSesion(View view){
        //valida el usuario (complementario, a espera de BBDD)
        var1= Usuario.getText().toString();
        VerificarEst();
    }
    private void VerificarEst(){
        String email= Usuario.getText().toString();
        String passw= Contrasena.getText().toString();
        AndroidNetworking.get("https://guarded-everglades-76767.herokuapp.com/VerificarUsuario.php?email="+email+"&passw="+passw).

                setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado= response.getString("respuesta");

                            if (estado.equals("200")){
                                JSONObject arrayObjeto= response.getJSONObject("data");
                                String tipe= arrayObjeto.getString("tipo");
                                if(tipe.equals("Estudiante")) {

                                    Toast.makeText(MainActivity.this, tipe + " logued", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, PrincipalEstudiantes.class);
                                    startActivity(intent);
                                }else{
                                    if(tipe.equals("Profesor")){
                                        Toast.makeText(MainActivity.this, tipe + " logued", Toast.LENGTH_SHORT).show();
                                        Intent intent3 = new Intent(MainActivity.this, PrincipalProfesor.class);
                                        startActivity(intent3);
                                    }
                                }
                            }
                            else{
                                Toast.makeText(MainActivity.this, "Usuario no existe", Toast.LENGTH_SHORT).show();
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







    }







/**
 intent.putExtra("test",2); //envia a la clase
 getIntent().getExtras().getString("test"); //recibe en la otra clase
 **/
