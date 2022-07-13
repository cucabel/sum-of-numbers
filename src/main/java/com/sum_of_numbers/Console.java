package com.sum_of_numbers;

import java.util.Scanner;

public class Console {
//	this variable is not needed, I use it for testing purposes
	private String output;

	public String scanInput() {
		Scanner scanner = createScanner();
		System.out.println("Introduce the numbers to sum");
		String input = scanner.nextLine();
		return input;
	}

	public void printResult(String output) {
		this.output = output;
		System.out.println(output);
	}
	
	public Scanner createScanner() {
		return new Scanner(System.in);
	}

	public String getOutput() {
		return output;
	}

}
