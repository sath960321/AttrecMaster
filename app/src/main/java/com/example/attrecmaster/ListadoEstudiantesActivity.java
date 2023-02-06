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

import com.example.attrecmaster.adaptadores.ListEstudiantesAdapter;
import com.example.attrecmaster.clases.Estudiante;
import com.example.attrecmaster.clases.Registro;
import com.example.attrecmaster.db.registroModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListadoEstudiantesActivity extends AppCompatActivity {
    TextView tvListadoEstudiantesInstancia, viewAsign1, viewGrupo1, viewAnio1, viewFacultad1;
    FloatingActionButton fabAddEstudiantes;
    RecyclerView rvListadoEstudiantes;
    Registro registro1;
    Estudiante estudiante1;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_estudiantes);

        tvListadoEstudiantesInstancia = findViewById(R.id.tvListadoEstudiantesInstancia);
        viewAsign1 = findViewById(R.id.viewAsign1);
        viewGrupo1 = findViewById(R.id.viewGrupo1);
        viewAnio1 = findViewById(R.id.viewAnio1);
        viewFacultad1 = findViewById(R.id.viewFacultad1);
        fabAddEstudiantes = findViewById(R.id.fabAddEstudiantes);
        rvListadoEstudiantes = findViewById(R.id.rvListadoEstudiantes);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
                Integer valorId = extras.getInt("ID");
                tvListadoEstudiantesInstancia.setText(valorId.toString());
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        registroModel registroModelBDVer = new registroModel(this);
        registro1 = registroModelBDVer.verRegistro(id);

        if (registro1 != null){
            viewAsign1.setText("Asignatura: "+registro1.getAsignatura());
            viewGrupo1.setText("Grupo: "+registro1.getGrupo());
            viewAnio1.setText("Año: "+registro1.getAnio());
            viewFacultad1.setText("Facultad: "+registro1.getFacultad());
        }

        rvListadoEstudiantes.setLayoutManager(new LinearLayoutManager(this));
        ListEstudiantesAdapter adapter = new ListEstudiantesAdapter(this, R.layout.activity_item_estudiantes, registroModelBDVer.mostrarEstudianteRegistro(id));
        rvListadoEstudiantes.setAdapter(adapter);


        fabAddEstudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddEstudiante = new Intent(ListadoEstudiantesActivity.this, AddEstudianteActivity.class);
                intentAddEstudiante.putExtra("ID", id);
                startActivity(intentAddEstudiante);
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