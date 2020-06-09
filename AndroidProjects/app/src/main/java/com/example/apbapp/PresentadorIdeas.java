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

import java.util.ArrayList;

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
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.idea);
        final EditText Idea = (EditText) findViewById(R.id.ETxInfoIdeas);
        Button agregarHip = (Button) findViewById(R.id.buttonIdeas);
        agregarHip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = String.valueOf(Idea.getText());
                TableLayout lista = (TableLayout) findViewById(R.id.tableIdeas);
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
    }


    public void pasoHip(View vista) {

        Intent changeHipotesis = new Intent(this, PresentadorHipotesis.class);
        startActivity(changeHipotesis);
    }
*/
}

