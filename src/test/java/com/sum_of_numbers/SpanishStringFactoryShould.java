package com.sum_of_numbers;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SpanishStringFactoryShould {

	private LanguageStringFactory languageStringFactory;
	
	@Before
	public void setUp() {
		languageStringFactory = new SpanishStringFactory();
	}
	
	@Test
	public void create_an_spanish_string() {
		String[] numbers = {"cuarenta", "mas", "cuarenta"};
		
		LanguageString spanishString = languageStringFactory.createLanguageString(numbers);
		
		assertTrue(spanishString.getClass() == SpanishString.class);
	}
}
