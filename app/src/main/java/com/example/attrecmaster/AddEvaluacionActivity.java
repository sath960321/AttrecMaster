package com.example.attrecmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.attrecmaster.adaptadores.ListAddEvaluacionAdapter;
import com.example.attrecmaster.clases.Registro;
import com.example.attrecmaster.db.registroModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Type;
import java.util.Calendar;

public class AddEvaluacionActivity extends AppCompatActivity {
    RecyclerView rvTipoEvaluacion;
    TextInputLayout InputLayoutFechaEvaluacion;
    TextInputEditText EditTextFechaEvaluacion;
    MaterialButton saveEvaluacionBtn;
    AutoCompleteTextView EditTextTipoEvaluacion;
    TextView viewAsign4, viewGrupo4, viewAnio4, viewFacultad4;
    Registro registro1;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evaluacion);

        rvTipoEvaluacion = findViewById(R.id.rvTipoEvaluacion);
        InputLayoutFechaEvaluacion = findViewById(R.id.InputLayoutFechaEvaluacion);
        EditTextFechaEvaluacion = findViewById(R.id.EditTextFechaEvaluacion);
        saveEvaluacionBtn = findViewById(R.id.saveEvaluacionBtn);
        EditTextTipoEvaluacion = findViewById(R.id.EditTextTipoEvaluacion);
        viewAsign4 = findViewById(R.id.viewAsign4);
        viewGrupo4 = findViewById(R.id.viewGrupo4);
        viewAnio4 = findViewById(R.id.viewAnio4);
        viewFacultad4 = findViewById(R.id.viewFacultad4);

        EditTextFechaEvaluacion.setInputType(InputType.TYPE_NULL);

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
            viewAsign4.setText("Asignatura: "+registro1.getAsignatura());
            viewGrupo4.setText("Grupo: "+registro1.getGrupo());
            viewAnio4.setText("Año: "+registro1.getAnio());
            viewFacultad4.setText("Facultad: "+registro1.getFacultad());
        }

        rvTipoEvaluacion.setLayoutManager(new LinearLayoutManager(this));
        ListAddEvaluacionAdapter adapter = new ListAddEvaluacionAdapter(this, R.layout.activity_item_add_evaluacion, registroModelBDVer.mostrarEstudianteRegistro(id));
        rvTipoEvaluacion.setAdapter(adapter);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        InputLayoutFechaEvaluacion.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddEvaluacionActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        EditTextFechaEvaluacion.setText(date);
                        EditTextFechaEvaluacion.setInputType(InputType.TYPE_NULL);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
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