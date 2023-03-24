package com.example.colortimer.Datos;

public class Color {
	private int valor;

	public Color() {
		this.valor = 0;
	}
	
	public Color(int valor) {
		this.valor = valor;
	}
	
	public Color(String valor) {
		setValorHexadecimal(valor);
	}
	
	public int getValor() {
		return valor;
	}
	
	public String getValorHexadecimal() {
		return convertirAHexadecimal(this.valor);
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public void setValorHexadecimal(String valor) {
		this.valor = convertirADecimal(valor);
	}
	
	public static String convertirAHexadecimal(int decimal) {
		return Integer.toHexString(decimal);
	}
	
	public static int convertirADecimal(String hexa) {
		return Integer.parseInt(hexa, 16);
	}
	
	
}
