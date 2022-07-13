package com.sum_of_numbers;

public class DigitsStringFactory extends LanguageStringFactory {

	@Override
	protected LanguageString createLanguageString(String[] numbers) {
		return new DigitsString(numbers);
	}
	
}
