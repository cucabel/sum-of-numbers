package com.sum_of_numbers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LanguageStringFactoryShould {
	
		private LanguageStringFactory languageStringFactory;
		
		@Test
		public void return_the_result_of_the_sum_digits_string() {
			languageStringFactory = new DigitsStringFactory();
			String[] numbers = {"5", "+", "5"};
			
			String actual = languageStringFactory.sum(numbers);
			
			assertEquals("10", actual);
		}
		
		@Test
		public void return_the_result_of_the_sum_spanish_string() {
			languageStringFactory = new SpanishStringFactory();
			String[] numbers = {"cinco", "mas", "cinco"};
			
			String actual = languageStringFactory.sum(numbers);
			
			assertEquals("diez", actual);
		}
		
		@Test
		public void return_the_result_of_the_sum_english_string() {
			languageStringFactory = new EnglishStringFactory();
			String[] numbers = {"five", "plus", "five"};
			
			String actual = languageStringFactory.sum(numbers);
			
			assertEquals("ten", actual);
		}

}
