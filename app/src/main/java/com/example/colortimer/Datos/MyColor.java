package com.example.colortimer.Datos;

import android.graphics.Color;

public class MyColor {
    private int id;
    private int valor; // Valor real rgb, sin embargo las views no leen correctamente este valor (setBackgroundColor())
    private int valorView; // Diferente valor genera el mismo color rgb, pero este si es aceptado por las views

    public MyColor() {
        this.id = 0;
        this.valor = 0;
        updateValorView();
    }

    public MyColor(int valor){
        this.id = valor;
        this.valor = valor;
        updateValorView();
    }

    public MyColor(int id, int valor){
        this.id = id;
        this.valor = valor;
        updateValorView();
    }

    //Llamar este constructor solo si se calculo anteriormente el valorView Color.rgb(r, g, b)
    public MyColor(int id, int valor, int valorView) {
        this.id = id;
        this.valor = valor;
        this.valorView = valorView;
    }


    public MyColor(String hex) {
        int valor = convertirADecimal(hex);
        this.id = valor;
        this.valor = valor;
        updateValorView();
    }

    public int getId(){
        return id;
    }
    public int getValor() {
        return valor;
    }

    public String getValorHexadecimal() {
        return convertirAHexadecimal(this.valor);
    }

    public void setId(int id){
        this.id = id;
    }

    public void setValor(int valor) {
        this.valor = valor;
        updateValorView();
    }

    // Llamar esta función solo si ya se genero el valor aceptado por las Views con Color.rgb(r, g, b)
    public void setValorView(int valorView){
        this.valorView = valorView;
    }

    // Esta función esta destinada a actualizar el valorView cada que el valor(Real) cambie
    // Pues ante un cambio el valor real no podra ser leido por las views del sistema de manera visual
    public void updateValorView(){
        //Obtenemos rojo, verde y azul del valor real
        int r = Color.red(valor);
        int g = Color.green(valor);
        int b = Color.blue(valor);

        // Generamos el rgb aceptado por las Views (botones, editText, textViews, etc.)
        valorView = Color.rgb(r, g, b);
    }

    public void setRGB(int r, int g, int b){
        valor = (r << 16) + (g << 8) + b;
        valorView = Color.rgb(r, g, b);
    }

    public int getValorView(){
        return valorView;
    }

    public int getRed(){
        return Color.red(valor);
    }

    public int getGreen(){
        return Color.green(valor);
    }

    public int getBlue(){
        return Color.blue(valor);
    }

    public void setValorHexadecimal(String valor) {
        this.valor = convertirADecimal(valor);
        updateValorView();
    }

    /*
     * Regresa 1 si aun debe continuar el proceso
     * Regresa 0 si ya se alcanzo el resultado
     * Regresa -1 si ya se excedio la decoloración
     * */
    public int evaluarDiferencia(MyColor colorDeseado){

        // Resultado deseado - actual
        int difR = colorDeseado.getRed() - getRed();
        int difG = colorDeseado.getGreen() - getGreen();
        int difB = colorDeseado.getBlue() - getBlue();


        if((difR > 0) && (difG > 0) && (difB > 0)){
            return 1; // Aun no se ha igualado ningun componente rgb
        }

        if((difR == 0) || (difG == 0) || (difB == 0)){
            return 0; // Tonos considerablemente iguales
        }

        return -1; // Una o mas de las diferencias ya es negativa, misión fallida ;(

    }

    public static String convertirAHexadecimal(int decimal) {
        return Integer.toHexString(decimal);
    }

    public static int convertirADecimal(String hexa) {
        return Integer.parseInt(hexa, 16);
    }
}


/*
 * En términos generales, no se puede determinar qué color RGB es "mayor" o "menor" que otro, ya que
 * el color es una propiedad subjetiva de la percepción visual humana. Sin embargo, en algunos casos,
 * se pueden comparar dos colores RGB en función de sus valores numéricos.
 *
 * Cada componente RGB (rojo, verde y azul) tiene un rango de valores de 0 a 255, lo que significa
 * que el valor máximo para cada componente es 255. Por lo tanto, para comparar dos colores RGB, se
 * pueden comparar los valores numéricos de sus componentes.
 *
 * En términos generales, un color con componentes RGB más altos se considera más "brillante" o
 * "intenso" que un color con componentes más bajos. Por ejemplo, el color RGB (255, 0, 0) (rojo
 * puro) se considera más brillante que el color RGB (128, 0, 0) (rojo oscuro).
 *
 * Sin embargo, es importante tener en cuenta que la percepción visual de los colores puede ser
 * afectada por diversos factores, como la iluminación, el contexto y la percepción individual, por
 * lo que la comparación de colores basada en los valores numéricos de sus componentes RGB no siempre
 * reflejará la percepción visual real del color.
 * */