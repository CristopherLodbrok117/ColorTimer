package com.example.colortimer.DAO.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ColorDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "ColorTimer.db";
    public static final String TABLE_COLORES = "Colores";

    public ColorDBHelper(@Nullable Context context){
        super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE IF NOT EXISTS "+TABLE_COLORES+"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "valor INTEGER" +
            ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DELETE FROM "+TABLE_COLORES);
        onCreate(sqLiteDatabase);
    }
}
