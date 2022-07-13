package com.sum_of_numbers;

import static com.sum_of_numbers.Constant.*;
import static com.sum_of_numbers.NumericConstant.FIRST_INDEX_POSITION;
import static com.sum_of_numbers.NumericConstant.INCREMENT_TO_NEXT_INDEX_POSITION;

public class SpanishStringValidation extends Validate {

	@Override
	public void areInThisLanguage(String[] numbers) throws InvalidInputStringException, NumberOutOfRangeException {
		for (int i = FIRST_INDEX_POSITION.getvalue(); i < numbers.length; i++) {
//			skips the separation between ten and unit, and the sum symbol
			if (numbers[i].equals(SPANISH_TEN_UNIT_SEPARATION.getvalue())) {
				continue;
				
			} else if (numbers[i].equals(SPANISH_SUM_SYMBOL.getvalue())) {
				continue;
			}

			if (numbers[i].equals(SPANISH_HUNDRED_WITH_SPACE.getvalue())) {
				throw new InvalidInputStringException(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getvalue());
			}
			
			if (!isInSpanish(numbers[i])) {
				throw new NumberOutOfRangeException(ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE.getvalue());
			}

//			if it is a ten, has to be followed by y
			if (numbers[i].contains(SPANISH_TEN_ENDING.getvalue()) && i + INCREMENT_TO_NEXT_INDEX_POSITION.getvalue() < numbers.length) {
				if (!numbers[i + INCREMENT_TO_NEXT_INDEX_POSITION.getvalue()].equals(SPANISH_SUM_SYMBOL.getvalue()) && !numbers[i + INCREMENT_TO_NEXT_INDEX_POSITION.getvalue()].equals(SPANISH_TEN_UNIT_SEPARATION.getvalue())) {
					throw new InvalidInputStringException(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getvalue());
				}
			}
		}
	}

}
