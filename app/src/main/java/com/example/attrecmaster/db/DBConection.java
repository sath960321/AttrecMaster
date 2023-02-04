package com.example.attrecmaster.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConection extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_NOMBRE = "attrec.db";

    public static final String TABLE_REGISTRO = "registros";

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRO);
        onCreate(sqLiteDatabase);
    }
}
