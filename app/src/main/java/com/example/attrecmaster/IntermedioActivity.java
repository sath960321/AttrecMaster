package com.example.attrecmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class IntermedioActivity extends AppCompatActivity {
    CardView cardViewAsistencia, cardViewEvaluaciones, cardViewListado, cardViewReporte;
    TextView textView;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermedio);

        textView = findViewById(R.id.textView);
        cardViewAsistencia = findViewById(R.id.cardViewAsistencia);
        cardViewEvaluaciones = findViewById(R.id.cardViewEvaluaciones);
        cardViewListado = findViewById(R.id.cardViewListado);
        cardViewReporte = findViewById(R.id.cardViewReporte);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
                Integer valorId = extras.getInt("ID");
                textView.setText(valorId.toString());
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        cardViewAsistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentListadoAsistencias = new Intent(IntermedioActivity.this, ListadoAsistenciasActivity.class);
                intentListadoAsistencias.putExtra("ID", id);
                startActivity(intentListadoAsistencias);
            }
        });

        cardViewListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentListadoEstudiantes = new Intent(IntermedioActivity.this, ListadoEstudiantesActivity.class);
                intentListadoEstudiantes.putExtra("ID", id);
                startActivity(intentListadoEstudiantes);
            }
        }
        );

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater;
        inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSetting:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}