package com.example.colortimer.Datos;

public class Proceso {
    private int id;
    private int tiempoDecoloracion;
    private String estado;

    public Proceso(){
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
}
