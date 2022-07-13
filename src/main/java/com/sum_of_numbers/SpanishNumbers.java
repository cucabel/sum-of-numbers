package com.sum_of_numbers;
// Spanish numbers between 0-999 
public enum SpanishNumbers {
	CERO(0),
	UNO(1),
	DOS(2),
	TRES(3),
	CUATRO(4),
	CINCO(5),
	SEIS(6),
	SIETE(7),
	OCHO(8),
	NUEVE(9),
	DIEZ(10),
	ONCE(11),
	DOCE(12),
	TRECE(13),
	CATORCE(14),
	QUINCE(15),
	DIECISEIS(16),
	DIECISIETE(17),
	DIECIOCHO(18),
	DIECINUEVE(19),
	VEINTE(20),
	VEINTIUNO(21),
	VEINTIDOS(22), 
	VEINTITRES(23),
	VEINTICUATRO(24),
	VEINTICINCO(25),
	VEINTISEIS(26),
	VEINTISIETE(27),
	VEINTIOCHO(28),
	VEINTINUEVE(29),
	TREINTA(30),
	CUARENTA(40),
	CINCUENTA(50),
	SESENTA(60),
	SETENTA(70),
	OCHENTA(80),
	NOVENTA(90),
	CIEN(100),
	CIENTO(100),
	DOSCIENTOS(200),
	TRESCIENTOS(300),
	CUATROCIENTOS(400),
	QUINIENTOS(500),
	SEISCIENTOS(600),
	SETECIENTOS(700),
	OCHOCIENTOS(800),
	NOVECIENTOS(900),
	;
	
	private final int number;

	private SpanishNumbers(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
}
