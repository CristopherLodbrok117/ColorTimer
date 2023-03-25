package com.example.colortimer.Datos;

public class Cabello {
    private int id;
    private String tipo;
    private String estado;
    private int proceso;
    private int color;

    public Cabello(){
        this(0,"A","A",0,0);
    }
    public Cabello(int id,String tipo,String estado,int proceso,int color){
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.proceso = proceso;
        this.color = color;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setProceso(int proceso){
        this.proceso = proceso;
    }
    public void setColor(int color){
        this.color = color;
    }

    public int getId() {
        return id;
    }
    public String getTipo() {
        return tipo;
    }
    public String getEstado() {
        return estado;
    }
    public int getProceso(){
        return proceso;
    }
    public int getColor(){
        return color;
    }
}
