package com.sum_of_numbers;

public class SpanishStringFactory extends LanguageStringFactory {

	@Override
	protected LanguageString createLanguageString(String[] numbers) {
		return new SpanishString(numbers);
	}

}
