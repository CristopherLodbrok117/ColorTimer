package com.example.colortimer.DAO;

import com.example.colortimer.Datos.Tinte;

import java.util.ArrayList;

public class DaoTinte {

    public DaoTinte(){
        // Aqui deberemos hacer la conexion a la base de datos
    }

    /**
     * Crear (Create): Crea un nuevo registro en la base de datos
     * @param Tinte tinte
     * @return boolean creado
     */
    public boolean crear(Tinte tinte){
        // Aqui ingresar el SQL para crear el registro del Proceso en la base de datos
        return true;
    }

    /**
     * Buscar (Read): Devuelve un solo registro, el que tenga el id que se paso
     * @param int idTinte
     * @return Tinte t
     */
    public Tinte buscar(int idTinte){
        Tinte t = new Tinte();

        // Aqui ingresar el SQL para buscar 1 solo registro, con el id que se pasa como parametro

        return t;
    }

    /**
     * Listar (Read): Devuelve todos los registros de procesos
     * @return ArrayList<Tinte> a
     */
    public ArrayList<Tinte> listar(){
        ArrayList<Tinte> a = new ArrayList<Tinte>();

        // Aqui ingresar el SQL para sacar todos los registros de la base de datos

        return a;
    }

    /**
     * Actualizar (Update): Actualiza un registro de la base de datos, esto con el id del proceso
     * que se paso como parametro
     * @param Tinte tinte
     * @return boolean actualizado
     */
    public boolean actualizar(Tinte tinte){
        // Aqui agregar el SQL para actualizar un registro
        return true;
    }

    /**
     * Borrar (Delete): Borra un registro de la base de datos, esto con el id que se le pase como
     * parametro
     * @param int idTinte
     * @return boolean borrado
     */
    public boolean borrar(int idTinte){
        // Aqui agregar el SQL para eliminar un registro
        return true;
    }
}
