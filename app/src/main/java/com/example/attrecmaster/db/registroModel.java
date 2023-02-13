package com.example.attrecmaster.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.attrecmaster.clases.Asistencia;
import com.example.attrecmaster.clases.Estudiante;
import com.example.attrecmaster.clases.EstudiantesEvaluados;
import com.example.attrecmaster.clases.Evaluacion;
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

    public ArrayList<String> mostrarEstudianteRegistroListado(int id){
        DBConection conex = new DBConection(context);
        SQLiteDatabase db = conex.getWritableDatabase();
        ArrayList<String> listaEstudiantes = new ArrayList<>();
        Cursor cursorEstudiante;
        cursorEstudiante = db.rawQuery("SELECT es.nombre FROM registro_control rgc LEFT JOIN estudiantes es ON(rgc.idestudiante = es.idestudiante) WHERE rgc.idregistro = "+ id + " LIMIT 5", null);
        if (cursorEstudiante.moveToFirst()){
            do {
                listaEstudiantes.add(cursorEstudiante.getString(0));
            } while (cursorEstudiante.moveToNext());
        }
        cursorEstudiante.close();
        return listaEstudiantes;
    }

    public int ObtenerEstudianteNombreRegistro(int idregistro, String nombre){
        DBConection conex = new DBConection(context);
        SQLiteDatabase db = conex.getWritableDatabase();
        int idestudianteSend = 0;
        Cursor cursorEstudiante;
        cursorEstudiante = db.rawQuery("SELECT rgc.idestudiante FROM registro_control rgc LEFT JOIN estudiantes es ON(rgc.idestudiante = es.idestudiante) WHERE rgc.idregistro = "+ idregistro + " AND es.nombre = '"+ nombre +"' LIMIT 5", null);
        if (cursorEstudiante.moveToFirst()){
            idestudianteSend = cursorEstudiante.getInt(0);
        }
        return idestudianteSend;
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

    public ArrayList<EstudiantesEvaluados> mostrarEvaluados(){
        DBConection conex = new DBConection(context);
        SQLiteDatabase db =conex.getWritableDatabase();
        ArrayList<EstudiantesEvaluados> listaRegistro = new ArrayList<>();
        EstudiantesEvaluados evaluados;
        Cursor cursorregistro;
        cursorregistro = db.rawQuery("SELECT rgc.idestudiante, es.nombre, es.ci, es.sexo, ev.valor  FROM registro_control rgc LEFT JOIN estudiantes es ON(rgc.idestudiante = es.idestudiante) LEFT JOIN evaluacion ev ON(rgc.idevaluacion = ev.idevaluacion)", null);
        if (cursorregistro.moveToFirst()){
            do {
                evaluados = new EstudiantesEvaluados();
                evaluados.setIdestudiante(cursorregistro.getInt(0));
                evaluados.setNombre(cursorregistro.getString(1));
                evaluados.setCi(cursorregistro.getString(2));
                evaluados.setSexo(cursorregistro.getString(3));
                evaluados.setValor(cursorregistro.getInt(4));
                listaRegistro.add(evaluados);
            } while(cursorregistro.moveToNext());
        }
        cursorregistro.close();
        return listaRegistro;
    }

    public ArrayList<Evaluacion> mostrarEvaluaciones(int idregistro){
        DBConection conex = new DBConection(context);
        SQLiteDatabase db =conex.getWritableDatabase();
        ArrayList<Evaluacion> listaRegistro = new ArrayList<>();
        Evaluacion evaluados;
        Cursor cursorregistro;
        cursorregistro = db.rawQuery("SELECT * FROM registro_control rgc LEFT JOIN estudiantes es ON(rgc.idestudiante = es.idestudiante) LEFT JOIN evaluacion ev ON(rgc.idevaluacion = ev.idevaluacion) WHERE rgc.idregistro = "+ idregistro + " LIMIT 10", null);
        if (cursorregistro.moveToFirst()){
            do {
                evaluados = new Evaluacion();
                evaluados.setIdevaluacion(cursorregistro.getInt(9));
                evaluados.setFecha(cursorregistro.getString(10));
                evaluados.setTipo(cursorregistro.getString(11));
                evaluados.setValor(cursorregistro.getInt(12));
                listaRegistro.add(evaluados);
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

    public long insertarEvaluacion(String fecha, String tipo, Integer valor) {
        long id = 0;
        try {
            DBConection conex = new DBConection(context);
            SQLiteDatabase db = conex.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("fecha", fecha);
            values.put("tipo", tipo);
            values.put("valor", valor);
            id = db.insert(TABLE_EVALUACION, null, values);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public boolean ActualizarRegistroControl(int idestudiante, int idevaluacion){
        boolean correcto = false;
        DBConection conex = new DBConection(context);
        SQLiteDatabase db = conex.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + TABLE_REGISTRO_CONTROL + " SET idevaluacion = " + idevaluacion + " WHERE idestudiante = " + idestudiante + " ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
}
