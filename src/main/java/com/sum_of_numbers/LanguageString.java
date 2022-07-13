package com.sum_of_numbers;

import static com.sum_of_numbers.NumericConstant.NONE;

import java.util.List;

public class LanguageString {
	
	private String[] numbers;
//	set dynamically at DigistString, SpanishString and EnglishString
	private ISumNumbers sumNumbers;
	private IParser parser;
	
	public LanguageString(String[] numbers) {
		this.numbers = numbers;
	}

	public String sum() {
		return sumNumbers.sum(this);
	}
	
	public int sum(List<Integer> digits) {
		return digits.stream().reduce(NONE.getvalue(), (a, b) -> a + b);
	}

	public String[] getNumbers() {
		return numbers;
	}

	public ISumNumbers getSumNumbers() {
		return sumNumbers;
	}

	protected void setSumNumbers(ISumNumbers sumNumbers) {
		this.sumNumbers = sumNumbers;
	}

	public IParser getParser() {
		return parser;
	}

	protected void setParser(IParser parser) {
		this.parser = parser;
	}

}
