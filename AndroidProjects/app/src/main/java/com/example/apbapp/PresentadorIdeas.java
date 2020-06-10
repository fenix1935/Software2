package com.example.apbapp;

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

public class PresentadorIdeas extends AppCompatActivity {
    ArrayList<String> ideas = new ArrayList<String>();
    private Button agregar;
    private Button seguir;
    String a;
    public void setIdeas(ArrayList<String> ideas) {
        this.ideas = ideas;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idea);

        agregar = (Button) findViewById(R.id.buttonIdeas);
        seguir = (Button) findViewById(R.id.buttonSiguienteIdeas);
        final TableLayout lista = (TableLayout) findViewById(R.id.tableclave);
        final EditText Idea = (EditText) findViewById(R.id.ETxInfoIdeas);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = String.valueOf(Idea.getText());
                ideas.add(a);
                Subir();
                TableLayout lista = (TableLayout) findViewById(R.id.tableclave);
                TableRow row = new TableRow(getBaseContext());
                TextView Nideas = new TextView(getBaseContext());
                Nideas.setTextColor(Color.WHITE);
                Nideas.setTextSize(18);
                Nideas.setText(a);
                row.addView(Nideas);
                lista.addView(row);
                Idea.setText("");
                //Toast.makeText(getBaseContext(),"aaaa"+ideas.toString(),Toast.LENGTH_LONG).show();
            }
        });
        seguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PresentadorIdeas.this,PresentadorHipotesis.class);
                intent.putExtra("ideas",ideas);
                startActivity(intent);
            }
        });
    }

    public void Subir(){
        String texti= a;
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

}

