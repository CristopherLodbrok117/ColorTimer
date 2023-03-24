package com.example.colortimer.DAO;

import com.example.colortimer.Datos.Cabello;

import java.util.ArrayList;

public class DaoCabello {
    public DaoCabello(){
        // Aqui deberemos hacer la conexion a la base de datos
    }

    /**
     * Crear (Create): Crea un nuevo registro en la base de datos
     * @param Cabello cabello
     * @return boolean creado
     */
    public boolean crear(Cabello cabello){
        // Aqui ingresar el SQL para crear el registro del Proceso en la base de datos
        return true;
    }

    /**
     * Buscar (Read): Devuelve un solo registro, el que tenga el id que se paso
     * @param int idCabello
     * @return Cabello c
     */
    public Cabello buscar(int idCabello){
        Cabello c = new Cabello();

        // Aqui ingresar el SQL para buscar 1 solo registro, con el id que se pasa como parametro

        return c;
    }

    /**
     * Listar (Read): Devuelve todos los registros de procesos
     * @return ArrayList<Cabello> a
     */
    public ArrayList<Cabello> listar(){
        ArrayList<Cabello> a = new ArrayList<Cabello>();

        // Aqui ingresar el SQL para sacar todos los registros de la base de datos

        return a;
    }

    /**
     * Actualizar (Update): Actualiza un registro de la base de datos, esto con el id del proceso
     * que se paso como parametro
     * @param Cabello cabello
     * @return boolean actualizado
     */
    public boolean actualizar(Cabello cabello){
        // Aqui agregar el SQL para actualizar un registro
        return true;
    }

    /**
     * Borrar (Delete): Borra un registro de la base de datos, esto con el id que se le pase como
     * parametro
     * @param int idCabello
     * @return boolean borrado
     */
    public boolean borrar(int idCabello){
        // Aqui agregar el SQL para eliminar un registro
        return true;
    }
}
