package com.example.apbapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PresentadorIdeas2 extends AppCompatActivity {

TableLayout tabla;
EditText tex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ideas2);
        //Usuario = findViewById(R.id.textoUsuarioRegistro);
        //Contrasena = findViewById(R.id.textoContrasenaRegistro);
        tabla=findViewById(R.id.tableLayout);
        tex=findViewById(R.id.editText2ideas);



    }
    public void botonsit(View view){
        String a= tex.getText().toString();
        TableRow row = new TableRow(getBaseContext());
        TextView Nideas2 = new TextView(getBaseContext());
        Nideas2.setTextColor(Color.WHITE);
        Nideas2.setTextSize(18);
        Nideas2.setText(a);
        row.addView(Nideas2);
        tabla.addView(row);
        tex.setText("");
    }
}
