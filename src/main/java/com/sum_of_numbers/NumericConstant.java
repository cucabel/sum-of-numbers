package com.sum_of_numbers;

public enum NumericConstant {
	
	FIRST_INDEX_POSITION(0),
	FIRST_NUMBER_POSITION(0),
	NO_REMAINDER (0),
	NONE (0),
	INCREMENT_TO_NEXT_INDEX_POSITION(1),
	ENGLISH_TEN_ENDING_LENGTH (2),
	FIRST_MULTIPLE_OF_TEN (10),
	NUMERIC_TEN (10),
	NUMERIC_HUNDRED(100),
	;

	private int value;

	private NumericConstant(int value) {
		this.value = value;
	}

	public int getvalue() {
		return value;
	}
}
