package com.sum_of_numbers;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static com.sum_of_numbers.Constant.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculateShould {
	
	@InjectMocks
	private Calculate calculate;
	@Mock
	private Console console;
	@Mock
	private Validate validate;
	@Mock
	private ICalculateOperation calculateOperation;
	
	@Before
	public void setUp() {
		Validate validate = new DigitsValidation();
		calculate = new Calculate(console, validate, calculateOperation);
	}
	
	@Test
	public void setTheValidation() {
		Validate spanishValidation = new SpanishStringValidation();
		
		calculate.setValidate(spanishValidation);
		
		Validate validation = calculate.getValidate();
		assertEquals(validation, spanishValidation);
	}
	
	@Test
	public void set_the_calculate_operation() {
		ICalculateOperation addOperation = new AddOperation();		
		
		calculate.setCalculateOperation(addOperation);
		
		ICalculateOperation calculateOperation = calculate.getCalculateOperation();
		assertEquals(calculateOperation, addOperation);
	}
	
	@Test
	public void get_the_input_from_the_console() {
		when(console.scanInput()).thenReturn("cinco");

		calculate.getInput();
		
		verify(console).scanInput();
	}
	
	@Test
	public void get_to_know_which_is_the_calculate_operation() {
		when(console.scanInput()).thenReturn("uno mas uno");
		
		calculate.getInput();
		String actualAction = calculate.getAction();
		String expectedAction = SUM_ACTION.getvalue();
		
		assertEquals(expectedAction, actualAction);
	}
	
	@Test
	public void validate_the_sum_string_is_valid() throws InvalidInputStringException, NumberOutOfRangeException {
		when(console.scanInput()).thenReturn("1 + 1", "uno mas dos", "one plus three");
		doNothing().when(validate).areInThisLanguage(Mockito.any(String[].class));
		doNothing().when(calculateOperation).setLanguageStringFactory(Mockito.any(LanguageStringFactory.class));
		when(calculateOperation.calculate(Mockito.any(String[].class))).thenReturn("2", "tres", "four");
		
		calculate.getInput();
		calculate.validateSumString();
		String actualOutput0 = calculate.getOutput();
		calculate.getInput();
		calculate.validateSumString();
		String actualOutput1 = calculate.getOutput();
		calculate.getInput();
		calculate.validateSumString();
		String actualOutput2 = calculate.getOutput();
		assertEquals("2", actualOutput0);
		assertEquals("tres", actualOutput1);
		assertEquals("four", actualOutput2);
	}
	
	@Test
	public void validate_the_sum_string_is_invalid() throws InvalidInputStringException, NumberOutOfRangeException {
		when(console.scanInput()).thenReturn("-1 + 1", "menos uno mas dos", "minus one plus three");
		doThrow(new NumberOutOfRangeException(ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE.getvalue())).when(validate).areInThisLanguage(Mockito.any(String[].class));
		
		calculate.getInput();
		calculate.validateSumString();
		String actualOutput0 = calculate.getOutput();
		calculate.getInput();
		calculate.validateSumString();
		String actualOutput1 = calculate.getOutput();
		calculate.getInput();
		calculate.validateSumString();
		String actualOutput2 = calculate.getOutput();
		assertEquals("some number is out of range (0-999)", actualOutput0);
		assertEquals("some number is out of range (0-999)", actualOutput1);
		assertEquals("some number is out of range (0-999)", actualOutput2);
	}
	
	@Test
	public void validate_hundred_toghether_is_invalid() {
		when(console.scanInput()).thenReturn("onehundred");
		
		calculate.getInput();
		calculate.validateSumString();
		String actualOutput = calculate.getOutput();
		assertEquals("invalid string", actualOutput);
	}
	
	@Test
	public void validate_ten_without_ten_unit_separation_is_invalid() {
		when(console.scanInput()).thenReturn("fortyfour");
		
		calculate.getInput();
		calculate.validateSumString();
		String actualOutput = calculate.getOutput();
		assertEquals("invalid string", actualOutput);
	}
	
	@Test
	public void validate_sum_string_is_not_in_digits_spanish_or_english_is_invalid() {
		when(console.scanInput()).thenReturn("achtundzwanzig");
		
		calculate.getInput();
		calculate.validateSumString();
		
		String actualOutput = calculate.getOutput();
		assertEquals("some number is out of range (0-999)", actualOutput);
	}
	
	@Test
	public void validate_the_sum_string_in_a_specific_language_is_valid() throws InvalidInputStringException, NumberOutOfRangeException {
		String[] numbers = {"1", "+", "1"};
		LanguageStringFactory digitsStringFactory = new DigitsStringFactory();
		doNothing().when(validate).areInThisLanguage(Mockito.any(String[].class));
		doNothing().when(calculateOperation).setLanguageStringFactory(Mockito.any(LanguageStringFactory.class));
		
		calculate.validateSumLanguageString(validate, numbers, digitsStringFactory);
		
		verify(validate).areInThisLanguage(numbers);
		verify(calculateOperation).setLanguageStringFactory(digitsStringFactory);
	}
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test
	public void validate_a_number_is_out_of_range() throws InvalidInputStringException, NumberOutOfRangeException {
		String[] numbers = {"-1", "+", "1"};
		doThrow(new NumberOutOfRangeException(ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE.getvalue())).when(validate).areInThisLanguage(Mockito.any(String[].class)); // podria tirar dos excepciones
		
		exceptionRule.expect(NumberOutOfRangeException.class);
		exceptionRule.expectMessage(ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE.getvalue());
		calculate.validateSumLanguageString(validate, numbers, new DigitsStringFactory());
		
		verify(validate).areInThisLanguage(numbers);
	}
	
	@Test
	public void validate_a_number_is_invalid() throws InvalidInputStringException, NumberOutOfRangeException {
		String[] numbers = {"dos", "cientos"};

		doThrow(new InvalidInputStringException(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getvalue())).when(validate).areInThisLanguage(Mockito.any(String[].class));
		
		exceptionRule.expect(InvalidInputStringException.class);
		exceptionRule.expectMessage(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getvalue());
		calculate.validateSumLanguageString(validate, numbers, new SpanishStringFactory());
		
		verify(validate).areInThisLanguage(numbers);
	}
	
	@Test
	public void sum_the_numbers() {
		String[] numbers = {"1", "+", "1"};
		when(calculateOperation.calculate(Mockito.any(String[].class))).thenReturn("2");
		
		calculate.sumNumbers(numbers);
		
		verify(calculateOperation).calculate(numbers);
	}
	
	@Test
	public void pass_the_output_to_the_console() {
		when(console.scanInput()).thenReturn("1 + 1", "-1 + 1");
		when(calculateOperation.calculate(Mockito.any(String[].class))).thenReturn("2");
		doNothing().when(console).printResult(anyString());
		
		calculate.getInput();
		calculate.validateSumString();
		String actualOutput0 = calculate.getOutput();
		calculate.passOutput();
		
		calculate.getInput();
		calculate.validateSumString();
		String actualOutput1 = calculate.getOutput();
		calculate.passOutput();
		
		assertEquals("2", actualOutput0);
		assertEquals(ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE.getvalue(), actualOutput1);
		verify(console).printResult(actualOutput0);
		verify(console).printResult(actualOutput1);
	}

}
