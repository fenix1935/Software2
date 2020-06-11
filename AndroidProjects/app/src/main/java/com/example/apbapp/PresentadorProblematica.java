package com.example.apbapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.vo.VOActividad;
import com.example.vo.VOGrupos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PresentadorProblematica extends AppCompatActivity{
    private Button btn;
    TextView prob;
    TextView urii;
    TextView textomio;
    String h;
    EditText texto;
    private String problem;
    private String link;
    public static ArrayList<VOActividad> g;
    private final int TIEMPO = 2000; // 1 Second
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problematica_estudiante);
        h = AsignacionTema.poss;
        prob = (TextView) findViewById(R.id.textView17);
        urii = (TextView) findViewById(R.id.textView18);
        textomio= (TextView)findViewById(R.id.textView20);
        btn = (Button) findViewById(R.id.buttonProblema);
        texto=(EditText) findViewById(R.id.editTextProblema);
        VerificarEst();
ejecutarTarea2();
        prob.setText(problem);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(PresentadorProblematica.this, h, Toast.LENGTH_SHORT).show();
                //Subir();
                textomio.setText(texto.getText().toString());
                texto.setText("");
                Subir();
            }
        });

    }
    public void ejecutarTarea2() {
        handler.postDelayed(new Runnable() {
            public void run() {
               /* if(estado1.equals("1")){
                    Intent intent = new Intent(AsignacionGrupo.this, AsignacionTema.class);
                    startActivity(intent);
                }*/
                //textoNombreGrupoAsignado2.setText(PrincipalEstudiantes1.cursoCode+" "+state);
                // función a ejecutar
                //Toast.makeText(PrincipalProfesor.this, "asd", Toast.LENGTH_SHORT).show(); // función para refrescar la ubicación del conductor, creada en otra línea de código
                pasar1();
                handler.postDelayed(this, TIEMPO);
            }

        }, TIEMPO);
    }

        private void VerificarEst(){
            Map<String,String> datos = new HashMap<>();
            datos.put("problematica", "0");
            JSONObject jsonData = new JSONObject(datos);
            System.out.println(jsonData);
            AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Actividad/GetProblema")
                    .addJSONObjectBody(jsonData)
                    .setPriority(Priority.MEDIUM)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                problem= response.getString("problematica");
                                link =response.getString("url");
                                prob.setText(problem);
                                urii.setText(link);

                                Toast.makeText(PresentadorProblematica.this, link, Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                Toast.makeText(PresentadorProblematica.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(PresentadorProblematica.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                        }
                    });
        }
public void Subir(){
        String texti= textomio.getText().toString();
    Map<String,String> datos = new HashMap<>();
    datos.put("problem", texti);
    datos.put("num1", AsignacionGrupo.numG);
    JSONObject jsonData = new JSONObject(datos);
    System.out.println(jsonData);
    AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Activity/ProblemaSubir")
            .addJSONObjectBody(jsonData)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(new JSONObjectRequestListener() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String estado=response.getString("Status");
                        if(estado.equals("hecho")){
                            Toast.makeText(PresentadorProblematica.this, "subido", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(PresentadorProblematica.this, "no", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        Toast.makeText(PresentadorProblematica.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onError(ANError anError) {
                    Toast.makeText(PresentadorProblematica.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
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
                            if (estado.equals("12")) {
                                //estado1="1";
                                //Toast.makeText(AsignacionGrupo.this, estado1, Toast.LENGTH_SHORT).show();
                                // Toast.makeText(AsignacionGrupo.this, "sjsjs", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(PresentadorProblematica.this, PresentadorIdeas.class);
                                    startActivity(intent);
                                    //Código importante.
                                    handler.removeCallbacksAndMessages(null);

                            } else {
                                //Toast.makeText(AsignacionGrupo.this, estado1, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(AsignacionGrupo.this, "No se pudo iniciar", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(PresentadorProblematica.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(PresentadorProblematica.this, "Error: " + anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

}



