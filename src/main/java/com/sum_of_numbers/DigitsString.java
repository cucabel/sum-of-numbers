package com.sum_of_numbers;

public class DigitsString extends LanguageString {

	public DigitsString(String[] numbers) {
		super(numbers);
		setSumNumbers(new DigitsSum());
		setParser(new DigitsParser());
	}

}
