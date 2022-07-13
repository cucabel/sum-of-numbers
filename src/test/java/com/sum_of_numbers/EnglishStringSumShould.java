package com.sum_of_numbers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class EnglishStringSumShould {
	
	private ISumNumbers englishStringSum;
	
	@Before
	public void setUp() {
		englishStringSum = new EnglishStringSum();
	}
	
	@Test
	public void return_the_result_of_the_sum() {
		String[] numbers = {"five", "plus", "one", "hundred"};
		LanguageString englishString = new EnglishString(numbers);
		
		String englishNumber = englishStringSum.sum(englishString);
		
		assertEquals("one hundred five", englishNumber);

	}
	
}
