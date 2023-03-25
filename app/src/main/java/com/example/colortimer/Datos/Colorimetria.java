package com.example.colortimer.Datos;

import com.example.colortimer.DAO.DaoColor;
import com.example.colortimer.MainActivity;

import java.util.Random;
public class Colorimetria {
	private Color colorInicial;
	private Color colorActual;
	private Color colorDeseado;
	
	
	/* Constructor base
	 * Este constructor inicializa cada color con su constructor base*/
	public Colorimetria () { 
		this.colorInicial = new Color();
		this.colorActual = new Color();
		this.colorDeseado = new Color();
	}
	
	/* Constructor copia
	 * Con este constructor podemos inicializar cada color a partir de
	 * 3 objetos de tipo color recibidos
	 * @param String inicial	| Color con el que comenzo el proceso
	 * @param String actual		| Color actual del proceso
	 * @param String destino	| Color al que se espera llegar
	 */
	public Colorimetria(Color inicial, Color actual, Color destino) {
		this.colorInicial = inicial;
		this.colorActual = actual;
		this.colorDeseado = destino;
	}
	
	/* Constructor parametrizado
	 * Con este constructor podemos inicializar cada color a partir de
	 * el valor decimal de cada uno
	 * @param String inicial	| Color con el que comenzo el proceso
	 * @param String actual		| Color actual del proceso
	 * @param String destino	| Color al que se espera llegar
	 */
	public Colorimetria(int inicial, int actual, int destino) {
		this.colorInicial = new Color(inicial);
		this.colorActual = new Color(actual);
		this.colorDeseado = new Color(destino);
	}
	
	/* Constructor parametrizado
	 * Con este constructor podemos inicializar cada color a partir de
	 * el valor decimal de cada uno
	 * @param String inicial	| Color con el que comenzo el proceso
	 * @param String actual		| Color actual del proceso
	 * @param String destino	| Color al que se espera llegar
	 */
	public Colorimetria(String inicial, String actual, String destino) {
		this.colorInicial = new Color(inicial);
		this.colorActual = new Color(actual);
		this.colorDeseado = new Color(destino);
	}
	
	public Color getColorInicial() {
		return colorInicial;
	}
	
	public void setColorInicial(Color colorInicial) {
		this.colorInicial = colorInicial;
	}
	
	public Color getColorActual() {
		return colorActual;
	}
	
	public void setColorActual(Color colorActual) {
		this.colorActual = colorActual;
	}
	
	public Color getColorDeseado() {
		return colorDeseado;
	}
	
	public void setColorDeseado(Color colorDeseado) {
		this.colorDeseado = colorDeseado;
	}
	
	/*Regresa la diferencia entre el color actual y el color deseado*/
	public int calcularDiferencia() {
		return colorDeseado.getValor() - colorActual.getValor();
	}
	
	/* Método estatico
	 * Regresa la diferencia entre los dos colores recibidos*/
	public static int calcularDiferencia(Color a, Color b) {
		return a.getValor() - b.getValor();
	}
	
	// Debe regresar un tinte
	public String hacerRecomendacion() {
		Random random = new Random();
		int randomInt = random.nextInt((100 - 1)+1) +1;
		return "Color: " + randomInt;
	}
	
}
