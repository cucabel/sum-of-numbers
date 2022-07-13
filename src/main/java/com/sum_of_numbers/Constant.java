package com.sum_of_numbers;

public enum Constant {
	SPANISH_TEN_UNIT_SEPARATION("y"),
	ENGLISH_TEN_UNIT_SEPARATION("-"),
	DIGIT_SUM_SYMBOL("+"),
	SPANISH_SUM_SYMBOL("mas"),
	ENGLISH_SUM_SYMBOL("plus"),
	SPANISH_TEN_ENDING("nta"),
	ENGLISH_TEN_ENDING("ty"),
	SUM_ACTION("addition"),
	SPANISH_HUNDRED_WITH_SPACE("cientos"),
	HUNDRED("hundred"),
	REGEX_DASH("\\-"),
	EMPTY_STRING(""),
	WHITESPACE(" "),
	DASH_BETWEEN_WHITESPACES(" - "),
	ERROR_MESSAGE_WHEN_INPUT_IS_EMPTY("empty input"),
	ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE("some number is out of range (0-999)"),
	ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID("invalid string"),
	;

	private String value;

	private Constant(String value) {
		this.value = value;
	}

	public String getvalue() {
		return value;
	}

}


