package com.sum_of_numbers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SpanishStringSumShould {
	
	private ISumNumbers spanishStringSum;
	
	@Before
	public void setUp() {
		spanishStringSum = new SpanishStringSum();
	}
	
	@Test
	public void return_the_result_of_the_sum() {
		String[] numbers = {"cinco", "mas", "cien"};
		LanguageString spanishString = new SpanishString(numbers);
		
		String spanishNumber = spanishStringSum.sum(spanishString);
		
		assertEquals("ciento cinco", spanishNumber);

	}
	
}
