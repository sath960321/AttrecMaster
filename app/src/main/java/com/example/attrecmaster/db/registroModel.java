package com.example.attrecmaster.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.attrecmaster.clases.Registro;

import java.util.ArrayList;

public class registroModel extends DBConection {
    Context context;
    public registroModel(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public ArrayList<Registro> mostrarRegistros(){
        DBConection conex = new DBConection(context);
        SQLiteDatabase db =conex.getWritableDatabase();
        ArrayList<Registro> listaRegistro = new ArrayList<>();
        Registro registro;
        Cursor cursorregistro;
        cursorregistro = db.rawQuery("SELECT * FROM " + TABLE_REGISTRO, null);
        if (cursorregistro.moveToFirst()){
            do {
                registro = new Registro();
                registro.setId_registro(cursorregistro.getInt(0));
                registro.setAsignatura(cursorregistro.getString(1));
                registro.setGrupo(cursorregistro.getString(2));
                registro.setAnio(cursorregistro.getString(3));
                registro.setFacultad(cursorregistro.getInt(4));
                listaRegistro.add(registro);
            } while(cursorregistro.moveToNext());
        }
        cursorregistro.close();
        return listaRegistro;
    }

    public long insertarRegistro(String asignatura, String grupo, String anio, String facultad) {
        long id = 0;
        try {
            DBConection conex = new DBConection(context);
            SQLiteDatabase db = conex.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("asignatura", asignatura);
            values.put("grupo", grupo);
            values.put("anio", anio);
            values.put("facultad", facultad);
            id = db.insert(TABLE_REGISTRO, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }
}
