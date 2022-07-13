package com.sum_of_numbers;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class LanguageStringShould {
	
	private LanguageString languageString;
	
	@Test
	public void return_the_result_of_the_digits_string_sum() {
		String[] numbers = {"5", "+", "5"};
		languageString = new DigitsString(numbers);
		
		String actual = languageString.sum();
		
		assertEquals("10", actual);
	}
	
	@Test
	public void return_the_result_of_the_spanish_string_sum() {
		String[] numbers = {"cinco", "mas", "cinco"};
		languageString = new SpanishString(numbers);
		
		String actual = languageString.sum();
		
		assertEquals("diez", actual);
	}
	
	@Test
	public void return_the_result_of_the_english_string_sum() {
		String[] numbers = {"five", "plus", "five"};
		languageString = new EnglishString(numbers);
		
		String actual = languageString.sum();
		
		assertEquals("ten", actual);
	}
	
	@Test
	public void return_the_result_of_the_digits_sum() {
		String[] numbers = {"5", "+", "5"};
		languageString = new DigitsString(numbers);
		List<Integer> digits = Arrays.asList(5, 5);
		
		int actual = languageString.sum(digits);
		
		assertEquals(10, actual);
	}

}
