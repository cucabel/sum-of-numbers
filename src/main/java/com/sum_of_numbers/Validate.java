package com.sum_of_numbers;

import static com.sum_of_numbers.Constant.EMPTY_STRING;
import static com.sum_of_numbers.Constant.REGEX_DASH;

import java.util.Arrays;

public abstract class Validate {
	
	public abstract void areInThisLanguage(String[] numbers) throws InvalidInputStringException, NumberOutOfRangeException;
	
	public boolean isInDigits(String number) {
		char firstChar = number.replaceAll(REGEX_DASH.getvalue(), EMPTY_STRING.getvalue()).charAt(0);
		return Character.isDigit(firstChar);
	}
	
	public boolean isInSpanish(String number) {
		return Arrays.stream(SpanishNumbers.class.getEnumConstants()).anyMatch(e -> e.name().equals(number.toUpperCase()));
	}
	
	public boolean isInEnglish(String number) {
		return Arrays.stream(EnglishNumbers.class.getEnumConstants()).anyMatch(e -> e.name().equals(number.toUpperCase()));
	}

}
