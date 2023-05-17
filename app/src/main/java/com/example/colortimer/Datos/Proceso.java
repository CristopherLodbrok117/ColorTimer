package com.example.colortimer.Datos;

public class Proceso {
    private int id;
    private String nombreCliente;
    private int tiempoDecoloracion;
    private String estado;



    public static final String ESTADO_ACTIVO = "A";
    public static final String ESTADO_INACTIVO = "I";

    public Proceso(){
        this(0,"", 0, ESTADO_ACTIVO);
    }
    public Proceso(int id, String nombreCliente, int tiempoDecoloracion, String estado){
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.tiempoDecoloracion = tiempoDecoloracion;
        this.estado = estado;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setTiempoDecoloracion(int tiempoDecoloracion){
        this.tiempoDecoloracion = tiempoDecoloracion;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }

    public int getId(){
        return id;
    }
    public int getTiempoDecoloracion(){
        return tiempoDecoloracion;
    }
    public String getEstado(){
        return estado;
    }

    public String getNombreCliente() {return this.nombreCliente; }

    public void setNombreCliente(String nombre){
        this.nombreCliente = nombre;
    }

    @Override
    public String toString() {
        String s = "";
        s += "ID: "+id+"\n";
        s += "Cliente: "+ this.nombreCliente + "\n";
        s += "TD: "+tiempoDecoloracion+"\n";
        s += "Estado: "+estado+"\n";
        return s;
    }
}
