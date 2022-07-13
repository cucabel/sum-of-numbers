package com.sum_of_numbers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DigitsStringFactoryShould {

	private LanguageStringFactory languageStringFactory;
	
	@Before
	public void setUp() {
		languageStringFactory = new DigitsStringFactory();
	}
	
	@Test
	public void create_a_digits_string() {
		String[] numbers = {"40", "+", "40"};
		
		LanguageString digitsString = languageStringFactory.createLanguageString(numbers);
		
		assertTrue(digitsString.getClass() == DigitsString.class);
	}
}
