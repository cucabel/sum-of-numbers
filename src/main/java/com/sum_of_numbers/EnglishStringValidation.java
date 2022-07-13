package com.sum_of_numbers;

import static com.sum_of_numbers.Constant.*;
import static com.sum_of_numbers.NumericConstant.*;

public class EnglishStringValidation extends Validate {
	
	@Override
	public void areInThisLanguage(String[] numbers) throws InvalidInputStringException, NumberOutOfRangeException {
		for (int i = FIRST_INDEX_POSITION.getvalue(); i < numbers.length; i++) {
//			skips the separation between ten and unit, and the sum symbol
			if (numbers[i].equals(ENGLISH_TEN_UNIT_SEPARATION.getvalue())) {
				continue;
				
			} else if (numbers[i].equals(ENGLISH_SUM_SYMBOL.getvalue())) {
				continue;
			}
			
			if (!isInEnglish(numbers[i])) {
				throw new NumberOutOfRangeException(ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE.getvalue());
			}
//			if it is a ten, has to be followed by -
			if (numbers[i].contains(ENGLISH_TEN_ENDING.getvalue()) && i + INCREMENT_TO_NEXT_INDEX_POSITION.getvalue() < numbers.length) {
				if (!numbers[i + INCREMENT_TO_NEXT_INDEX_POSITION.getvalue()].equals(ENGLISH_SUM_SYMBOL.getvalue()) && !numbers[i + INCREMENT_TO_NEXT_INDEX_POSITION.getvalue()].equals(ENGLISH_TEN_UNIT_SEPARATION.getvalue())) {
					throw new InvalidInputStringException(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getvalue());
				}
			}
		}
	}
	
	public void hundredTogetherIsInvalid(String number) throws InvalidInputStringException {
		String unit = number.substring(FIRST_INDEX_POSITION.getvalue(), number.indexOf(HUNDRED.getvalue()));
		String hundred = number.substring(number.indexOf(HUNDRED.getvalue()));
		if (isInEnglish(unit) && isInEnglish(hundred)) {
			throw new InvalidInputStringException(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getvalue());
		}	
	}
	
	public void tenTogetherIsInvalid(String number) throws InvalidInputStringException {
		String unit = number.substring(number.indexOf(ENGLISH_TEN_ENDING.getvalue()) + ENGLISH_TEN_ENDING_LENGTH.getvalue());
		String ten = number.substring(FIRST_INDEX_POSITION.getvalue(), number.indexOf(unit));
		if (isInEnglish(unit) && isInEnglish(ten)) {
			throw new InvalidInputStringException(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getvalue());
		}		
	}
	
}
