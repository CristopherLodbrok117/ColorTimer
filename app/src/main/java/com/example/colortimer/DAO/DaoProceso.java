package com.example.colortimer.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.colortimer.Datos.Proceso;

import java.util.ArrayList;
import java.util.List;

public class DaoProceso extends ProcesoDBHelper {

    private Context context;

    public DaoProceso(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    /**
     * Crear (Create): Crea un nuevo registro en la base de datos
     * @params Proceso proceso
     * @return long id
     */
    public int crear(Proceso proceso){
        int id = 0;
        try{
            ProcesoDBHelper dbHelper = new ProcesoDBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("tiempoDecoloracion",proceso.getTiempoDecoloracion());
            values.put("estado",proceso.getEstado());
            id = (int)db.insert(TABLE_PROCESOS+"2",null,values);
            Toast.makeText(this.context,"No Error",Toast.LENGTH_LONG).show();
        } catch (SQLException e){
            Toast.makeText(this.context,"Error",Toast.LENGTH_LONG).show();
            e.toString();
        } finally {
            return id;
        }
    }

    /**
     * Buscar (Read): Devuelve un solo registro, el que tenga el id que se paso
     * @params int idProceso
     * @return Proceso p
     */
    public Proceso buscar(int idProceso){
        Proceso p = new Proceso();

        // Aqui ingresar el SQL para buscar 1 solo registro, con el id que se pasa como parametro

        return p;
    }

    /**
     * Listar (Read): Devuelve todos los registros de procesos
     * @return ArrayList<Proceso> a
     */
    public ArrayList<Proceso> listar(){
        ArrayList<Proceso> a = new ArrayList<Proceso>();

        ProcesoDBHelper dbHelper = new ProcesoDBHelper(this.context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columnas = new String[3];
        columnas[0] = "id";
        columnas[1] = "tiempoDecoloracion";
        columnas[2] = "estado";

        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_PROCESOS,null);
        if(cursor.moveToFirst()){
            do {
                a.add(new Proceso(cursor.getInt(0),cursor.getInt(1),cursor.getString(0)));
            }while(cursor.moveToNext());
        }

        return a;
    }

    /**
     * Actualizar (Update): Actualiza un registro de la base de datos, esto con el id del proceso
     * que se paso como parametro
     * @params Proceso proceso
     * @return boolean actualizado
     */
    public boolean actualizar(Proceso proceso){
        // Aqui agregar el SQL para actualizar un registro
        return true;
    }

    /**
     * Borrar (Delete): Borra un registro de la base de datos, esto con el id que se le pase como
     * parametro
     * @params int idProceso
     * @return boolean borrado
     */
    public boolean borrar(int idProceso){
        // Aqui agregar el SQL para eliminar un registro
        return true;
    }
}
