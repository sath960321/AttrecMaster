package com.example.attrecmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.attrecmaster.clases.Registro;
import com.example.attrecmaster.db.registroModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListadoEvaluacionActivity extends AppCompatActivity {
    TextView viewAsign3, viewGrupo3, viewAnio3, viewFacultad3;
    FloatingActionButton fabAddEvaluacion;
    Registro registro1;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_evaluacion);

        viewAsign3 = findViewById(R.id.viewAsign3);
        viewGrupo3 = findViewById(R.id.viewGrupo3);
        viewAnio3 = findViewById(R.id.viewAnio3);
        viewFacultad3 = findViewById(R.id.viewFacultad3);
        fabAddEvaluacion = findViewById(R.id.fabAddEvaluacion);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
                Integer valorId = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        registroModel registroModelBDVer = new registroModel(this);
        registro1 = registroModelBDVer.verRegistro(id);

        if (registro1 != null){
            viewAsign3.setText("Asignatura: "+registro1.getAsignatura());
            viewGrupo3.setText("Grupo: "+registro1.getGrupo());
            viewAnio3.setText("Año: "+registro1.getAnio());
            viewFacultad3.setText("Facultad: "+registro1.getFacultad());
        }

        fabAddEvaluacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddEvaluacion = new Intent(ListadoEvaluacionActivity.this, AddEvaluacionActivity.class);
                intentAddEvaluacion.putExtra("ID", id);
                startActivity(intentAddEvaluacion);
            }
        });
    }
}