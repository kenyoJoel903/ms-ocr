package com.reto.ocr.util;

public enum Lenguaje {
	
	ESP("spa"),
	ENG("eng"),
	ITALIANO("ita");
	
	private String valor;
	
	private Lenguaje(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
	

}
