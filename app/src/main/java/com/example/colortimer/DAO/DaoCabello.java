package com.example.colortimer.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.colortimer.DAO.helpers.CabelloDBHelper;
import com.example.colortimer.Datos.Cabello;

import java.util.ArrayList;

public class DaoCabello extends CabelloDBHelper{
    private Context context;
    public DaoCabello(@Nullable Context context){
        super(context);
        this.context = context;
    }

    /**
     * Crear (Create): Crea un nuevo registro en la base de datos
     * @params Cabello cabello
     * @return boolean creado
     */
    public long crear(Cabello cabello){
        long id = -1;

        try {
            CabelloDBHelper dbHelper = new CabelloDBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("estado",cabello.getEstado());
            values.put("tipo",cabello.getTipo());
            values.put("idproceso",cabello.getProceso());
            values.put("idcolor",cabello.getColor());
            id = db.insert(TABLE_CABELLOS,null,values);
            db.close();
        } catch (Exception e){
            e.toString();
        } finally {
            return id;
        }
    }

    /**
     * Buscar (Read): Devuelve un solo registro, el que tenga el id que se paso
     * @params int idCabello
     * @return Cabello c
     */
    public Cabello buscar(int idCabello){
        Cabello c = new Cabello();
        ArrayList<Cabello> listado = this.listar();

        for(int i = 0; i < listado.size(); i++){
            if(listado.get(i).getId() == idCabello){
                c = listado.get(i);
                break;
            }
        }

        return c;
    }

    /**
     * Listar (Read): Devuelve todos los registros de procesos
     * @return ArrayList<Cabello> a
     */
    public ArrayList<Cabello> listar(){
        ArrayList<Cabello> a = new ArrayList<Cabello>();
        CabelloDBHelper dbHelper = new CabelloDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_CABELLOS,null);

        try {
            if(cursor.moveToFirst()){
                do {
                    a.add(new Cabello(cursor.getInt(0),cursor.getString(1),
                            cursor.getString(2),cursor.getInt(3),cursor.getInt(4)));
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
     * @params Cabello cabello
     * @return boolean actualizado
     */
    public void actualizar(Cabello cabello){
        CabelloDBHelper dbHelper = new CabelloDBHelper(context);
    }

    /**
     * Borrar (Delete): Borra un registro de la base de datos, esto con el id que se le pase como
     * parametro
     * @params int idCabello
     * @return boolean borrado
     */
    public boolean borrar(int idCabello){
        // Aqui agregar el SQL para eliminar un registro
        return true;
    }
}
