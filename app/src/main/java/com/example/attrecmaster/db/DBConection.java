package com.example.attrecmaster.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConection extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NOMBRE = "attrec.db";

    public static final String TABLE_REGISTRO = "registros";

    public static final String TABLE_ESTUDIANTES = "estudiantes";

    public static final String TABLE_ASISTENCIA = "asistencia";

    public static final String TABLE_EVALUACION = "evaluacion";

    public static final String TABLE_REGISTRO_CONTROL = "registro_control";

    public static final String TABLE_ESTADO = "estado";

    public static final String TABLE_TIPO = "tipo";

    public DBConection(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_REGISTRO +  "(" +
                "idregistro INTEGER PRIMARY KEY AUTOINCREMENT," +
                "asignatura TEXT NOT NULL," +
                "grupo TEXT NOT NULL," +
                "anio TEXT NOT NULL," +
                "facultad TEXT NOT NULL)");
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ESTUDIANTES +  "(" +
                "idestudiante INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "ci TEXT NOT NULL," +
                "sexo TEXT NOT NULL)");
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ASISTENCIA +  "(" +
                "idasistencia INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fecha TEXT NOT NULL," +
                "estado TEXT NOT NULL," +
                "anio TEXT NOT NULL)");
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EVALUACION +  "(" +
                "idevaluacion INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fecha TEXT NOT NULL," +
                "tipo TEXT NOT NULL," +
                "valor TEXT NOT NULL)");
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_REGISTRO_CONTROL +  "(" +
                "idregistroControl INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idregistro INTEGER NOT NULL," +
                "idestudiante INTEGER NOT NULL," +
                "idevaluacion INTEGER NOT NULL," +
                "idasistencia INTEGER NOT NULL)");
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ESTADO +  "(" +
                "idestado INTEGER PRIMARY KEY AUTOINCREMENT," +
                "estado TEXT NOT NULL)");
            sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_TIPO +  "(" +
                "id_tipoEvaluacion INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tipo TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ESTUDIANTES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ASISTENCIA);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EVALUACION);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRO_CONTROL);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ESTADO);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TIPO);
        onCreate(sqLiteDatabase);
    }
}
