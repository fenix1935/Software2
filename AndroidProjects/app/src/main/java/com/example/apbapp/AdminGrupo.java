package com.example.apbapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AdminGrupo extends AppCompatActivity {

public static String verso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_verestudiantes);
    }

    public void op1(View view){
verso="1";
        Intent intent = new Intent(AdminGrupo.this, VerRespuestas.class);
        startActivity(intent);
    }
    public void op2(View view){
        verso="2";
        Intent intent = new Intent(AdminGrupo.this, VerRespuestas.class);
        startActivity(intent);
    }
    public void op3(View view){
        verso="3";
        Intent intent = new Intent(AdminGrupo.this, VerRespuestas.class);
        startActivity(intent);
    }



}
