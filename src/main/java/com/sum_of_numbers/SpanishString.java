package com.sum_of_numbers;

public class SpanishString extends LanguageString {

	public SpanishString(String[] numbers) {
		super(numbers);
		setSumNumbers(new SpanishStringSum());
		setParser(new SpanishParser());
	}

}
