package com.example.colortimer.Datos;

public class Color {
	private int id;
	private int valor;

	public Color() {
		this(0,0);
	}
	
	public Color(int id, int valor) {
		this.id = id;
		this.valor = valor;
	}

	public Color(int valor){
		this(0,valor);
	}
	
	public Color(String valor) {
		setValorHexadecimal(valor);
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
