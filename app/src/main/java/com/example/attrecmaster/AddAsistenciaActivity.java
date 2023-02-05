package com.example.attrecmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.attrecmaster.clases.Registro;
import com.example.attrecmaster.db.registroModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class AddAsistenciaActivity extends AppCompatActivity {

    TextInputLayout InputLayoutFechaAsistencia;
    TextInputEditText EditTextFechaAsistencia;
    MaterialButton saveAsistenciaBtn;
    TextView tvListadoAddAsistenciaInstancia, viewAsign5, viewGrupo5, viewAnio5, viewFacultad5;
    Registro registro1;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asistencia);

        InputLayoutFechaAsistencia = findViewById(R.id.InputLayoutFechaAsistencia);
        EditTextFechaAsistencia = findViewById(R.id.EditTextFechaAsistencia);
        tvListadoAddAsistenciaInstancia = findViewById(R.id.tvListadoAddAsistenciaInstancia);
        viewAsign5 = findViewById(R.id.viewAsign5);
        viewGrupo5 = findViewById(R.id.viewGrupo5);
        viewAnio5 = findViewById(R.id.viewAnio5);
        viewFacultad5 = findViewById(R.id.viewFacultad5);
        saveAsistenciaBtn = findViewById(R.id.saveAsistenciaBtn);


        EditTextFechaAsistencia.setInputType(InputType.TYPE_NULL);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
                Integer valorId = extras.getInt("ID");
                tvListadoAddAsistenciaInstancia.setText(valorId.toString());
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }


        registroModel registroModelBDVer = new registroModel(this);
        registro1 = registroModelBDVer.verRegistro(id);

        if (registro1 != null){
            viewAsign5.setText("Asignatura: "+registro1.getAsignatura());
            viewGrupo5.setText("Grupo: "+registro1.getGrupo());
            viewAnio5.setText("Año: "+registro1.getAnio());
            viewFacultad5.setText("Facultad: "+registro1.getFacultad());
        }

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        InputLayoutFechaAsistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddAsistenciaActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        EditTextFechaAsistencia.setText(date);
                        EditTextFechaAsistencia.setInputType(InputType.TYPE_NULL);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
}