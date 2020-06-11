package com.example.apbapp;

import com.example.apbapp.MainActivity;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AgregarCurso extends AppCompatActivity {

    EditText Codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unirse_curso_estudiante);
        Codigo = findViewById(R.id.unirseGrupo);

    }

    public void botonAgregarCurso(View view){

        String cod = Codigo.getText().toString(); //obtiene el texto en el capo de "Codigo"
        guardarCurso();

    }
    private void guardarCurso(){

        String email = MainActivity.var1;
        String acceso = Codigo.getText().toString();
        //pass = codificarContrasena(pass);
        Map<String,String> datos = new HashMap<>();
        datos.put("estudiante", email);
        datos.put("acceso", acceso);
        //datos.put("tipo", tipo);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Grupos/AgregarCurso").
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
                                Intent intent = new Intent(AgregarCurso.this, PrincipalEstudiantes1.class);
                                startActivity(intent);
                                Toast.makeText(AgregarCurso.this, "Curso Guardado", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(AgregarCurso.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(AgregarCurso.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });

    }

}
