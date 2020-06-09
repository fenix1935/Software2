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

    ListView opciones;
    private ArrayAdapter<String> adapter;
    public static String cursoE;
    public static ArrayList<VOGrupos> g;
    private final int TIEMPO = 1000; // 1 Second
    private Handler handler = new Handler();
    public static int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.docentes_principal);

        opciones = (ListView) findViewById(R.id.listV);
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        opciones.setAdapter(adapter);

        CursosProfe();
        ejecutarTarea();

        opciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursoE=opciones.getItemAtPosition(position).toString();
                posicion=position;
                Intent intent = new Intent(PrincipalProfesor.this, Lobby.class);
                startActivity(intent);
                //Toast.makeText(PrincipalProfesor.this, g.get(position).getCodigo(), Toast.LENGTH_SHORT).show();


            }
        });
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
                            adapter.clear();
                            Gson gson= new Gson();
                            g= new ArrayList<VOGrupos>();
                            Type userListType = (new TypeToken<ArrayList<VOGrupos>>(){}).getType();
                            g= gson.fromJson(response, userListType);
                            //System.out.println(g.size());
                            //System.out.println(g.get(1).getNombreCurso());
                            for(int i=0; i<g.size();i++){
                                adapter.add(g.get(i).getNombreCurso()+": "+g.get(i).getCodigoAcceso());
                            }
                            adapter.notifyDataSetChanged();
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
