package com.example.colortimer.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.colortimer.Datos.Proceso;

import java.util.ArrayList;

public class DaoProceso extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "ColorTimer.db";
    private static final String TABLE_PROCESOS = "Contactos";

    public DaoProceso(@Nullable Context context) {
        // Aqui deberemos hacer la conexion a la base de datos
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE "+TABLE_PROCESOS+"(" +
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

    /**
     * Crear (Create): Crea un nuevo registro en la base de datos
     * @param Proceso proceso
     * @return boolean creado
     */
    public boolean crear(Proceso proceso){
        // Aqui ingresar el SQL para crear el registro del Proceso en la base de datos
    }

    /**
     * Buscar (Read): Devuelve un solo registro, el que tenga el id que se paso
     * @param int idProceso
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

        // Aqui ingresar el SQL para sacar todos los registros de la base de datos

        return a;
    }

    /**
     * Actualizar (Update): Actualiza un registro de la base de datos, esto con el id del proceso
     * que se paso como parametro
     * @param Proceso proceso
     * @return boolean actualizado
     */
    public boolean actualizar(Proceso proceso){
        // Aqui agregar el SQL para actualizar un registro
    }

    /**
     * Borrar (Delete): Borra un registro de la base de datos, esto con el id que se le pase como
     * parametro
     * @param int idProceso
     * @return boolean borrado
     */
    public boolean borrar(int idProceso){
        // Aqui agregar el SQL para eliminar un registro
    }
}
