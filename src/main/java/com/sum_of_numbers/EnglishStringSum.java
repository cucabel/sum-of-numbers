package com.sum_of_numbers;

import static com.sum_of_numbers.Constant.ENGLISH_SUM_SYMBOL;
import static com.sum_of_numbers.Constant.ENGLISH_TEN_UNIT_SEPARATION;
import static com.sum_of_numbers.NumericConstant.*;

import java.util.ArrayList;
import java.util.List;

public class EnglishStringSum implements ISumNumbers {
	
	@Override
	public String sum(LanguageString languageString) {

		String[] numbers = languageString.getNumbers();
		IParser parser = languageString.getParser();

		int digit = NONE.getvalue();
		int sumResult = NONE.getvalue();
		List<Integer> digits = new ArrayList<>();

		for (String number : numbers) {
//			skips the separation between ten and unit
			if (number.equals(ENGLISH_TEN_UNIT_SEPARATION.getvalue())) {
				continue;
			}
//			adds the result of the sum of the first addend to digits, and skips the sum symbol
			if (number.equals(ENGLISH_SUM_SYMBOL.getvalue())) {
				digits.add(sumResult);
				sumResult = NONE.getvalue();
				continue;
			}

			digit = parser.getWordValue(number);

			if (digit == NUMERIC_HUNDRED.getvalue()) {
//				sumResult contained the number before hundred, from the previous iteration, and overrides it, with the hundred value
				sumResult = sumResult * digit;
			} else {
				sumResult += digit;
			}
		}

//		adds the result of the sum of the second addend to digits
		digits.add(sumResult);
//		result of the sum of the two addends, or the sum of the only addend, in case there is only one
		sumResult = languageString.sum(digits);

		String englishNumber = parser.getLanguageNumber(sumResult);

		return englishNumber;
	}

}
