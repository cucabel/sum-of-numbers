package com.sum_of_numbers;

import static com.sum_of_numbers.Constant.DIGIT_SUM_SYMBOL;
import static com.sum_of_numbers.Constant.ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE;

public class DigitsValidation extends Validate {
	
	private static final int NUMERIC_MAX_RANGE_VALUE = 999;
	private static final int NUMERIC_MIN_RANGE_VALUE = 0;

	@Override
	public void areInThisLanguage(String[] numbers)  throws NumberOutOfRangeException {
		
		for (String number : numbers) {
			if (number.equals(DIGIT_SUM_SYMBOL.getvalue())) {
				continue;
			}
			
			int digit = Integer.parseInt(number);
			
			if (digit > NUMERIC_MAX_RANGE_VALUE || digit < NUMERIC_MIN_RANGE_VALUE) {
				throw new NumberOutOfRangeException(ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE.getvalue());
			}
		}
	}

}
