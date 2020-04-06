package com.example.apbapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PrincipalProfesor extends AppCompatActivity {

    Spinner opciones;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docentes_principal);

        opciones = (Spinner)findViewById(R.id.spinnerCursosProfesor);
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);
        VerificarProf();
    }

    public void botonIrCrearCurso(View view){
        Intent intent = new Intent(PrincipalProfesor.this, CrearCurso.class);
        startActivity(intent);
    }
    private void VerificarProf(){
        String correo=MainActivity.var1;
        AndroidNetworking.get("https://guarded-everglades-76767.herokuapp.com/GetCursoProf.php?correo="+correo).

                setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado= response.getString("respuesta");


                            if (estado.equals("200")){
                                JSONArray arrayCursos= response.getJSONArray("data");
                                for(int i=0; i<arrayCursos.length();i++){
                                    JSONObject jsonCurso= arrayCursos.getJSONObject(i);
                                    String nombre=jsonCurso.getString("nombre");

                                    adapter.add(nombre);
                                }
                                adapter.notifyDataSetChanged();
                            }
                            else{
                                Toast.makeText(PrincipalProfesor.this, "Usuario no existe", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(PrincipalProfesor.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(PrincipalProfesor.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();

                    }
                });

    }


}
