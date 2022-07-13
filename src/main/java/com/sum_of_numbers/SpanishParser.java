package com.sum_of_numbers;

import static com.sum_of_numbers.Constant.*;
import static com.sum_of_numbers.NumericConstant.*;

import java.util.Arrays;

public class SpanishParser implements IParser {
	
	public static final int LAST_ONE_WORD_NUMBER = 29;
	
	@Override
	public int getWordValue(String spanishNumber) {
		return SpanishNumbers.valueOf(spanishNumber.toUpperCase()).getNumber();
	}
	
//  it gets the result of the sum	
	@Override
	public String getLanguageNumber(int digitNumber) {
		String spanishNumber = EMPTY_STRING.getvalue();
//		if it is one digit number, two, or three
		if (digitNumber / NUMERIC_HUNDRED.getvalue() != NONE.getvalue()) {
//			one word string. it also returns the first constant name with number 100
			int ten = digitNumber % NUMERIC_HUNDRED.getvalue();
			int hundred = digitNumber - ten;
			
			if (digitNumber % FIRST_MULTIPLE_OF_TEN.getvalue() == NO_REMAINDER.getvalue() && ten == NONE.getvalue()) {
				spanishNumber = Arrays.stream(SpanishNumbers.class.getEnumConstants()).filter(e -> e.getNumber() == digitNumber).map(e -> e.name())
						.findFirst().get().toString().toLowerCase();
			} else {
				spanishNumber = getLanguageString(hundred) + WHITESPACE.getvalue() + getCombinedLanguageString(ten);
			}
		} else if (digitNumber / NUMERIC_TEN.getvalue() != NONE.getvalue()) {
		spanishNumber = getCombinedLanguageString(digitNumber);
		} else {
			spanishNumber = getLanguageString(digitNumber);
		}
		return spanishNumber;	
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
				return getLanguageString(ten) +WHITESPACE .getvalue()+ SPANISH_TEN_UNIT_SEPARATION.getvalue() + WHITESPACE.getvalue() + getLanguageString(unit);
		}
	}
//	one word string. If the hundred is 100, returns the second constant name with number 100
	public String getLanguageString(int digit) {
		return Arrays.stream(SpanishNumbers.class.getEnumConstants()).filter(e -> e.getNumber() == digit).map(e -> e.name())
				.reduce((first, second) -> second).get().toString().toLowerCase();
	}

}
