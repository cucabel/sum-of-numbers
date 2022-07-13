package com.sum_of_numbers;

public abstract class LanguageStringFactory {
	
	protected abstract LanguageString createLanguageString(String[] numbers);
	
	public String sum(String[] numbers) {
		
		LanguageString languageString = createLanguageString(numbers);
		
		String sumResult = languageString.sum();

		return sumResult;
	}
	
}
