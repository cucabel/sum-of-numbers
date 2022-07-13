package com.sum_of_numbers;

import static com.sum_of_numbers.Constant.SPANISH_SUM_SYMBOL;
import static com.sum_of_numbers.Constant.SPANISH_TEN_UNIT_SEPARATION;
import static com.sum_of_numbers.NumericConstant.NONE;

import java.util.ArrayList;
import java.util.List;

public class SpanishStringSum implements ISumNumbers {
	
	@Override
	public String sum(LanguageString languageString) {
		
		String[] numbers = languageString.getNumbers();
		IParser parser = languageString.getParser();
		
		int digit = NONE.getvalue();
		int sumResult = NONE.getvalue();
		List<Integer> digits = new ArrayList<>();
		
		// DIGITS OBTENTION
		for (String number : numbers) {
//			skips the separation between ten and unit
			if (number.equals(SPANISH_TEN_UNIT_SEPARATION.getvalue())) {
				continue;
			}
//			adds the result of the sum of the first addend to digits, and skips the sum symbol
			else if (number.equals(SPANISH_SUM_SYMBOL.getvalue())) {
				digits.add(sumResult);
				sumResult = NONE.getvalue();
				continue;
			}
				// PARSE
				digit = parser.getWordValue(number);

				sumResult += digit;
		}

//		adds the result of the sum of the second addend to digits
		digits.add(sumResult);
//		result of the sum of the two addends, or the sum of the only addend, in case there is only one

		// SUM
		sumResult = languageString.sum(digits);

		// PARSE
		String spanishNumber = parser.getLanguageNumber(sumResult);

		return spanishNumber;
	}

}
