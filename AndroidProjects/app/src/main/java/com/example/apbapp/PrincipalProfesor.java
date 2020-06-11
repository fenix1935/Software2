package com.example.apbapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.vo.VOGrupos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class PrincipalProfesor extends AppCompatActivity {
    TextView primero;
    TextView primero1;
    TextView segundo;
    TextView segundo2;
    TextView tercero;
    TextView tercero3;
    TextView cuarto;
    TextView cuarto4;
    TextView quinto;
    TextView quinto5;
    TextView sexto;
    TextView sexto6;
    TextView septimo;
    TextView septimo7;
    TextView octavo;
    TextView octavo8;
    //ListView opciones;
    public static String cursoE;
    public static ArrayList<VOGrupos> g;
    private final int TIEMPO = 1000; // 1 Second
    private Handler handler = new Handler();
    public static int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos_profesor);

        primero= findViewById(R.id.curso_name_1);
        primero1= findViewById(R.id.curso_codigo1);
        segundo= findViewById(R.id.curso_name_2);
        segundo2= findViewById(R.id.curso_codigo2);
        tercero= findViewById(R.id.curso_name_3);
        tercero3= findViewById(R.id.curso_codigo3);
        cuarto= findViewById(R.id.curso_profe4);
        cuarto4= findViewById(R.id.curso_codigo4);
        quinto= findViewById(R.id.curso_profe5);
        quinto5= findViewById(R.id.curso_codigo5);
        sexto= findViewById(R.id.curso_profe6);
        sexto6= findViewById(R.id.curso_codigo6);
        septimo= findViewById(R.id.curso_profe7);
        septimo7= findViewById(R.id.curso_codigo7);
        octavo= findViewById(R.id.curso_profe8);
        octavo8= findViewById(R.id.curso_codigo8);

        primero.setText("-");
        segundo.setText("-");
        tercero.setText("-");
        cuarto.setText("-");
        quinto.setText("-");
        sexto.setText("-");
        septimo.setText("-");
        octavo.setText("-");

        CursosProfe();
        ejecutarTarea();
    }
    public void ejecutarTarea() {
        handler.postDelayed(new Runnable() {
            public void run() {
                // función a ejecutar
                //Toast.makeText(PrincipalProfesor.this, "asd", Toast.LENGTH_SHORT).show(); // función para refrescar la ubicación del conductor, creada en otra línea de código
                CursosProfe();
                handler.postDelayed(this, TIEMPO);
            }

        }, TIEMPO);

    }
    public void botonCrear(View view) {
        Intent intent = new Intent(PrincipalProfesor.this, CrearCurso.class);
        startActivity(intent);
    }
    public void boton11(View view){

        if(!primero.getText().toString().equals("-")){
            posicion=0;
            cursoE=g.get(posicion).getNombreCurso();
            Toast.makeText(this, "Iniciado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PrincipalProfesor.this, Lobby.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalProfesor.this, CrearCurso.class);
            startActivity(intent);
        }

        //Toast.makeText(this, "as: "+primero.getText().toString(), Toast.LENGTH_SHORT).show();
    }
    public void boton22(View view){
        if(!segundo.getText().toString().equals("-")){
            posicion=1;
            cursoE=g.get(posicion).getNombreCurso();
            Toast.makeText(this, "Iniciado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PrincipalProfesor.this, Lobby.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalProfesor.this, CrearCurso.class);
            startActivity(intent);
        }
    }
    public void boton33(View view){
        if(!tercero.getText().toString().equals("-")){
            posicion=2;
            cursoE=g.get(posicion).getNombreCurso();
            Toast.makeText(this,"Iniciado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PrincipalProfesor.this, Lobby.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalProfesor.this, CrearCurso.class);
            startActivity(intent);
        }
    }
    public void boton44(View view){
        if(!cuarto.getText().toString().equals("-")) {
            posicion = 3;
            cursoE = g.get(posicion).getNombreCurso();
            Toast.makeText(this, "Iniciado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PrincipalProfesor.this, Lobby.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalProfesor.this, CrearCurso.class);
            startActivity(intent);
        }
    }
    public void boton55(View view){
        if(!quinto.getText().toString().equals("-")){
            posicion=4;
            cursoE=g.get(posicion).getNombreCurso();
            Toast.makeText(this, "Iniciado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PrincipalProfesor.this, Lobby.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalProfesor.this, CrearCurso.class);
            startActivity(intent);
        }
    }
    public void boton66(View view){
        if(!sexto.getText().toString().equals("-")){
            posicion=5;
            cursoE=g.get(posicion).getNombreCurso();
            Toast.makeText(this, "Iniciado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PrincipalProfesor.this, Lobby.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalProfesor.this, CrearCurso.class);
            startActivity(intent);
        }
    }
    public void boton77(View view){
        if(!septimo.getText().toString().equals("-")){
            posicion=6;
            cursoE=g.get(posicion).getNombreCurso();
            Toast.makeText(this, "Iniciado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PrincipalProfesor.this, Lobby.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalProfesor.this, CrearCurso.class);
            startActivity(intent);
        }
    }
    public void boton88(View view){
        if(!octavo.getText().toString().equals("-")){
            posicion=7;
            cursoE=g.get(posicion).getNombreCurso();
            Toast.makeText(this, "Iniciado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PrincipalProfesor.this, Lobby.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalProfesor.this, CrearCurso.class);
            startActivity(intent);
        }
    }


    private void CursosProfe(){
        MainActivity m= new MainActivity();
        String profe=m.getVar1();
        Map<String,String> datos = new HashMap<>();
        datos.put("profesor", profe);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Grupos/CursosProfe").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Gson gson= new Gson();
                            g= new ArrayList<VOGrupos>();
                            Type userListType = (new TypeToken<ArrayList<VOGrupos>>(){}).getType();
                            g= gson.fromJson(response, userListType);
                            //System.out.println(g.size());
                            //System.out.println(g.get(1).getNombreCurso());
                            for(int i=0; i<g.size();i++){
                                switch(i) {
                                case 0: primero.setText(g.get(i).getNombreCurso()); primero1.setText(g.get(i).getCodigoAcceso()); break;
                                case 1: segundo.setText(g.get(i).getNombreCurso()); segundo2.setText(g.get(i).getCodigoAcceso()); break;
                                case 2: tercero.setText(g.get(i).getNombreCurso()); tercero3.setText(g.get(i).getCodigoAcceso()); break;
                                case 3: cuarto.setText(g.get(i).getNombreCurso()); cuarto4.setText(g.get(i).getCodigoAcceso()); break;
                                case 4: quinto.setText(g.get(i).getNombreCurso()); quinto5.setText(g.get(i).getCodigoAcceso()); break;
                                case 5: sexto.setText(g.get(i).getNombreCurso()); sexto6.setText(g.get(i).getCodigoAcceso()); break;
                                case 6: septimo.setText(g.get(i).getNombreCurso()); septimo7.setText(g.get(i).getCodigoAcceso()); break;
                                case 7: octavo.setText(g.get(i).getNombreCurso()); octavo8.setText(g.get(i).getCodigoAcceso()); break;


                                }

                            }

                        } catch (Exception e) {
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
