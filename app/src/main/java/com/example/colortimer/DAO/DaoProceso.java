package com.example.colortimer.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.colortimer.DAO.helpers.ProcesoDBHelper;
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
    public long crear(Proceso proceso){
        long id = -1;
        try{
            ProcesoDBHelper dbHelper = new ProcesoDBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put("nombre", proceso.getNombreCliente());
            values.put("tiempoDecoloracion",proceso.getTiempoDecoloracion());
            values.put("estado",proceso.getEstado());
            values.put("inicial", proceso.getInicial().getValor());
            values.put("actual", proceso.getActual().getValor());
            values.put("deseado", proceso.getDeseado().getValor());
            id = db.insert(TABLE_PROCESOS,null,values);
            db.close();
        } catch (SQLException e){
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
        Proceso p = null;
        ArrayList<Proceso> listado = this.listar();

        for(int i = 0; i < listado.size(); i++){
            if(listado.get(i).getId() == idProceso){
                p = new Proceso(listado.get(i));
                break;
            }
        }

        return p;
    }

    public List<Proceso> listarProcesosActivos() {
        List<Proceso> procesos = this.listar();
        List<Proceso> procesosActivos = new ArrayList<>();

        for(Proceso proceso : procesos){
            if(proceso.getEstado().compareTo("A") == 0){
                Proceso p = new Proceso(proceso);
                procesosActivos.add(p);
            }
        }

        return procesosActivos;

    }

    /**
     * Listar (Read): Devuelve todos los registros de procesos
     * @return ArrayList<Proceso> a
     */
    public ArrayList<Proceso> listar(){
        ArrayList<Proceso> a = new ArrayList<>();
        ProcesoDBHelper dbHelper = new ProcesoDBHelper(this.context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_PROCESOS,null);

        try{
            if(cursor.moveToFirst()){
                do {
                    a.add(new Proceso(cursor.getInt(0),
                            cursor.getString(1),
                            cursor.getInt(2),
                            cursor.getString(3),
                            cursor.getInt(4),
                            cursor.getInt(5),
                            cursor.getInt(6)
                            ));
                }while(cursor.moveToNext());
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
     * @params Proceso proceso
     * @return boolean actualizado
     */
    public void actualizar(Proceso proceso){
        ProcesoDBHelper dbHelper = new ProcesoDBHelper(this.context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db != null){
            db.execSQL("UPDATE "+TABLE_PROCESOS+" SET nombre='" + proceso.getNombreCliente() +"', "+
                    "tiempoDecoloracion='" + proceso.getTiempoDecoloracion()+ "', " +
                    "estado='"+proceso.getEstado()+ "'," +
                    "inicial='" + proceso.getInicial().getValor() + "', "+
                    "actual='" + proceso.getActual().getValor() +  "', "+
                    "deseado='" + proceso.getDeseado().getValor() +  "'" +
                    " WHERE id=" + proceso.getId());
            db.close();
        }
    }

    /**
     * Borrar (Delete): Borra un registro de la base de datos, esto con el id que se le pase como
     * parametro
     * @params int idProceso
     * @return boolean borrado
     */
    public void borrar(int idProceso){
        ProcesoDBHelper dbHelper = new ProcesoDBHelper(this.context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        if(db != null){
            db.execSQL("DELETE FROM "+TABLE_PROCESOS+" WHERE id='"+idProceso+"'");
            db.close();
            Toast.makeText(context, "Borrando: "+idProceso, Toast.LENGTH_SHORT).show();
        }
    }
}
