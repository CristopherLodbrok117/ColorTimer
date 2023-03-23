package com.example.colortimer.DAO;

import com.example.colortimer.Datos.Proceso;

import java.util.ArrayList;

public class DaoProceso {

    public DaoProceso(){
        // Aqui deberemos hacer la conexion a la base de datos
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
