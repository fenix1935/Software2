package com.example.apbapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class links extends AppCompatActivity {
    private Button Buscar;
    String google=" ";
    private void recibirdata(){
        Bundle extras = getIntent().getExtras();
        ArrayList<String> d1 = extras.getStringArrayList("ideas");
        //String d1 = extras.getString("ideas");
        Toast.makeText(getBaseContext(),"aaaa"+d1,Toast.LENGTH_LONG).show();
        final TableLayout lista = (TableLayout) findViewById(R.id.tablaLink);

        for (int i=0;i<d1.size();i++){
            TableRow row = new TableRow(getBaseContext());
            TextView Nideas = new TextView(getBaseContext());
            Nideas.setTextColor(Color.WHITE);
            Nideas.setTextSize(18);
            Nideas.setText(d1.get(i));
            google+=d1.get(i)+" ";
            row.addView(Nideas);
            lista.addView(row);
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);
        recibirdata();
        Buscar = (Button) findViewById(R.id.btnbuscarLink);
        Buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(links.this, PresentadorIdeas2.class);
                startActivity(intent);
            }
        });
    }
}
