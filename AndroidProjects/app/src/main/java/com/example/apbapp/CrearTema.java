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

public class CrearTema extends AppCompatActivity {

    EditText tema;
    EditText ur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problematica_crear);
        tema = findViewById(R.id.textoIntroducirTitulo);
        ur = findViewById(R.id.textoIntroducirURL);
    }

    public void botonCrearProblema(View view){

        //String cod = tema.getText().toString(); //obtiene el texto en el capo de "Codigo"
       // String urs= ur.getText().toString();
        crearTema();

    }
    private void crearTema(){

        String email = MainActivity.var1;
        String acceso = tema.getText().toString();
        String urs= ur.getText().toString();
        //pass = codificarContrasena(pass);
        Map<String,String> datos = new HashMap<>();
        datos.put("problematica", acceso);
        datos.put("url", urs);
        //datos.put("tipo", tipo);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Actividad/CrearProblema").
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
                                Intent intent = new Intent(CrearTema.this, AsignacionTema.class);
                                startActivity(intent);
                                Toast.makeText(CrearTema.this, "Tema Guardado", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(CrearTema.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(CrearTema.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
