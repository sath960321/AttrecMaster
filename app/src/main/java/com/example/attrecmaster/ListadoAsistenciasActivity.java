package com.example.attrecmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListadoAsistenciasActivity extends AppCompatActivity {
    TextView tvListadoAsistenciasInstancia;
    FloatingActionButton fabAddAsistencias;
    Integer id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_asistencias);

        tvListadoAsistenciasInstancia = findViewById(R.id.tvListadoAsistenciasInstancia);
        fabAddAsistencias = findViewById(R.id.fabAddAsistencias);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
                Integer valorId = extras.getInt("ID");
                tvListadoAsistenciasInstancia.setText(valorId.toString());
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

    }
}