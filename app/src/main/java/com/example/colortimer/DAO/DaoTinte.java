package com.example.colortimer.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.colortimer.DAO.helpers.CabelloDBHelper;
import com.example.colortimer.DAO.helpers.TinteDBHelper;
import com.example.colortimer.Datos.Tinte;

import java.util.ArrayList;

public class DaoTinte extends TinteDBHelper {
    private Context context;

    public DaoTinte(@Nullable Context context){
        super(context);
        this.context = context;
    }

    /**
     * Crear (Create): Crea un nuevo registro en la base de datos
     * @params Tinte tinte
     * @return long id
     */
    public long crear(Tinte tinte){
        long id = -1;

        try {
            TinteDBHelper dbHelper = new TinteDBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("id",tinte.getId());
            values.put("marca",tinte.getMarca());
            db.insert(TABLE_TINTES,null,values);
            db.close();
        } catch (Exception e) {
            e.toString();
        } finally {
            return id;
        }

    }

    /**
     * Buscar (Read): Devuelve un solo registro, el que tenga el id que se paso
     * @params int idTinte
     * @return Tinte t
     */
    public Tinte buscar(int idTinte){
        Tinte t = new Tinte();
        ArrayList<Tinte> listado = this.listar();

        for(int i = 0; i < listado.size(); i++){
            if(listado.get(i).getId() == idTinte){
                t = listado.get(i);
                break;
            }
        }

        return t;
    }

    /**
     * Listar (Read): Devuelve todos los registros de procesos
     * @return ArrayList<Tinte> a
     */
    public ArrayList<Tinte> listar(){
        ArrayList<Tinte> a = new ArrayList<Tinte>();
        TinteDBHelper dbHelper = new TinteDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_TINTES,null);

        try {
            if(cursor.moveToFirst()){
                do {
                    a.add(new Tinte(cursor.getInt(0),cursor.getString(1)));
                }while (cursor.moveToNext());
            }
        } catch (Exception e){
            e.toString();
        } finally {
            return a;
        }
    }

    /**
     * Actualizar (Update): Actualiza un registro de la base de datos, esto con el id del proceso
     * que se paso como parametro
     * @params Tinte tinte
     */
    public void actualizar(Tinte tinte){
        TinteDBHelper dbHelper = new TinteDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db != null){
            db.execSQL("UPDATE "+TABLE_TINTES+" SET marca='"+tinte.getMarca()+"'");
            db.close();
        }
    }

    /**
     * Borrar (Delete): Borra un registro de la base de datos, esto con el id que se le pase como
     * parametro
     * @params int idTinte
     */
    public void borrar(int idTinte){
        CabelloDBHelper dbHelper = new CabelloDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db != null){
            db.execSQL("DELETE FROM "+TABLE_TINTES+" WHERE id='"+idTinte+"'");
            db.close();
        }
    }
}
