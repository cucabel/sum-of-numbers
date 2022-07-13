package com.sum_of_numbers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EnglishParserShould {
	
	private IParser parser;
	private EnglishParser englishParser;
	
	@Before
	public void setUp() {
		parser = new EnglishParser();
		englishParser = new EnglishParser();
	}
	
	@Test
	public void pass_the_number_in_english_to_digits() {
		int digitNumber = parser.getWordValue("hundred");
		
		assertEquals(100, digitNumber);
	}
	
	@Test
	public void pass_the_number_in_digits_to_english() {
		String englishNumber = parser.getLanguageNumber(145);
		
		assertEquals("one hundred forty-five", englishNumber);
	}
	
	
	@Test
	public void pass_a_ten_in_digits_to_english() {
		String englishNumber = englishParser.getCombinedLanguageString(19);
		String englishNumber0 = englishParser.getCombinedLanguageString(45);
		String englishNumber1= englishParser.getCombinedLanguageString(50);
		
		assertEquals("nineteen", englishNumber);
		assertEquals("forty-five", englishNumber0);
		assertEquals("fifty", englishNumber1);
	}
	
	
	@Test
	public void pass_part_of_the_the_number_in_digits_to_english() {
		String actual = englishParser.getLanguageString(100);
		
		assertEquals("hundred", actual);
	}
	
}
