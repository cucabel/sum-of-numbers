package com.sum_of_numbers;

import static com.sum_of_numbers.Constant.*;
import static com.sum_of_numbers.NumericConstant.*;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EnglishParser implements IParser {

	public static final int LAST_ONE_WORD_NUMBER = 19;
	public static final int TEN_INDEX_POSITION = 1;

	@Override
	public int getWordValue(String englishNumber) {
		return EnglishNumbers.valueOf(englishNumber.toUpperCase()).getNumber();
	}

//  it gets the result of the sum	
	@Override
	public String getLanguageNumber(int digitNumber) {
		String englishNumber = EMPTY_STRING.getvalue();
//		if it is one digit number, two, or three
		if (digitNumber / NUMERIC_HUNDRED.getvalue() != NONE.getvalue()) {
//			one word string
			if (digitNumber == NUMERIC_HUNDRED.getvalue()) {
				englishNumber = getLanguageString(digitNumber);
			} else {
				int ten = digitNumber % NUMERIC_HUNDRED.getvalue();
//			number before hundred
				int hundred = (digitNumber - ten) / NUMERIC_HUNDRED.getvalue();
				englishNumber = getLanguageString(hundred) + WHITESPACE.getvalue() + HUNDRED.getvalue() + WHITESPACE .getvalue()+ getCombinedLanguageString(ten);
			}
		} else if (digitNumber / NUMERIC_TEN.getvalue() != NONE.getvalue()) {
			englishNumber = getCombinedLanguageString(digitNumber);
		} else {
			englishNumber = getLanguageString(digitNumber);
		}
		return englishNumber;
	}

//	tens
	public String getCombinedLanguageString(int digit) {
//		one word string
		if (digit <= LAST_ONE_WORD_NUMBER || digit % FIRST_MULTIPLE_OF_TEN.getvalue() == NO_REMAINDER.getvalue()) {
			return getLanguageString(digit);
//		two words string
		} else {
			String digitString = Integer.toString(digit);
			int unit = Integer.parseInt(digitString.substring(UNIT_INDEX_POSITION));
			int ten = digit - unit;
			return getLanguageString(ten) + ENGLISH_TEN_UNIT_SEPARATION.getvalue() + getLanguageString(unit);
		}
	}

//	one word string
	public String getLanguageString(int digit) {
		return Arrays.stream(EnglishNumbers.class.getEnumConstants())
				.filter(e -> e.getNumber() == digit)
				.map(e -> e.name()).collect(Collectors.joining()).toLowerCase();
	}

}
