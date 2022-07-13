package com.sum_of_numbers;

import static com.sum_of_numbers.Constant.ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DigitsValidationShould {
	
	private Validate validate;
	
	@Before
	public void setUp() {
		validate = new DigitsValidation();
	}
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test
	public void return_the_error_message_some_number_is_out_of_range_when_a_number_is_out_of_range() throws InvalidInputStringException, NumberOutOfRangeException {
		String[] numbers = {"1", "+", "1000"};
		
		exceptionRule.expect(NumberOutOfRangeException.class);
		exceptionRule.expectMessage(ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE.getvalue());
		validate.areInThisLanguage(numbers);
	}

}
