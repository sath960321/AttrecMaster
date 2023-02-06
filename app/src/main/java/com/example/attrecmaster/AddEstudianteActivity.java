package com.example.attrecmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attrecmaster.db.registroModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddEstudianteActivity extends AppCompatActivity {
    TextInputEditText EditTextNombreEstudiante, EditTextCIEstudiante, EditTextSexoEstudiante;
    MaterialButton insertEstudianteBtn, insertEstudianteContinuarBtn;
    int idregistro = 0;
    Integer valorId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_estudiante);

        EditTextNombreEstudiante = findViewById(R.id.EditTextNombreEstudiante);
        EditTextCIEstudiante = findViewById(R.id.EditTextCIEstudiante);
        EditTextSexoEstudiante = findViewById(R.id.EditTextSexoEstudiante);
        insertEstudianteBtn = findViewById(R.id.insertEstudianteBtn);
        insertEstudianteContinuarBtn = findViewById(R.id.insertEstudianteContinuarBtn);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                idregistro = Integer.parseInt(null);
            } else {
                idregistro = extras.getInt("ID");
                valorId = extras.getInt("ID");
            }
        } else {
            idregistro = (int) savedInstanceState.getSerializable("ID");
        }



        insertEstudianteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!EditTextNombreEstudiante.getText().toString().equals("") || !EditTextCIEstudiante.getText().toString().equals("") || !EditTextSexoEstudiante.getText().toString().equals("")){
                    registroModel modeldb = new registroModel(AddEstudianteActivity.this);
                      long idEstudiante = modeldb.insertarEstudiante(EditTextNombreEstudiante.getText().toString(),EditTextCIEstudiante.getText().toString(), EditTextSexoEstudiante.getText().toString());
                      long idRegistroControl = modeldb.insertarRegistroEstudiante(idEstudiante, idregistro);
                      if (idEstudiante > 0 && idRegistroControl > 0){
                          Toast.makeText(AddEstudianteActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                          Limpiar();
                          Intent intentBackAfterInsert = new Intent(AddEstudianteActivity.this, ListadoEstudiantesActivity.class);
                          intentBackAfterInsert.putExtra("ID", idregistro);
                          startActivity(intentBackAfterInsert);
                      } else {
                          Toast.makeText(AddEstudianteActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                      }
                } else {
                    Toast.makeText(AddEstudianteActivity.this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void Limpiar() {
        EditTextNombreEstudiante.setText("");
        EditTextCIEstudiante.setText("");
        EditTextSexoEstudiante.setText("");
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