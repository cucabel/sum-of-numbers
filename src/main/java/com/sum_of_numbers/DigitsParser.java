package com.sum_of_numbers;

public class DigitsParser implements IParser {
	
	@Override
	public int getWordValue(String number) {
		return Integer.parseInt(number);
	}

	@Override
	public String getLanguageNumber(int digit) {
		return String.valueOf(digit);
	}

}
