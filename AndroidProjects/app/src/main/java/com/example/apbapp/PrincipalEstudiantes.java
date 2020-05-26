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

public class PrincipalEstudiantes extends AppCompatActivity {

    ListView opciones;
    private ArrayAdapter<String> adapter;
    public static String cursoCode;
    public static String state;
    public static ArrayList<VOGruposEst> g;
    public static int posicion;
    private final int TIEMPO = 5000; // 1 Second
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estudiantes_principal);

        opciones = (ListView) findViewById(R.id.lis1);
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        opciones.setAdapter(adapter);
       // VerificarEst();
        CursosEstudiante();
        ejecutarTarea1();

        //listado, ver archivo Array en carpeta values
        opciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos=position;
                String pos2= String.valueOf(pos);
                cursoCode=opciones.getItemAtPosition(pos).toString();
                posicion=pos;
                //iniciarSesion(position);
                Intent intent = new Intent(PrincipalEstudiantes.this, AsignacionGrupo.class);
                startActivity(intent);
                //int saber= position;

               // Toast.makeText(PrincipalEstudiantes.this, g.get(pos).getCodigoGrupo(), Toast.LENGTH_SHORT).show();
            }
        });

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

    public void botonIrAgregarCurso(View view){
        Intent intent = new Intent(PrincipalEstudiantes.this, AgregarCurso.class);
        startActivity(intent);

    }
    public void boton1(View view){
        //String text= opciones.getSelectedItem().toString();
        //Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        //Intent intent = new Intent(PrincipalEstudiantes.this, Lobby.class);
        //startActivity(intent);
        MainActivity m= new MainActivity();
        String ja= m.getVar1();
        Toast.makeText(this, ja, Toast.LENGTH_SHORT).show();
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
                            adapter.clear();
                            Gson gson= new Gson();
                            g= new ArrayList<VOGruposEst>();
                            Type userListType = (new TypeToken<ArrayList<VOGruposEst>>(){}).getType();
                            g= gson.fromJson(response, userListType);
                            //System.out.println(g.size());
                            //System.out.println(g.get(1).getNombreCurso());
                            for(int i=0; i<g.size();i++){
                                adapter.add(g.get(i).getNombreG());
                            }
                            adapter.notifyDataSetChanged();
                        } catch (Exception e) {
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
