package com.sum_of_numbers;

public class EnglishString extends LanguageString {

	public EnglishString(String[] numbers) {
		super(numbers);
		setSumNumbers(new EnglishStringSum());
		setParser(new EnglishParser());
	}

}
