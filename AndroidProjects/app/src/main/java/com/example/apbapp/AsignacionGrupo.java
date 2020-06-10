package com.example.apbapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.vo.VOGrupos;
import com.example.vo.VOSesion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AsignacionGrupo extends AppCompatActivity {
 ListView opciones;
 TextView textoNombreGrupoAsignado2;
    TextView textoNombreGrupoAsignado;
 private static String state="No disponible";
    private final int TIEMPO = 2000; // 1 Second
    private Handler handler = new Handler();
    private ArrayAdapter<String> adapter;
    public static ArrayList<VOSesion> g1;
    private String estado1="0";
    private String estado2="no";
    public static String numG="0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grupo_asignacion);
        textoNombreGrupoAsignado2=findViewById(R.id.textoNombreGrupoAsignado2);
        textoNombreGrupoAsignado=findViewById(R.id.textoNombreGrupoAsignado);
        textoNombreGrupoAsignado2.setText(PrincipalEstudiantes.cursoCode+" "+state);

        opciones = (ListView) findViewById(R.id.listG);

        adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        opciones.setAdapter(adapter);
        update(PrincipalEstudiantes.posicion);
        //grupoGet1();
            ejecutarTarea2();


        adapter.add(MainActivity.var1);
        adapter.notifyDataSetChanged();
    }
    public void ejecutarTarea2() {
        handler.postDelayed(new Runnable() {
            public void run() {
               /* if(estado1.equals("1")){
                    Intent intent = new Intent(AsignacionGrupo.this, AsignacionTema.class);
                    startActivity(intent);
                }*/
                //textoNombreGrupoAsignado2.setText(PrincipalEstudiantes.cursoCode+" "+state);
                // función a ejecutar
                //Toast.makeText(PrincipalProfesor.this, "asd", Toast.LENGTH_SHORT).show(); // función para refrescar la ubicación del conductor, creada en otra línea de código
                update(PrincipalEstudiantes.posicion);
                update2();
                grupoGet1();
                pasar1();
                handler.postDelayed(this, TIEMPO);
            }

        }, TIEMPO);
    }

    public void grupoGet1(){
        MainActivity m= new MainActivity();
        String profe= PrincipalEstudiantes.g.get(PrincipalEstudiantes.posicion).getCodigoGrupo();
        Map<String,String> datos = new HashMap<>();
        datos.put("grupoS", profe);
        datos.put("Gnum",numG);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Sesion/SesionDatos").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            adapter.clear();
                            Gson gson= new Gson();
                            g1= new ArrayList<VOSesion>();
                            Type userListType = (new TypeToken<ArrayList<VOSesion>>(){}).getType();
                            g1= gson.fromJson(response, userListType);
                            //System.out.println(g.size());
                            //System.out.println(g.get(1).getNombreCurso());
                            for(int i=0; i<g1.size();i++){
                                adapter.add(g1.get(i).getEstudianteS());
                            }
                            adapter.notifyDataSetChanged();

                        } catch (Exception e) {
                            Toast.makeText(AsignacionGrupo.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(AsignacionGrupo.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void iniciarSesion(int pos){

        String email = MainActivity.var1;
        String grup = PrincipalEstudiantes.g.get(pos).getCodigoGrupo();
        //pass = codificarContrasena(pass);
        Map<String,String> datos = new HashMap<>();
        datos.put("estudianteS", email);
        datos.put("grupoS", grup);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Sesion/RegistrarSesion").
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
                                //Intent intent = new Intent(PrincipalEstudiantes.this, AsignacionGrupo.class);
                                //startActivity(intent);
                                Toast.makeText(AsignacionGrupo.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                //Intent intent = new Intent(PrincipalEstudiantes.this, AsignacionGrupo.class);
                                //startActivity(intent);
                                Toast.makeText(AsignacionGrupo.this, "No se ha comenzado la sesión o ya está registrado en una", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(AsignacionGrupo.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(AsignacionGrupo.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void delite(int pos){

        String email = MainActivity.var1;
        String grup = PrincipalEstudiantes.g.get(pos).getCodigoGrupo();
        //pass = codificarContrasena(pass);
        Map<String,String> datos = new HashMap<>();
        datos.put("estudianteS", email);
        datos.put("grupoS", grup);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Sesion/SesionDelete").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado= response.getString("Status");
                            //Toast.makeText(Registro.this, estado, Toast.LENGTH_SHORT).show();
                            if (estado.compareTo("Hecho")==0){
                                //Intent intent = new Intent(PrincipalEstudiantes.this, AsignacionGrupo.class);
                                //startActivity(intent);
                                Toast.makeText(AsignacionGrupo.this, "Usuario Eliminado", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                //Intent intent = new Intent(PrincipalEstudiantes.this, AsignacionGrupo.class);
                                //startActivity(intent);
                                Toast.makeText(AsignacionGrupo.this, "No se puede eliminar", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(AsignacionGrupo.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(AsignacionGrupo.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void update(int pos) {
        String email = MainActivity.var1;
        String grup = PrincipalEstudiantes.g.get(pos).getCodigoGrupo();
        //pass = codificarContrasena(pass);
        Map<String, String> datos = new HashMap<>();
        //datos.put("estudianteS", email);
        datos.put("grupoS", grup);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Sesion/Estado").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado = response.getString("Status");
                            //Toast.makeText(Registro.this, estado, Toast.LENGTH_SHORT).show();
                            if (estado.compareTo("hecho") == 0) {
                                //Intent intent = new Intent(PrincipalEstudiantes.this, AsignacionGrupo.class);
                                //startActivity(intent);
                                //Toast.makeText(AsignacionGrupo.this, "Usuario Eliminado", Toast.LENGTH_SHORT).show();
                                state="Disponible";
                                textoNombreGrupoAsignado2.setText(PrincipalEstudiantes.cursoCode+" "+state);
                            } else {
                                //Intent intent = new Intent(PrincipalEstudiantes.this, AsignacionGrupo.class);
                                //startActivity(intent);
                                //Toast.makeText(AsignacionGrupo.this, "No se puede eliminar", Toast.LENGTH_SHORT).show();
                                state="No Disponible";
                                textoNombreGrupoAsignado2.setText(PrincipalEstudiantes.cursoCode+" "+state);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(AsignacionGrupo.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(AsignacionGrupo.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void update2() {
        String email = MainActivity.var1;
        //String grup = PrincipalEstudiantes.g.get(pos).getCodigoGrupo();
        //pass = codificarContrasena(pass);
        Map<String, String> datos = new HashMap<>();
        //datos.put("estudianteS", email);
        datos.put("estudianteS", email);
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Sesion/Conseguir").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado = response.getString("Status");
                            //Toast.makeText(Registro.this, estado, Toast.LENGTH_SHORT).show();
                            if (estado.compareTo("null") == 0) {
                                //Intent intent = new Intent(PrincipalEstudiantes.this, AsignacionGrupo.class);
                                //startActivity(intent);
                                //Toast.makeText(AsignacionGrupo.this, "Usuario Eliminado", Toast.LENGTH_SHORT).show();
                                //state="Disponible";
                                textoNombreGrupoAsignado.setText("Grupo #?");
                            } else {
                                //Intent intent = new Intent(PrincipalEstudiantes.this, AsignacionGrupo.class);
                                //startActivity(intent);
                                //Toast.makeText(AsignacionGrupo.this, "No se puede eliminar", Toast.LENGTH_SHORT).show();
                                //state="No Disponible";
                                numG=estado;
                                textoNombreGrupoAsignado.setText("Grupo #"+estado);
                            }
                        } catch (JSONException e) {
                            Toast.makeText(AsignacionGrupo.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(AsignacionGrupo.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
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
                            if (estado.equals("11")) {
                                estado1="1";
                                //Toast.makeText(AsignacionGrupo.this, estado1, Toast.LENGTH_SHORT).show();
                               // Toast.makeText(AsignacionGrupo.this, "sjsjs", Toast.LENGTH_SHORT).show();
                                pasar2(PrincipalEstudiantes.posicion);
                                if(estado2.equals("si")) {
                                    Intent intent = new Intent(AsignacionGrupo.this, PresentadorProblematica.class);
                                    startActivity(intent);
                                    //Código importante.
                                    handler.removeCallbacksAndMessages(null);
                                }
                            } else {
                                //Toast.makeText(AsignacionGrupo.this, estado1, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(AsignacionGrupo.this, "No se pudo iniciar", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(AsignacionGrupo.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(AsignacionGrupo.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void pasar2(int pos){
        //String email = MainActivity.var1;
        //String grup = PrincipalProfesor.g.get(PrincipalProfesor.posicion).getCodigo();
        //pass = codificarContrasena(pass);
        Map<String, String> datos = new HashMap<>();
        //datos.put("estudianteS", email);
        datos.put("estudianteS", MainActivity.var1);
        datos.put("grupoS",PrincipalEstudiantes.g.get(pos).getCodigoGrupo());
        JSONObject jsonData = new JSONObject(datos);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Sesion/SesionNumero1").
                addJSONObjectBody(jsonData).
                setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado = response.getString("Status");
                            //Toast.makeText(Registro.this, estado, Toast.LENGTH_SHORT).show();
                            if (estado.equals("hecho")) {
                                estado1="1";
                                estado2="si";
                                //Toast.makeText(AsignacionGrupo.this, estado1, Toast.LENGTH_SHORT).show();
                                // Toast.makeText(AsignacionGrupo.this, "sjsjs", Toast.LENGTH_SHORT).show();
                                //Intent intent = new Intent(AsignacionGrupo.this, AsignacionTema.class);
                                //startActivity(intent);
                                //handler.removeCallbacksAndMessages(null);

                            } else {
                                //Toast.makeText(AsignacionGrupo.this, estado1, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(AsignacionGrupo.this, "No se pudo iniciar", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(AsignacionGrupo.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(AsignacionGrupo.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
    public void botonRegistrar1(View view){
        iniciarSesion(PrincipalEstudiantes.posicion);
    }
    public void botonEliminar1(View view){
        delite(PrincipalEstudiantes.posicion);
    }
}
