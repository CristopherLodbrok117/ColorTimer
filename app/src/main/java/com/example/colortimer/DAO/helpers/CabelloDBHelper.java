package com.example.colortimer.DAO.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CabelloDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "ColorTimer.db";
    public static final String TABLE_CABELLOS = "Cabellos";

    public CabelloDBHelper(@Nullable Context context){
        super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE IF NOT EXISTS "+TABLE_CABELLOS+"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "estado VARCHAR(1)," +
                "tipo VARCHAR(50)," +
                "idproceso INTEGER REFERENCES Procesos(id) DEFAULT NULL," +
                "idcolor INTEGER REFERENCES Colores(id) DEFAULT NULL" +
            ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DELETE FROM "+TABLE_CABELLOS);
        onCreate(sqLiteDatabase);
    }
}
