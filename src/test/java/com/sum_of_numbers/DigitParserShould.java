package com.sum_of_numbers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DigitParserShould {
	
	private IParser parser;
	
	@Before
	public void setUp() {
		parser = new DigitsParser();
	}
	
	@Test
	public void pass_a_digit_string_to_a_digit() {
		Integer number = parser.getWordValue("2");
		
		assertTrue(2 == number);
	}
	
	@Test
	public void pass_a_digit_to_a_digit_string() {
		String number = parser.getLanguageNumber(2);
		
		assertEquals("2", number);
	}

}
