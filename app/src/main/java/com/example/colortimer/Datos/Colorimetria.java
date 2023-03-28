package com.example.colortimer.Datos;

import java.util.Random;
public class Colorimetria {
	private MyColor colorInicial;
	private MyColor colorActual;
	private MyColor colorDeseado;
	
	
	/* Constructor base
	 * Este constructor inicializa cada color con su constructor base*/
	public Colorimetria () { 
		this.colorInicial = new MyColor();
		this.colorActual = new MyColor();
		this.colorDeseado = new MyColor();
	}
	
	/* Constructor copia
	 * Con este constructor podemos inicializar cada color a partir de
	 * 3 objetos de tipo color recibidos
	 * @param String inicial	| Color con el que comenzo el proceso
	 * @param String actual		| Color actual del proceso
	 * @param String destino	| Color al que se espera llegar
	 */
	public Colorimetria(MyColor inicial, MyColor actual, MyColor deseado) {
		this.colorInicial = inicial;
		this.colorActual = actual;
		this.colorDeseado = deseado;
	}
	
	/* Constructor parametrizado
	 * Con este constructor podemos inicializar cada color a partir de
	 * el valor decimal de cada uno
	 * @param String inicial	| Color con el que comenzo el proceso
	 * @param String actual		| Color actual del proceso
	 * @param String destino	| Color al que se espera llegar
	 */
	public Colorimetria(int inicial, int actual, int destino) {
		this.colorInicial = new MyColor(inicial);
		this.colorActual = new MyColor(actual);
		this.colorDeseado = new MyColor(destino);
	}
	
	/* Constructor parametrizado
	 * Con este constructor podemos inicializar cada color a partir de
	 * el valor decimal de cada uno
	 * @param String inicial	| Color con el que comenzo el proceso
	 * @param String actual		| Color actual del proceso
	 * @param String destino	| Color al que se espera llegar
	 */
	public Colorimetria(String inicial, String actual, String destino) {
		this.colorInicial = new MyColor(inicial);
		this.colorActual = new MyColor(actual);
		this.colorDeseado = new MyColor(destino);
	}
	
	public MyColor getColorInicial() {
		return colorInicial;
	}
	
	public void setColorInicial(MyColor colorInicial) {
		this.colorInicial = colorInicial;
	}
	
	public MyColor getColorActual() {
		return colorActual;
	}
	
	public void setColorActual(MyColor colorActual) {
		this.colorActual = colorActual;
	}
	
	public MyColor getColorDeseado() {
		return colorDeseado;
	}
	
	public void setColorDeseado(MyColor colorDeseado) {
		this.colorDeseado = colorDeseado;
	}
	
	/*Regresa la diferencia entre el color actual y el color deseado*/
	public int calcularDiferencia() {
		return colorActual.evaluarDiferencia(colorDeseado);
	}

	public String obtenerEstado(){
		String estado = "";
		estado = "";
		int resultado = calcularDiferencia();
		if(resultado < 0){
			estado = "Cabello arruinado";
		}
		else{
			estado = "Continuar decoloración";
		}

		return estado;
	}
	
	/* Método estatico
	 * Regresa la diferencia entre los dos colores recibidos*/
	public static int calcularDiferencia(MyColor a, MyColor b) {
		return a.getValor() - b.getValor();
	}
	
	// Debe regresar un tinte
	public String hacerRecomendacion() {
		Random random = new Random();
		int randomInt = random.nextInt((100 - 1)+1) +1;
		return "Color: " + randomInt;
	}
	
}
