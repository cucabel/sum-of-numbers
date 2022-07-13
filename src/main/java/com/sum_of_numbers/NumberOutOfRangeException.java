package com.sum_of_numbers;

public class NumberOutOfRangeException extends Exception {
	
	public boolean outOfRange;

	public NumberOutOfRangeException() {
		super();
	}

	public NumberOutOfRangeException(String message) {
		super(message);
	}
	
	

}
