package com.example.apbapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
import com.example.apbapp.MainActivity;

public class CrearCurso extends AppCompatActivity {
    EditText nombre;
    EditText codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.curso_crear);
        nombre= findViewById(R.id.textoCrearNombreGrupo);
        codigo= findViewById(R.id.Code);


    }
    public void ButonCrear(View view){

        crearC();
        Intent intent = new Intent(CrearCurso.this, PrincipalProfesor.class);
        startActivity(intent);

    }
    private void crearC(){

        String correo=MainActivity.var1;
        String code= codigo.getText().toString();
        String name= nombre.getText().toString();
        Map<String,String> datos= new HashMap<>();
        datos.put("codigo", code);
        datos.put("nombre", name);
        datos.put("profe", correo);
        JSONObject jsonData = new JSONObject(datos);

        AndroidNetworking.post("https://guarded-everglades-76767.herokuapp.com/CrearGrupo.php").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado= response.getString("estado");
                            Toast.makeText(CrearCurso.this, estado, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Toast.makeText(CrearCurso.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(CrearCurso.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();

                    }
                });

    }
}
