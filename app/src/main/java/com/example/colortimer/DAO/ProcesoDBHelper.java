package com.example.colortimer.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ProcesoDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "ColorTimer.db";
    public static final String TABLE_PROCESOS = "Procesos";

    public ProcesoDBHelper(@Nullable Context context){
        super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS "+TABLE_PROCESOS+"(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "tiempoDecoloracion INTEGER," +
                        "estado VARCHAR(1))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+TABLE_PROCESOS);
        this.onCreate(sqLiteDatabase);
    }
}
