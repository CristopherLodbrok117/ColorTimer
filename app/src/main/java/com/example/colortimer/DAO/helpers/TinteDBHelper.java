package com.example.colortimer.DAO.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TinteDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "ColorTimer.db";
    public static final String TABLE_TINTES = "Tintes";

    public TinteDBHelper(@Nullable Context context){
        super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE IF NOT EXISTS "+TABLE_TINTES+"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "marca VARCHAR(50)" +
            ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DELETE FROM "+TABLE_TINTES);
        onCreate(sqLiteDatabase);
    }
}
