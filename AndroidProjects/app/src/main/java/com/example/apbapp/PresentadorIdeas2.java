package com.example.apbapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
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

import java.util.HashMap;
import java.util.Map;

public class PresentadorIdeas2 extends AppCompatActivity {

        TableLayout tabla;
        EditText tex;
        public static String a;
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
        a= tex.getText().toString();
        TableRow row = new TableRow(getBaseContext());
        TextView Nideas2 = new TextView(getBaseContext());
        Subir1();
        Nideas2.setTextColor(Color.WHITE);
        Nideas2.setTextSize(18);
        Nideas2.setText(a);
        row.addView(Nideas2);
        tabla.addView(row);
        tex.setText("");
        }
    public void Subir1(){
        //String texti= texto.getText().toString();
        Map<String,String> datos = new HashMap<>();
        datos.put("idea", a);
        datos.put("num2", AsignacionGrupo.numG);
        JSONObject jsonData = new JSONObject(datos);
        System.out.println(jsonData);
        AndroidNetworking.post(MainActivity.port+":8080/Proyecto/restJR/Activity/IdeaSubir2")
                .addJSONObjectBody(jsonData)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String estado=response.getString("Status");
                            if(estado.equals("hecho")){
                                Toast.makeText(PresentadorIdeas2.this, "subido", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(PresentadorIdeas2.this, "no", Toast.LENGTH_SHORT).show();
                            }



                        } catch (JSONException e) {
                            Toast.makeText(PresentadorIdeas2.this, "Error: "+e.getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(PresentadorIdeas2.this, "Error: "+anError.getErrorDetail() , Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
