package com.example.colortimer.Datos;

public class Proceso {
    private int id;
    private String nombreCliente;
    private int tiempoDecoloracion;
    private String estado;

    private MyColor inicial;
    private MyColor actual;
    private MyColor deseado;



    public static final String ESTADO_PREPARANDO = "P";
    public static final String ESTADO_ACTIVO = "A";
    public static final String ESTADO_TERMINADO = "T";
    public static final String ESTADO_ARRUINADO = "X";


    public Proceso(){
        this(0,"", 0, ESTADO_ACTIVO, 0, 0, 0);
    }
    public Proceso(int id, String nombreCliente, int tiempoDecoloracion, String estado,
                   int inicial, int actual, int deseado){
        this.id = id;
        this.nombreCliente = nombreCliente;
        this.tiempoDecoloracion = tiempoDecoloracion;
        this.estado = estado;

        this.inicial = new MyColor(inicial);
        this.actual = new MyColor(actual);;
        this.deseado = new MyColor(deseado);;
    }

    public Proceso(Proceso p) {
        this.id = p.getId();
        this.nombreCliente = p.getNombreCliente();
        this.tiempoDecoloracion = p.getTiempoDecoloracion();
        this.estado = p.getEstado();

        this.inicial = new MyColor(p.getInicial().getValor());
        this.actual = new MyColor(p.getActual().getValor());;
        this.deseado = new MyColor(p.getDeseado().getValor());;
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

    public MyColor getInicial() {
        return inicial;
    }

    public void setInicial(int inicial) {
        this.inicial.setValor(inicial);
    }

    public MyColor getActual() {
        return actual;
    }

    public void setActual(int actual) {
        this.actual.setValor(actual);
    }

    public MyColor getDeseado() {
        return deseado;
    }

    public void setDeseado(int deseado) {
        this.deseado.setValor(deseado);
    }

    @Override
    public String toString() {
        String s = "";
        s += "ID: "+id+"\n";
        s += "Cliente: "+ this.nombreCliente + "\n";
        s += "TD: "+tiempoDecoloracion+"\n";
        s += "Estado: "+estado+"\n";
        s +=  "Inicial" + inicial.getValor() + "\n";
        s +=  "Actual" + actual.getValor() + "\n";
        s +=  "Final" + deseado.getValor() + "\n";
        return s;
    }
}
