package com.example.attrecmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.attrecmaster.adaptadores.ListAsistenciasAdapter;
import com.example.attrecmaster.clases.Registro;
import com.example.attrecmaster.db.registroModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListadoAsistenciasActivity extends AppCompatActivity {
    RecyclerView rvListadoAsistencias;
    TextView tvListadoAsistenciasInstancia, viewAsign2, viewGrupo2, viewAnio2, viewFacultad2;;
    FloatingActionButton fabAddAsistencias;
    Registro registro1;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_asistencias);

        rvListadoAsistencias = findViewById(R.id.rvListadoAsistencias);
        tvListadoAsistenciasInstancia = findViewById(R.id.tvListadoAsistenciasInstancia);
        viewAsign2 = findViewById(R.id.viewAsign2);
        viewGrupo2 = findViewById(R.id.viewGrupo2);
        viewAnio2 = findViewById(R.id.viewAnio2);
        viewFacultad2 = findViewById(R.id.viewFacultad2);
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

        registroModel registroModelBDVer = new registroModel(this);
        registro1 = registroModelBDVer.verRegistro(id);

        if (registro1 != null){
            viewAsign2.setText("Asignatura: "+registro1.getAsignatura());
            viewGrupo2.setText("Grupo: "+registro1.getGrupo());
            viewAnio2.setText("Año: "+registro1.getAnio());
            viewFacultad2.setText("Facultad: "+registro1.getFacultad());
        }

        rvListadoAsistencias.setLayoutManager(new LinearLayoutManager(this));
        ListAsistenciasAdapter adapter = new ListAsistenciasAdapter(this, R.layout.activity_item_asistencias, registroModelBDVer.mostrarAsistenciasRegistro(id));
        rvListadoAsistencias.setAdapter(adapter);

        fabAddAsistencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddAsistencias = new Intent(ListadoAsistenciasActivity.this, AddAsistenciaActivity.class);
                intentAddAsistencias.putExtra("ID", id);
                startActivity(intentAddAsistencias);
            }
        });

    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater;
        inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }
    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuSetting:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}