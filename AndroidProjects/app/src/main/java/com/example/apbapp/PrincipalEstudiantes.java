package com.example.apbapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.apbapp.MainActivity;
public class PrincipalEstudiantes extends AppCompatActivity {

    Spinner opciones;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estudiantes_principal);

        opciones = (Spinner)findViewById(R.id.spinnerCursosEstudiante);
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);
        VerificarEst();
        //listado, ver archivo Array en carpeta values

    }

    public void botonIrAgregarCurso(View view){
        Intent intent = new Intent(PrincipalEstudiantes.this, AgregarCurso.class);
        startActivity(intent);

    }
    public void boton1(View view){
        //String text= opciones.getSelectedItem().toString();
        //Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(PrincipalEstudiantes.this, Lobby.class);
        //startActivity(intent);

    }

    private void VerificarEst(){
        String correo=MainActivity.var1;
        AndroidNetworking.get("https://guarded-everglades-76767.herokuapp.com/GetCurso.php?correo="+correo).

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
                                Toast.makeText(PrincipalEstudiantes.this, "Usuario no existe", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(PrincipalEstudiantes.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(PrincipalEstudiantes.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();

                    }
                });

    }

}
