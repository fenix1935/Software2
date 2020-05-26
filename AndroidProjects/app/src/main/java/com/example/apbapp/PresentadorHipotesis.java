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

import java.util.ArrayList;


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
                a = String.valueOf(Idea.getText());
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
}
