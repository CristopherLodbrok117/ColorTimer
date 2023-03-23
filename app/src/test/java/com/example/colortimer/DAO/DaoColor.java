package com.example.colortimer.DAO;

import com.example.colortimer.Datos.Color;

import java.util.ArrayList;

public class DaoColor {
    public DaoColor(){
        // Aqui deberemos hacer la conexion a la base de datos
    }

    /**
     * Crear (Create): Crea un nuevo registro en la base de datos
     * @param Color color
     * @return boolean creado
     */
    public boolean crear(Color color){
        // Aqui ingresar el SQL para crear el registro del Proceso en la base de datos
    }

    /**
     * Buscar (Read): Devuelve un solo registro, el que tenga el id que se paso
     * @param int idColor
     * @return Color c
     */
    public Color buscar(int idColor){
        Color c = new Color();

        // Aqui ingresar el SQL para buscar 1 solo registro, con el id que se pasa como parametro

        return c;
    }

    /**
     * Listar (Read): Devuelve todos los registros de procesos
     * @return ArrayList<Color> a
     */
    public ArrayList<Color> listar(){
        ArrayList<Color> a = new ArrayList<Color>();

        // Aqui ingresar el SQL para sacar todos los registros de la base de datos

        return a;
    }

    /**
     * Actualizar (Update): Actualiza un registro de la base de datos, esto con el id del proceso
     * que se paso como parametro
     * @param Color color
     * @return boolean actualizado
     */
    public boolean actualizar(Color color){
        // Aqui agregar el SQL para actualizar un registro
    }

    /**
     * Borrar (Delete): Borra un registro de la base de datos, esto con el id que se le pase como
     * parametro
     * @param int idColor
     * @return boolean borrado
     */
    public boolean borrar(int idColor){
        // Aqui agregar el SQL para eliminar un registro
    }
}
