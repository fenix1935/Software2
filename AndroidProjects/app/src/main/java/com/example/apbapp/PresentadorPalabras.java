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

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PresentadorPalabras extends Activity {
    ArrayList<String> ideas = new ArrayList<String>();
    private Button agregar;
    private Button seguir;
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.llave);

        agregar = (Button) findViewById(R.id.BtnAgregarCv);
        seguir = (Button) findViewById(R.id.BtnSiguienteCv);
        final TableLayout lista = (TableLayout) findViewById(R.id.TablaClave);
        final EditText Idea = (EditText) findViewById(R.id.TextoClave);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = String.valueOf(Idea.getText());
                ideas.add(a);
                Subir();
                TableLayout lista = (TableLayout) findViewById(R.id.TablaClave);
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
        seguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PresentadorPalabras.this,links.class);
                intent.putExtra("ideas",ideas);
                startActivity(intent);
            }
        });
    }
    public void Subir(){
       // String texti= texto.getText().toString();
        Map<String,String> datos = new HashMap<>();
        datos.put("palabras", a);
        datos.put("num4", AsignacionGrupo.numG);
        JSONObject jsonData = new JSONObject(datos);
        System.out.println(jsonData);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Activity/PalabrasSubir")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado=response.getString("Status");
                            if(estado.equals("hecho")){
                                Toast.makeText(PresentadorPalabras.this, "subido", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(PresentadorPalabras.this, "no", Toast.LENGTH_SHORT).show();
                            }



                        } catch (JSONException e) {
                            Toast.makeText(PresentadorPalabras.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(PresentadorPalabras.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });
    }
}


