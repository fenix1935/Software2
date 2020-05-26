package com.example.apbapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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
import java.util.Random;

import com.example.apbapp.MainActivity;

public class CrearCurso extends AppCompatActivity {
    EditText nombre;
    EditText cod;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.curso_crear);
        nombre= findViewById(R.id.textoCrearNombreGrupo);
        name=findViewById(R.id.textView);
        cod=findViewById(R.id.textoCrearNombreGrupo2);
        name.setText("Bienvenido "+MainActivity.var1);
        cod.setText(codigoGenerado());


    }
    public void ButonCrear(View view){
        crearC();

    }
    public void ButonUpdate(View view){
        cod.setText(codigoGenerado());
    }
    public String codigoGenerado(){
        Random aleatorio = new Random();
        String alfa = "ABCDEFGHIJKLMNOPQRSTVWXYZ";
        String beta = "ABCDEFGHIJKLMNOPQRSTVWXYZ";
        String omega = "ABCDEFGHIJKLMNOPQRSTVWXYZ";
        String cadena = "";    //Inicializamos la Variable//
        int numero;
        int forma;
        int forma1;
        int forma2;
        forma=(int)(aleatorio.nextDouble() * alfa.length()-1+0);
        forma1=(int)(aleatorio.nextDouble() * beta.length()-1+0);
        forma2=(int)(aleatorio.nextDouble() * omega.length()-1+0);
//Definimos la cantidad máxima de números aleatorios (99) y sumamos 100 para mantener 3 números cada vez//
        numero=(int)(aleatorio.nextDouble() * 999);
        cadena=cadena+alfa.charAt(forma)+beta.charAt(forma1)+omega.charAt(forma2)+numero;
        return cadena;
    }


    private void crearC(){
        MainActivity m= new MainActivity();
        String profe=m.getVar1();
        String nombre1 = nombre.getText().toString();
        String pass = cod.getText().toString();
        Map<String,String> datos = new HashMap<>();
        datos.put("codigoAcceso", pass);
        datos.put("nombreCurso", nombre1);
        datos.put("profesor", profe);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Grupos/RegistrarGrupo").
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
                                Intent intent = new Intent(CrearCurso.this, PrincipalProfesor.class);
                                startActivity(intent);
                                Toast.makeText(CrearCurso.this, "Curso Registrado", Toast.LENGTH_SHORT).show();
                            }
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
