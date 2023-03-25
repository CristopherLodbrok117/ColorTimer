package com.example.colortimer.Datos;

public class Tinte {
    private int id;
    private String marca;

    public Tinte(){
        this(0,"");
    }
    public Tinte(int id,String marca){
        this.id = id;
        this.marca = marca;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }

    public int getId(){
        return id;
    }
    public String getMarca(){
        return  marca;
    }
}
