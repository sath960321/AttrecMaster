package com.example.attrecmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.attrecmaster.adaptadores.ListRegistroAdapter;
import com.example.attrecmaster.db.registroModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvRegistro;
    FloatingActionButton fabAddRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvRegistro = findViewById(R.id.rvRegistro);
        fabAddRegistro = findViewById(R.id.fabAddRegistro);

        rvRegistro.setLayoutManager(new LinearLayoutManager(this));
        registroModel registroModeldb = new registroModel(this);
        ListRegistroAdapter adapter = new ListRegistroAdapter(this, R.layout.activity_item_registro, registroModeldb.mostrarRegistros());
        rvRegistro.setAdapter(adapter);

        fabAddRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddRegistro = new Intent(MainActivity.this, AddRegistroActivity.class);
                startActivity(intentAddRegistro);
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