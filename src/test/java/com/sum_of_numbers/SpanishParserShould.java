package com.sum_of_numbers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SpanishParserShould {
	
	private IParser parser;
	private SpanishParser spanishParser;
	
	@Before
	public void setUp() {
		parser = new SpanishParser();
		spanishParser = new SpanishParser();
	}
	
	@Test
	public void pass_the_number_in_spanish_to_digits() {
		int digitNumber = parser.getWordValue("cien");
		
		assertEquals(100, digitNumber);
	}
	
	@Test
	public void pass_the_number_in_digits_to_spanish() {
		String spanishNumber = parser.getLanguageNumber(100);
		String spanishNumber0 = parser.getLanguageNumber(400);
		String spanishNumber1 = parser.getLanguageNumber(145);
		
		assertEquals("cien", spanishNumber);
		assertEquals("cuatrocientos", spanishNumber0);
		assertEquals("ciento cuarenta y cinco", spanishNumber1);
	}
	
	
	@Test
	public void pass_a_ten_in_digits_to_spanish() {
		String spanishNumber = spanishParser.getCombinedLanguageString(29);
		String spanishNumber0 = spanishParser.getCombinedLanguageString(45);
		String spanishNumber1 = spanishParser.getCombinedLanguageString(50);
		
		assertEquals("veintinueve", spanishNumber);
		assertEquals("cuarenta y cinco", spanishNumber0);
		assertEquals("cincuenta", spanishNumber1);
	}
	
	
	@Test
	public void pass_part_of_the_the_number_in_digits_to_spanish() {
		String spanishNumber = spanishParser.getLanguageString(100);
		
		assertEquals("ciento", spanishNumber);
	}

}
