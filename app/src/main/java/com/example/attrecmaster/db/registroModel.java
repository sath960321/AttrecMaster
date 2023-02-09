package com.example.attrecmaster.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.attrecmaster.clases.Asistencia;
import com.example.attrecmaster.clases.Estudiante;
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

    public Registro verRegistro(int id){
        DBConection conex = new DBConection(context);
        SQLiteDatabase db = conex.getWritableDatabase();
        Registro registro = null;
        Cursor cursorRegistro;
        cursorRegistro = db.rawQuery("SELECT * FROM "+ TABLE_REGISTRO + " WHERE idregistro = "+ id + " LIMIT 5", null);
        if (cursorRegistro.moveToFirst()){
            registro = new Registro();
            registro.setId_registro(cursorRegistro.getInt(0));
            registro.setAsignatura(cursorRegistro.getString(1));
            registro.setGrupo(cursorRegistro.getString(2));
            registro.setAnio(cursorRegistro.getString(3));
            registro.setFacultad(cursorRegistro.getInt(4));
        }
        cursorRegistro.close();
        return registro;
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

    public long insertarEstudiante(String nombre, String ci, String sexo) {
        long id = 0;
        try {
            DBConection conex = new DBConection(context);
            SQLiteDatabase db = conex.getWritableDatabase();
            ContentValues values1 = new ContentValues();
            values1.put("nombre", nombre);
            values1.put("ci", ci);
            values1.put("sexo", sexo);
            id = db.insert(TABLE_ESTUDIANTES, null, values1);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public long insertarRegistroEstudiante(long idEstudiante, Integer idregistro) {
        long id = 0;
        try {
            DBConection conex = new DBConection(context);
            SQLiteDatabase db = conex.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("idEstudiante", idEstudiante);
            values.put("idregistro", idregistro);
            id = db.insert(TABLE_REGISTRO_CONTROL, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public ArrayList<Estudiante> mostrarEstudianteRegistro(int id){
        DBConection conex = new DBConection(context);
        SQLiteDatabase db = conex.getWritableDatabase();
        Estudiante estudiante = null;
        ArrayList<Estudiante> listaEstudiantes = new ArrayList<>();
        Cursor cursorEstudiante;
        cursorEstudiante = db.rawQuery("SELECT rgc.idregistroControl, rgc.idregistro, es.idestudiante, es.nombre, es.ci, es.sexo FROM registro_control rgc LEFT JOIN estudiantes es ON(rgc.idestudiante = es.idestudiante) WHERE rgc.idregistro = "+ id + " LIMIT 5", null);
        if (cursorEstudiante.moveToFirst()){
            do {
                String strnombre = cursorEstudiante.getString(3);
                String[] outputstr = strnombre.split(" ");
                estudiante = new Estudiante();
                estudiante.setIdestudiante(cursorEstudiante.getInt(2));
                estudiante.setNombre(outputstr[0]);
                estudiante.setCi(cursorEstudiante.getString(4));
                estudiante.setSexo(cursorEstudiante.getString(5));
                listaEstudiantes.add(estudiante);
            } while (cursorEstudiante.moveToNext());
        }
        cursorEstudiante.close();
        return listaEstudiantes;
    }

    public ArrayList<Asistencia> mostrarAsistenciasRegistro(int id){
        DBConection conex = new DBConection(context);
        SQLiteDatabase db = conex.getWritableDatabase();
        Asistencia asistencia = null;
        ArrayList<Asistencia> listaAsistencias = new ArrayList<>();
        Cursor cursorAsistencia;
        cursorAsistencia = db.rawQuery("SELECT * FROM registro_control rgc LEFT JOIN asistencia asis ON(rgc.idasistencia = asis.idasistencia) WHERE rgc.idregistro = "+ id + " AND rgc.idasistencia != NULL LIMIT 5", null);
        if (cursorAsistencia.moveToFirst()){
            do {
                asistencia = new Asistencia();
                asistencia.setIdasistencia(cursorAsistencia.getInt(5));
                asistencia.setFecha(cursorAsistencia.getString(6));
                asistencia.setEstado(cursorAsistencia.getString(7));
                listaAsistencias.add(asistencia);
            } while (cursorAsistencia.moveToNext());
        }
        cursorAsistencia.close();
        return listaAsistencias;
    }
}
