package com.example.attrecmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.attrecmaster.adaptadores.ListAddAsistenciasAdapter;
import com.example.attrecmaster.adaptadores.ListEstudiantesAdapter;
import com.example.attrecmaster.clases.Registro;
import com.example.attrecmaster.db.registroModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class AddAsistenciaActivity extends AppCompatActivity {
    RecyclerView rvAddAsistencia;
    TextInputLayout InputLayoutFechaAsistencia;
    TextInputEditText EditTextFechaAsistencia;
    MaterialButton saveAsistenciaBtn;
    TextView viewAsign5, viewGrupo5, viewAnio5, viewFacultad5;
    Registro registro1;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asistencia);

        rvAddAsistencia = findViewById(R.id.rvAddAsistencia);
        InputLayoutFechaAsistencia = findViewById(R.id.InputLayoutFechaAsistencia);
        EditTextFechaAsistencia = findViewById(R.id.EditTextFechaAsistencia);
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

        rvAddAsistencia.setLayoutManager(new LinearLayoutManager(this));
        ListAddAsistenciasAdapter adapter = new ListAddAsistenciasAdapter(this, R.layout.activity_item_add_asistencia, registroModelBDVer.mostrarEstudianteRegistro(id));
        rvAddAsistencia.setAdapter(adapter);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        InputLayoutFechaAsistencia.setEndIconOnClickListener(new View.OnClickListener() {
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