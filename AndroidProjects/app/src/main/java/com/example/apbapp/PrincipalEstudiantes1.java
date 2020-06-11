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

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidnetworking.interfaces.StringRequestListener;
import com.example.apbapp.MainActivity;
import com.example.vo.VOGrupos;
import com.example.vo.VOGruposEst;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PrincipalEstudiantes1 extends AppCompatActivity {

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
    //private ArrayAdapter<String> adapter;
    public static String cursoCode;
    public static String state;
    public static ArrayList<VOGruposEst> g;
    public static int posicion;
    private final int TIEMPO = 2000; // 1 Second
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos_estudiante);

        primero= findViewById(R.id.curso_nombre_1);
        primero1= findViewById(R.id.codigoC1);
        segundo= findViewById(R.id.curso_nombre_2);
        segundo2= findViewById(R.id.codigoC2);
        tercero= findViewById(R.id.curso_nombre_3);
        tercero3= findViewById(R.id.codigoC3);
        cuarto= findViewById(R.id.curso_nombre_4);
        cuarto4= findViewById(R.id.codigoC4);
        quinto= findViewById(R.id.curso_nombre_5);
        quinto5= findViewById(R.id.codigo_C5);
        sexto= findViewById(R.id.curso_nombre_6);
        sexto6= findViewById(R.id.codigoC6);
        septimo= findViewById(R.id.curso_nombre_7);
        septimo7= findViewById(R.id.codigoC7);
        octavo= findViewById(R.id.curso_nombre_8);
        octavo8= findViewById(R.id.codigoC8);

        primero.setText("-");
        segundo.setText("-");
        tercero.setText("-");
        cuarto.setText("-");
        quinto.setText("-");
        sexto.setText("-");
        septimo.setText("-");
        octavo.setText("-");


        // VerificarEst();
        CursosEstudiante();
        ejecutarTarea1();

    }
    public void ejecutarTarea1() {
        handler.postDelayed(new Runnable() {
            public void run() {

                // función a ejecutar
                //Toast.makeText(PrincipalProfesor.this, "asd", Toast.LENGTH_SHORT).show(); // función para refrescar la ubicación del conductor, creada en otra línea de código
                CursosEstudiante();
                handler.postDelayed(this, TIEMPO);
            }

        }, TIEMPO);
    }

    public void boton1(View view){

        if(!primero.getText().toString().equals("-")){
            posicion=0;
            cursoCode=g.get(posicion).getNombreG();
            Toast.makeText(this, "Iniciado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PrincipalEstudiantes1.this, AsignacionGrupo.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalEstudiantes1.this, AgregarCurso.class);
            startActivity(intent);
        }

        //Toast.makeText(this, "as: "+primero.getText().toString(), Toast.LENGTH_SHORT).show();
    }
    public void boton2(View view){
        if(!segundo.getText().toString().equals("-")){
        posicion=1;
        cursoCode=g.get(posicion).getNombreG();
        Toast.makeText(this, "Iniciado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PrincipalEstudiantes1.this, AsignacionGrupo.class);
        startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalEstudiantes1.this, AgregarCurso.class);
            startActivity(intent);
        }
    }
    public void boton3(View view){
        if(!tercero.getText().toString().equals("-")){
        posicion=2;
        cursoCode=g.get(posicion).getNombreG();
        Toast.makeText(this,"Iniciado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PrincipalEstudiantes1.this, AsignacionGrupo.class);
        startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalEstudiantes1.this, AgregarCurso.class);
            startActivity(intent);
        }
    }
    public void boton4(View view){
        if(!cuarto.getText().toString().equals("-")) {
            posicion = 3;
            cursoCode = g.get(posicion).getNombreG();
            Toast.makeText(this, "Iniciado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(PrincipalEstudiantes1.this, AsignacionGrupo.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalEstudiantes1.this, AgregarCurso.class);
            startActivity(intent);
        }
    }
    public void boton5(View view){
        if(!quinto.getText().toString().equals("-")){
        posicion=4;
        cursoCode=g.get(posicion).getNombreG();
        Toast.makeText(this, "Iniciado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PrincipalEstudiantes1.this, AsignacionGrupo.class);
        startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalEstudiantes1.this, AgregarCurso.class);
            startActivity(intent);
        }
    }
    public void boton6(View view){
        if(!sexto.getText().toString().equals("-")){
        posicion=5;
        cursoCode=g.get(posicion).getNombreG();
        Toast.makeText(this, "Iniciado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PrincipalEstudiantes1.this, AsignacionGrupo.class);
        startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalEstudiantes1.this, AgregarCurso.class);
            startActivity(intent);
        }
    }
    public void boton7(View view){
        if(!septimo.getText().toString().equals("-")){
        posicion=6;
        cursoCode=g.get(posicion).getNombreG();
        Toast.makeText(this, "Iniciado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PrincipalEstudiantes1.this, AsignacionGrupo.class);
        startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalEstudiantes1.this, AgregarCurso.class);
            startActivity(intent);
        }
    }
    public void boton8(View view){
        if(!octavo.getText().toString().equals("-")){
        posicion=7;
        cursoCode=g.get(posicion).getNombreG();
        Toast.makeText(this, "Iniciado", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PrincipalEstudiantes1.this, AsignacionGrupo.class);
        startActivity(intent);
        }
        else{
            Intent intent = new Intent(PrincipalEstudiantes1.this, AgregarCurso.class);
            startActivity(intent);
        }
    }
    private void CursosEstudiante(){
        MainActivity m= new MainActivity();
        String estudiante=m.getVar1();
        Map<String,String> datos = new HashMap<>();
        datos.put("estudiante", estudiante);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Grupos/CursosEstudiante").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //adapter.clear();
                            Gson gson= new Gson();
                            g= new ArrayList<VOGruposEst>();
                            Type userListType = (new TypeToken<ArrayList<VOGruposEst>>(){}).getType();
                            g= gson.fromJson(response, userListType);
                            //System.out.println(g.size());
                            //System.out.println(g.get(1).getNombreCurso());
                            for(int i=0; i<g.size();i++){
                                //adapter.add(g.get(i).getNombreG());
                                switch(i){
                                    case 0: primero.setText(g.get(i).getNombreG()); primero1.setText(g.get(i).getCodigo1()); break;
                                    case 1: segundo.setText(g.get(i).getNombreG()); segundo2.setText(g.get(i).getCodigo1()); break;
                                    case 2: tercero.setText(g.get(i).getNombreG()); tercero3.setText(g.get(i).getCodigo1()); break;
                                    case 3: cuarto.setText(g.get(i).getNombreG()); cuarto4.setText(g.get(i).getCodigo1()); break;
                                    case 4: quinto.setText(g.get(i).getNombreG()); quinto5.setText(g.get(i).getCodigo1()); break;
                                    case 5: sexto.setText(g.get(i).getNombreG()); sexto6.setText(g.get(i).getCodigo1()); break;
                                    case 6: septimo.setText(g.get(i).getNombreG()); septimo7.setText(g.get(i).getCodigo1()); break;
                                    case 7: octavo.setText(g.get(i).getNombreG()); octavo8.setText(g.get(i).getCodigo1()); break;
                                }
                            }
                            //adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Toast.makeText(PrincipalEstudiantes1.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(PrincipalEstudiantes1.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
