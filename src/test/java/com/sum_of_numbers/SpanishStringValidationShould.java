package com.sum_of_numbers;

import static com.sum_of_numbers.Constant.ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID;
import static com.sum_of_numbers.Constant.ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SpanishStringValidationShould {
	
	private Validate validate;
	
	@Before
	public void setUp() {
		validate = new SpanishStringValidation();
	}
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test
	public void return_the_error_message_invalid_string_when_the_hundred_has_space() throws InvalidInputStringException, NumberOutOfRangeException {
		String[] numbers = {"tres", "cientos"};
		
		exceptionRule.expect(InvalidInputStringException.class);
		exceptionRule.expectMessage(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getvalue());
		validate.areInThisLanguage(numbers);
	}
	
	@Test
	public void return_the_error_message_some_number_is_out_of_range_when_a_number_is_out_of_range() throws InvalidInputStringException, NumberOutOfRangeException {
		String[] numbers = {"uno", "mas", "mil"};
		
		exceptionRule.expect(NumberOutOfRangeException.class);
		exceptionRule.expectMessage(ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE.getvalue());
		validate.areInThisLanguage(numbers);
	}
	
	@Test
	public void return_the_error_message_invalid_string_when_the_ten_has_not_spanish_ten_unit_separation() throws InvalidInputStringException, NumberOutOfRangeException {
		String[] numbers = {"cuarenta", "cuatro","mas", "mil"};
		
		exceptionRule.expect(InvalidInputStringException.class);
		exceptionRule.expectMessage(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getvalue());
		validate.areInThisLanguage(numbers);
	}
}
