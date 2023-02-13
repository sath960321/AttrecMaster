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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.attrecmaster.adaptadores.ListAddEvaluacionAdapter;
import com.example.attrecmaster.clases.Estudiante;
import com.example.attrecmaster.clases.EstudiantesEvaluados;
import com.example.attrecmaster.clases.Registro;
import com.example.attrecmaster.db.registroModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

public class AddEvaluacionActivity extends AppCompatActivity {
    RecyclerView rvEvaluacion;
    TextInputLayout InputLayoutFechaEvaluacion;
    TextInputEditText EditTextFechaEvaluacion, EditTextListaNotas;
    MaterialButton saveEvaluacionBtn;
    AutoCompleteTextView EditTextTipoEvaluacion, EditTextListaEstudiantes;
    TextView viewAsign4, viewGrupo4, viewAnio4, viewFacultad4;
    Registro registro1;
    int id = 0;
    registroModel registroModelBDVer = new registroModel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_evaluacion);

        rvEvaluacion = findViewById(R.id.rvEvaluacion);
        InputLayoutFechaEvaluacion = findViewById(R.id.InputLayoutFechaEvaluacion);
        EditTextFechaEvaluacion = findViewById(R.id.EditTextFechaEvaluacion);
        saveEvaluacionBtn = findViewById(R.id.saveEvaluacionBtn);
        EditTextTipoEvaluacion = findViewById(R.id.EditTextTipoEvaluacion);
        EditTextListaEstudiantes = findViewById(R.id.EditTextListaEstudiantes);
        EditTextListaNotas = findViewById(R.id.EditTextListaNotas);
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

        registro1 = registroModelBDVer.verRegistro(id);

        if (registro1 != null){
            viewAsign4.setText("Asignatura: "+registro1.getAsignatura());
            viewGrupo4.setText("Grupo: "+registro1.getGrupo());
            viewAnio4.setText("Año: "+registro1.getAnio());
            viewFacultad4.setText("Facultad: "+registro1.getFacultad());
        }


        LanzarAdapterEstudiantesEvaluados();

        ArrayList<String> tiposclases = new ArrayList<>();
        tiposclases.add("Parcial");
        tiposclases.add("Final");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddEvaluacionActivity.this, R.layout.support_simple_spinner_dropdown_item, tiposclases);
        EditTextTipoEvaluacion.setAdapter(adapter);

        ArrayList<String> cargarEstu = registroModelBDVer.mostrarEstudianteRegistroListado(id);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(AddEvaluacionActivity.this, R.layout.support_simple_spinner_dropdown_item, cargarEstu);
        EditTextListaEstudiantes.setAdapter(adapter1);

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

        saveEvaluacionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!EditTextFechaEvaluacion.getText().toString().equals("") && !EditTextTipoEvaluacion.getText().toString().equals("") && !EditTextListaEstudiantes.getText().toString().equals("") && !EditTextListaNotas.getText().toString().equals("")){
//                  Insertar en la tabla evaluacion
                  long idEval = registroModelBDVer.insertarEvaluacion(EditTextFechaEvaluacion.getText().toString(), EditTextTipoEvaluacion.getText().toString(), Integer.parseInt(EditTextListaNotas.getText().toString()));
//                  Obtener idEstudiante para modificar
                  int EstudianteExiste = registroModelBDVer.ObtenerEstudianteNombreRegistro(id, EditTextListaEstudiantes.getText().toString().trim());
                   if (idEval > 0 ){
                       if (EstudianteExiste != 0){
                           registroModelBDVer.ActualizarRegistroControl(EstudianteExiste, ConvertirToInt(idEval));
                           Limpiar();
                           LanzarAdapterEstudiantesEvaluados();
                           Toast.makeText(AddEvaluacionActivity.this, "REGISTRO GUARDADO Y ACTUALIZADO", Toast.LENGTH_SHORT).show();
                       } else {
                           Toast.makeText(AddEvaluacionActivity.this, "NO EXISTE ESE ESTUDIANTE", Toast.LENGTH_SHORT).show();
                       }
                   } else {
                       Toast.makeText(AddEvaluacionActivity.this, "ERROR AL GUARDAR", Toast.LENGTH_SHORT).show();
                   }
                } else {
                    Toast.makeText(AddEvaluacionActivity.this, "DEBE LLENAR TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void LanzarAdapterEstudiantesEvaluados() {
        rvEvaluacion.setLayoutManager(new LinearLayoutManager(AddEvaluacionActivity.this));
        ArrayList<EstudiantesEvaluados> listaEval = registroModelBDVer.mostrarEvaluados();
        ListAddEvaluacionAdapter adapter = new ListAddEvaluacionAdapter(AddEvaluacionActivity.this, R.layout.activity_item_add_evaluacion, listaEval);
        rvEvaluacion.setAdapter(adapter);
    }

    private void Limpiar() {
        EditTextTipoEvaluacion.setText("");
        EditTextListaEstudiantes.setText("");
        EditTextListaNotas.setText("");
    }

    private int ConvertirToInt(long id) {
        int valor = 0;
        String str = String.valueOf(id);
        valor = Integer.parseInt(str);
        return valor;
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