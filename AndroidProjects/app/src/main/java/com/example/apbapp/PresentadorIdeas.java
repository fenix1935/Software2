package com.example.apbapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.vo.VOActividad;
import com.example.vo.VOIdeas;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PresentadorIdeas extends AppCompatActivity {
    ListView opciones;
    private ArrayAdapter<String> adapter;
    public static ArrayList<VOIdeas> gPi;
    private Button agregar;
    private Button seguir;
    String a;
    EditText subirI;
    private final int TIEMPO = 2000; // 1 Second
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideas);

        //agregar = (Button) findViewById(R.id.buttonIdeas);

        //final TableLayout lista = (TableLayout) findViewById(R.id.tableclave);
        //final EditText Idea = (EditText) findViewById(R.id.ETxInfoIdeas);
        opciones=(ListView)findViewById(R.id.listaIdeas);
        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        opciones.setAdapter(adapter);
        subirI= findViewById(R.id.subirIdea);
        ejecutarTarea12();
        opciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int posss=position;
                //String pos2= String.valueOf(pos);


                // Intent intent = new Intent(AsignacionTema.this, AsignacionGrupo.class);
                //startActivity(intent);
                //int saber= position;

                //Toast.makeText(AsignacionTema.this, "POS "+position, Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void ejecutarTarea12() {
        handler.postDelayed(new Runnable() {
            public void run() {
                // función a ejecutar
                //Toast.makeText(PrincipalProfesor.this, "asd", Toast.LENGTH_SHORT).show(); // función para refrescar la ubicación del conductor, creada en otra línea de código
                IdeaGet();
                pasar1();
                handler.postDelayed(this, TIEMPO);
            }
        }, TIEMPO);

    }
    public void botonidea(View view){
        Subir();
       // IdeaGet();
    }

    public void Subir(){
        String texti= subirI.getText().toString();
        Map<String,String> datos = new HashMap<>();
        datos.put("idea", texti);
        datos.put("num2", AsignacionGrupo.numG);
        JSONObject jsonData = new JSONObject(datos);
        System.out.println(jsonData);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Activity/IdeaSubir")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado=response.getString("Status");
                            if(estado.equals("hecho")){
                                Toast.makeText(PresentadorIdeas.this, "subido", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(PresentadorIdeas.this, "no", Toast.LENGTH_SHORT).show();
                            }



                        } catch (JSONException e) {
                            Toast.makeText(PresentadorIdeas.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(PresentadorIdeas.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void IdeaGet(){
        MainActivity m= new MainActivity();
        //String profe= PrincipalEstudiantes.g.get(PrincipalEstudiantes.posicion).getCodigoGrupo();
        Map<String,String> datos = new HashMap<>();
        datos.put("num2", AsignacionGrupo.numG);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Activity/IdeaGet").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            adapter.clear();
                            Gson gson= new Gson();
                            gPi= new ArrayList<VOIdeas>();
                            Type userListType = (new TypeToken<ArrayList<VOIdeas>>(){}).getType();
                            gPi= gson.fromJson(response, userListType);
                            //System.out.println(g.size());
                            //System.out.println(g.get(1).getNombreCurso());
                            for(int i=0; i<gPi.size();i++){
                                String a= gPi.get(i).getIdea();
                                adapter.add(gPi.get(i).getIdea());
                            }
                            adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            Toast.makeText(PresentadorIdeas.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(PresentadorIdeas.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void pasar1(){
        //String email = MainActivity.var1;
        //String grup = PrincipalProfesor.g.get(PrincipalProfesor.posicion).getCodigo();
        //pass = codificarContrasena(pass);
        Map<String, String> datos = new HashMap<>();
        //datos.put("estudianteS", email);
        datos.put("gnum", "11");
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Sesion/SesionNumero").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado = response.getString("Status");
                            //Toast.makeText(Registro.this, estado, Toast.LENGTH_SHORT).show();
                            if (estado.equals("13")) {
                                //estado1="1";
                                //Toast.makeText(AsignacionGrupo.this, estado1, Toast.LENGTH_SHORT).show();
                                // Toast.makeText(AsignacionGrupo.this, "sjsjs", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(PresentadorIdeas.this, PresentadorHipotesis.class);
                                startActivity(intent);
                                //Código importante.
                                handler.removeCallbacksAndMessages(null);

                            } else {
                                //Toast.makeText(AsignacionGrupo.this, estado1, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(AsignacionGrupo.this, "No se pudo iniciar", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(PresentadorIdeas.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(PresentadorIdeas.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}

