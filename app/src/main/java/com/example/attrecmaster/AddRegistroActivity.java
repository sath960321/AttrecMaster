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

public class AddRegistroActivity extends AppCompatActivity {
    TextInputEditText EditTextAsignatura, EditTextGrupo, EditTextAnio, EditTextFacultad;
    MaterialButton insertRegistroBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_registro);

        insertRegistroBtn = findViewById(R.id.insertRegistroBtn);
        EditTextAsignatura = findViewById(R.id.EditTextAsignatura);
        EditTextGrupo = findViewById(R.id.EditTextGrupo);
        EditTextAnio = findViewById(R.id.EditTextAnio);
        EditTextFacultad = findViewById(R.id.EditTextFacultad);


        insertRegistroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!EditTextAsignatura.getText().toString().equals("") || !EditTextGrupo.getText().toString().equals("") || !EditTextAnio.getText().toString().equals("") || !EditTextFacultad.getText().toString().equals("")){
                    registroModel modeldb = new registroModel(AddRegistroActivity.this);
                    long id = modeldb.insertarRegistro(EditTextAsignatura.getText().toString(),EditTextGrupo.getText().toString(),EditTextAnio.getText().toString(),EditTextFacultad.getText().toString());
                    if (id > 0) {
                        Toast.makeText(AddRegistroActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        Limpiar();
                        Intent intentBackAfterInsert = new Intent(AddRegistroActivity.this, MainActivity.class);
                        startActivity(intentBackAfterInsert);
                    } else {
                        Toast.makeText(AddRegistroActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(AddRegistroActivity.this, "DEBES LLENAR TODOS LOS CAMPOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void Limpiar() {
        EditTextAsignatura.setText("");
        EditTextGrupo.setText("");
        EditTextAnio.setText("");
        EditTextFacultad.setText("");
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