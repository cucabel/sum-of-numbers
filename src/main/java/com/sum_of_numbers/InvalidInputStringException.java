package com.sum_of_numbers;

public class InvalidInputStringException extends Exception {
	
	public InvalidInputStringException() {}
	
	public InvalidInputStringException(String message) {
		super(message);
	}
}
