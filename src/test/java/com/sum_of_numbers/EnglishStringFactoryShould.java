package com.sum_of_numbers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class EnglishStringFactoryShould {

	private LanguageStringFactory languageStringFactory;
	
	@Before
	public void setUp() {
		languageStringFactory = new EnglishStringFactory();
	}
	
	@Test
	public void create_an_english_string() {
		String[] numbers = {"forty", "plus", "forty"};
		
		LanguageString englishString = languageStringFactory.createLanguageString(numbers);
		
		assertTrue(englishString.getClass() == EnglishString.class);
	}
}
