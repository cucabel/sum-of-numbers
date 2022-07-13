package com.sum_of_numbers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DigitsSumShould {
	
	private ISumNumbers digitsSum;
	
	@Before
	public void setUp() {
		digitsSum = new DigitsSum();
	}
	
	@Test
	public void return_the_result_of_the_sum() {
		String[] numbers = {"5", "+", "100"};
		LanguageString digitsString = new DigitsString(numbers);
		
		String digitsNumber = digitsSum.sum(digitsString);
		
		assertEquals("105", digitsNumber);

	}
	
}
