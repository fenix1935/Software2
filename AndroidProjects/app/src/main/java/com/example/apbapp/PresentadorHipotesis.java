package com.example.apbapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class PresentadorHipotesis extends AppCompatActivity {
    private Button agregar;
    private Button Siguiente;
    String a;

    private void recibirdata(){
        Bundle extras = getIntent().getExtras();
        ArrayList<String> d1 = extras.getStringArrayList("ideas");
        //String d1 = extras.getString("ideas");
        Toast.makeText(getBaseContext(),"aaaa"+d1,Toast.LENGTH_LONG).show();
        final TableLayout lista = (TableLayout) findViewById(R.id.tableHipotesisIdeas);

        for (int i=0;i<d1.size();i++){
            TableRow row = new TableRow(getBaseContext());
            TextView Nideas = new TextView(getBaseContext());
            Nideas.setTextColor(Color.WHITE);
            Nideas.setTextSize(18);
            Nideas.setText(d1.get(i));
            row.addView(Nideas);
            lista.addView(row);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hipotesis);
        recibirdata();
        agregar = (Button) findViewById(R.id.buttonHipostesis);
        Siguiente = (Button) findViewById(R.id.buttonSiguienteHipotesis);
        final TableLayout lista = (TableLayout) findViewById(R.id.tableHipotesis);
        final EditText Idea = (EditText) findViewById(R.id.ETxInfoHipotesis);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista.removeAllViews();
                a = String.valueOf(Idea.getText());
                Subir();
                TableRow row = new TableRow(getBaseContext());
                TextView Nideas = new TextView(getBaseContext());
                Nideas.setTextColor(Color.WHITE);
                Nideas.setTextSize(18);
                Nideas.setText(a);
                row.addView(Nideas);
                lista.addView(row);
                Idea.setText("");
            }
        });
        Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PresentadorHipotesis.this,PresentadorPalabras.class);
                startActivity(intent);
            }
        });
    }
    public void Subir(){
        //String texti= texto.getText().toString();
        Map<String,String> datos = new HashMap<>();
        datos.put("hipo", a);
        datos.put("num3", AsignacionGrupo.numG);
        JSONObject jsonData = new JSONObject(datos);
        System.out.println(jsonData);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Activity/HipotesisSubir")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado=response.getString("Status");
                            if(estado.equals("hecho")){
                                Toast.makeText(PresentadorHipotesis.this, "subido", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(PresentadorHipotesis.this, "no", Toast.LENGTH_SHORT).show();
                            }



                        } catch (JSONException e) {
                            Toast.makeText(PresentadorHipotesis.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(PresentadorHipotesis.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
