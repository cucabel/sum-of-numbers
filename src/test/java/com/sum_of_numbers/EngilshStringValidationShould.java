package com.sum_of_numbers;

import static com.sum_of_numbers.Constant.ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID;
import static com.sum_of_numbers.Constant.ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class EngilshStringValidationShould {
	
	private Validate validate;
	private EnglishStringValidation englishStringValidate;
	
	@Before
	public void setUp() {
		validate = new EnglishStringValidation();
		englishStringValidate = new EnglishStringValidation();
	}
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	
	@Test
	public void return_the_error_message_some_number_is_out_of_range_when_a_number_is_out_of_range() throws InvalidInputStringException, NumberOutOfRangeException {
		String[] numbers = {"one", "plus", "thousand"};
		
		exceptionRule.expect(NumberOutOfRangeException.class);
		exceptionRule.expectMessage(ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE.getvalue());
		validate.areInThisLanguage(numbers);
	}
	
	@Test
	public void return_the_error_message_invalid_string_when_the_ten_has_not_english_ten_unit_separation() throws InvalidInputStringException, NumberOutOfRangeException {
		String[] numbers = {"forty", "four"};
		
		exceptionRule.expect(InvalidInputStringException.class);
		exceptionRule.expectMessage(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getvalue());
		validate.areInThisLanguage(numbers);
	}
	
	@Test
	public void return_the_error_message_invalid_string_when_the_hundred_goes_together() throws InvalidInputStringException, NumberOutOfRangeException {
		String number = "onehundred";
		
		exceptionRule.expect(InvalidInputStringException.class);
		exceptionRule.expectMessage(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getvalue());
		englishStringValidate.hundredTogetherIsInvalid(number);
	}
	
	@Test
	public void return_the_error_message_invalid_string_when_the_ten_goes_together() throws InvalidInputStringException, NumberOutOfRangeException {
		String number = "fortyfour";
		
		exceptionRule.expect(InvalidInputStringException.class);
		exceptionRule.expectMessage(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getvalue());
		englishStringValidate.tenTogetherIsInvalid(number);
	}

}
