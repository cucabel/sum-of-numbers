package com.sum_of_numbers;

import static com.sum_of_numbers.Constant.DIGIT_SUM_SYMBOL;
import static com.sum_of_numbers.NumericConstant.NONE;

import java.util.ArrayList;
import java.util.List;

public class DigitsSum implements ISumNumbers {

	public String sum(LanguageString languageString) {
		
		String[] numbers = languageString.getNumbers();
		IParser parser = languageString.getParser();
		
		int digit = 0;
		int sumResult = 0;
		List<Integer> digits = new ArrayList<>();
		
		// DIGITS OBTENTION
		for (String number : numbers) {
//			adds the result of the sum of the first addend to digits, and skips the sum symbol
			if (number.equals(DIGIT_SUM_SYMBOL.getvalue())) {
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
		sumResult = languageString.sum(digits);

		// PARSE
		String digitsSumResult = parser.getLanguageNumber(sumResult);

		return digitsSumResult;
	}

}
