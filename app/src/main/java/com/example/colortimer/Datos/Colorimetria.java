package com.example.colortimer.Datos;

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
	 * 3 objetos de tipo color recibidos*/
	public Colorimetria(Color i, Color a, Color d) { 
		this.colorInicial = i;
		this.colorActual = a;
		this.colorDeseado = d;
	}
	
	/* Constructor parametrizado
	 * Con este constructor podemos inicializar cada color a partir de
	 * el valor decimal de cada uno*/
	public Colorimetria(int i, int a, int d) { 
		this.colorInicial = new Color(i);
		this.colorActual = new Color(a);
		this.colorDeseado = new Color(d);
	}
	
	/* Constructor parametrizado
	 * Con este constructor podemos inicializar cada color a partir de
	 * el valor decimal de cada uno*/
	public Colorimetria(String i, String a, String d) { 
		this.colorInicial = new Color(i);
		this.colorActual = new Color(a);
		this.colorDeseado = new Color(d);
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
