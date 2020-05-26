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

import java.util.ArrayList;

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
}


