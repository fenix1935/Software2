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
        setContentView(R.layout.curso_ingresar);
        Codigo = findViewById(R.id.textoIntroducirCodigo);

    }

    public void botonAgregarCurso(View view){

        String cod = Codigo.getText().toString(); //obtiene el texto en el capo de "Codigo"
        guardarEstudiante();
        Intent intent = new Intent(AgregarCurso.this, PrincipalEstudiantes.class);
        startActivity(intent);

    }
    private void guardarEstudiante(){

        String correo=MainActivity.var1;
        String curso=Codigo.getText().toString();
        Map<String,String> datos= new HashMap<>();
        datos.put("correo", correo);
        datos.put("curso", curso);
        JSONObject jsonData = new JSONObject(datos);

        AndroidNetworking.post("https://guarded-everglades-76767.herokuapp.com/AgregarCursoEst.php").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado= response.getString("estado");
                            Toast.makeText(AgregarCurso.this, estado, Toast.LENGTH_SHORT).show();
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
