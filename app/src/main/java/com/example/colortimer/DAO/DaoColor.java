package com.example.colortimer.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.colortimer.DAO.helpers.ColorDBHelper;
import com.example.colortimer.Datos.Color;

import java.util.ArrayList;

public class DaoColor extends ColorDBHelper {
    private Context context;
    public DaoColor(@Nullable Context context){
        super(context);
        this.context = context;
    }

    /**
     * Crear (Create): Crea un nuevo registro en la base de datos
     * @params Color color
     * @return boolean creado
     */
    public long crear(Color color){
        long id = -1;
        try{
            ColorDBHelper dbHelper = new ColorDBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("valor",color.getValor());
            id = db.insert(TABLE_COLORES,null,values);
            db.close();
        } catch (Exception e) {
            e.toString();
        } finally {
            return id;
        }
    }

    /**
     * Buscar (Read): Devuelve un solo registro, el que tenga el id que se paso
     * @params int idColor
     * @return Color c
     */
    public Color buscar(int idColor){
        Color c = new Color();
        ArrayList<Color> listado = this.listar();

        for(int i = 0; i < listado.size(); i++){
            if(listado.get(i).getId() == idColor){
                c = listado.get(i);
                break;
            }
        }

        return c;
    }

    /**
     * Listar (Read): Devuelve todos los registros de procesos
     * @return ArrayList<Color> a
     */
    public ArrayList<Color> listar(){
        ArrayList<Color> a = new ArrayList<Color>();
        ColorDBHelper dbHelper = new ColorDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_COLORES,null);

        try {
            if(cursor.moveToFirst()){
                do {
                    a.add(new Color(cursor.getInt(0),cursor.getInt(1)));
                }while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.toString();
        } finally {
            return a;
        }
    }

    /**
     * Actualizar (Update): Actualiza un registro de la base de datos, esto con el id del proceso
     * que se paso como parametro
     * @params Color color
     * @return boolean actualizado
     */
    public void actualizar(Color color){
        ColorDBHelper dbHelper = new ColorDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db != null){
            db.execSQL("UPDATE "+TABLE_COLORES+" SET valor = '"+color.getValor()+"'");
            db.close();
        }
    }

    /**
     * Borrar (Delete): Borra un registro de la base de datos, esto con el id que se le pase como
     * parametro
     * @params int idColor
     * @return boolean borrado
     */
    public void borrar(int idColor){
        ColorDBHelper dbHelper = new ColorDBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db != null){
            db.execSQL("DELETE FROM "+TABLE_COLORES+" WHERE id = '"+idColor+"'");
            db.close();
        }
    }
}
