package com.sum_of_numbers;

public class EnglishStringFactory extends LanguageStringFactory{

	@Override
	protected LanguageString createLanguageString(String[] numbers) {
		return new EnglishString(numbers);
	}

}
