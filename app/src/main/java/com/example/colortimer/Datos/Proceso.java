package com.example.colortimer.Datos;

import androidx.annotation.NonNull;

public class Proceso {
    private int id;
    private int tiempoDecoloracion;
    private String estado;

    public Proceso(){
        this(0,0,"A");
    }
    public Proceso(int id,int tiempoDecoloracion,String estado){
        this.id = id;
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

    @NonNull
    @Override
    public String toString() {
        String s = "";
        s += "ID: "+id+"\n";
        s += "TD: "+tiempoDecoloracion+"\n";
        s += "Estado: "+estado+"\n";
        return s;
    }
}
