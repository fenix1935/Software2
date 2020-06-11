package com.example.apbapp;

import android.content.Intent;
import android.os.Bundle;
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

        prob.setText(problem);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(PresentadorProblematica.this, PresentadorIdeas.class);
                //startActivity(intent);
                //Toast.makeText(PresentadorProblematica.this, h, Toast.LENGTH_SHORT).show();
                //Subir();
                textomio.setText(texto.getText().toString());
                texto.setText("");
                Subir();
            }
        });
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


}



