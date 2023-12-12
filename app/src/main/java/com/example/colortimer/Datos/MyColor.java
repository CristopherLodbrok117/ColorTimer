package com.example.colortimer.Datos;

import android.animation.ArgbEvaluator;
import android.graphics.Color;

public class MyColor {

    public static int RESULT_CONTINUE = 0;
    public static int RESULT_STOP_SUGGESTION = 1;
    public static int RESULT_STOP_IMMEDIATELY = 2;

    public static int RANGE_LIMIT = 30;
    public static double DELTA_LIMIT = 20;
    private int id;
    private int valor; // Valor real rgb, sin embargo las views no leen correctamente este valor (setBackgroundColor())
    private int valorView; // Diferente valor genera el mismo color rgb, pero este si es aceptado por las views

    public int RGB = 1;
    public int RGB_LUMA = 1;
    public int RGB_BRIGHT = 1;
    public int RGB_LUMINOSITY = 1;
    public int RGB_GREYSCALE = 1;

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
        this.id = this.valor;
        updateValorView();
    }

    /*
     * Regresa 1 si aun debe continuar el proceso
     * Regresa 0 si ya se alcanzo el resultado
     * Regresa -1 si ya se excedio la decoloración
     * */
    public int evaluarDiferencia(MyColor cd){

        final int RGB_RANGO = 215;

        int coincidencia = 0;
        coincidencia += evaluarBrillo(cd);
        coincidencia += evaluarLuma(cd);
        coincidencia += evaluarLuminosidad(cd);
        coincidencia += evaluarGreysCale(cd);

        if((coincidencia > 0) && getRed() >= RGB_RANGO && getGreen() >= RGB_RANGO && getBlue() >= RGB_RANGO){
            return 1;
        }
        else {
            return -1;
        }
    }

    /*  Este método calcula la diferencia de los componentes rgb del color actual y el deseado,
    * si el color actual entra en el rango del color deseado llamara al método euclideanDistance
    * para evaluar la distancia entre ambos colores,
    * caso contrario el resultado sera continuar el proceso   */
    public int evaluarRango(MyColor desiredColor) {
        int redDiff = Math.abs(this.getRed() - desiredColor.getRed());
        int greenDiff = Math.abs(this.getGreen() - desiredColor.getGreen());
        int blueDiff  = Math.abs(this.getBlue() - desiredColor.getBlue());

        if(redDiff <= RANGE_LIMIT && greenDiff <= RANGE_LIMIT && blueDiff <= RANGE_LIMIT) {
            return euclideanDistance(redDiff, greenDiff, blueDiff);
        }
        return MyColor.RESULT_CONTINUE;
    }


    /* Este método recibe la diferencia entre los componentes rgb del color actual y el color
    * deseado. A partir de la raiz cuadrada de la sumatoria de sus valores al cuadrado se obtiene
    * la distancia/delta-e entre ambos colores. Si el delta se encuentra dentro del límite estable-
    * cido devuelve "Detener proceso automaticamente", caso contrario devuelve "Sugerencia para
    * detener manualmente"
    * Este método solo debe ser llamado si el color actual y deseado estan dentro del rango estable-
    * cido*/
    public int euclideanDistance(int redDiff, int greenDiff, int blueDiff) {
        final int SQUARE_EXP = 2;
        double deltaE = Math.sqrt((double)Math.pow(redDiff, SQUARE_EXP)
                + Math.pow(greenDiff, SQUARE_EXP)
                + Math.pow(blueDiff, SQUARE_EXP));

        if(deltaE <= DELTA_LIMIT){
            return MyColor.RESULT_STOP_IMMEDIATELY; // Cabello arruinado
        }
        return MyColor.RESULT_STOP_SUGGESTION; // Candidato a detenerse (suficientemente cerca)
    }

    public String evaluarDiferenciaStr(MyColor colorDeseado){
        final int DIFERENCIA_ACEPTABLE = 110;
        float fraction= .75f;
        Object str = new ArgbEvaluator().evaluate(fraction, valorView, colorDeseado.getValorView());


        return str.toString();
    }

    public int evaluarLuma(MyColor cd){
        final int DIFERENCIA_ACEPTABLE = 10;
        double luma1 = (0.2126 * getRed()) + (0.7152 * getGreen()) + (0.0722 * getBlue());
        double luma2 = (0.2126 * cd.getRed()) + (0.7152 * cd.getGreen()) + (0.0722 * cd.getBlue());

        double result = ((int)luma1 - (int)luma2) - 255;

        if(result >= -DIFERENCIA_ACEPTABLE && result <= DIFERENCIA_ACEPTABLE){
            return 1;
        }
        else{
            return RGB_LUMA;
        }
    }

    public int evaluarLuminosidad(MyColor cd){
        int r = (valor >> 16) & 0xff;
        int g = (valor >> 8) & 0xff;
        int b = valor & 0xff;
        int luz1 = (r + g + b) / 3;

        int r2 = (cd.getValor() >> 16) & 0xff;
        int g2 = (cd.getValor() >> 8) & 0xff;
        int b2 = cd.getValor() & 0xff;
        int luz2 = (r2 + g2 + b2) / 3;

        int cantidadLuz = luz1 + luz2;

        if(cantidadLuz > 0){
            return cantidadLuz;
        }

        return RGB_LUMINOSITY;
    }

    public int evaluarGreysCale(MyColor cd){
        int pixel1 = (getRed() + getGreen() + getBlue()) / 3;
        int pixel2 = (cd.getRed() + cd.getGreen() + cd.getBlue()) / 3;

        MyColor gris1 = new MyColor();
        MyColor gris2 = new MyColor();
        int g1 = (Color.red(pixel1) << 16) + (Color.green(pixel1) << 8) + Color.blue(pixel1);
        int g2 = (Color.red(pixel2) << 16) + (Color.green(pixel2) << 8) + Color.blue(pixel2);

        gris1.setValor(g1);
        gris2.setValor(g2);

        int color1 = (gris1.getRed() + gris1.getGreen() + gris1.getBlue() );
        int color2 = (gris2.getRed() + gris2.getGreen() + gris2.getBlue() );

        return RGB_GREYSCALE;
    }

    public int evaluarBrillo(MyColor cd){
        double brillo1 = Math.sqrt( (0.299*(Math.pow(getRed(), 2))) + (0.587*(Math.pow(getGreen(), 2))) +
                (0.114*(Math.pow(getBlue(), 2))) );
        double brillo2 = Math.sqrt( (0.299*(Math.pow(cd.getRed(), 2))) + (0.587*(Math.pow(cd.getGreen(), 2))) +
                (0.114*(Math.pow(cd.getBlue(), 2))) );

        double diff = brillo1 - brillo2;
        int diferencia = (int)diff - 255;
        if(diferencia > 0){
            return diferencia;
        }
        else{
            return RGB_BRIGHT;
        }
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