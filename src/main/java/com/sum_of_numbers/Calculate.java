package com.sum_of_numbers;

import static com.sum_of_numbers.Constant.*;
import static com.sum_of_numbers.NumericConstant.FIRST_NUMBER_POSITION;

// Calculate, the high-level module, depends on the CalculateOperation interface, the abstraction
public class Calculate {
	
	private String input;
	private String output;
	private Console console;
	private Validate validate;
	private ICalculateOperation calculateOperation;

	public Calculate(Console console, Validate validate, ICalculateOperation calculateOperation) {
		this.console = console;
		this.validate = validate;
		this.calculateOperation = calculateOperation;
	}

	public Calculate(Console console, Validate validate) {
		this.console = console;
		this.validate = validate;
	}
	
	public void getInput() {
		input = console.scanInput();
	}
	
	public String getOutput() {
		return output;
	}

	public Validate getValidate() {
		return validate;
	}

	public void setValidate(Validate validate) {
		this.validate = validate;
	}

	public ICalculateOperation getCalculateOperation() {
		return calculateOperation;
	}

	public void setCalculateOperation(ICalculateOperation calculateOperation) {
		this.calculateOperation = calculateOperation;
	}
	
	public String getAction() {
		String action = SUM_ACTION.getvalue();
		if (input.contains(DIGIT_SUM_SYMBOL.getvalue()) || input.contains(SPANISH_SUM_SYMBOL.getvalue()) || input.contains(ENGLISH_SUM_SYMBOL.getvalue())) {
			action = SUM_ACTION.getvalue();
		}
		return action;
	}

	public void validateSumString() {
		String[] numbers = null;
		EnglishStringValidation englishStringValidate = new EnglishStringValidation();

		try {
			if (validate.isInDigits(input)) {
				numbers = input.split(WHITESPACE.getvalue());
				
				validateSumLanguageString(validate, numbers, new DigitsStringFactory());
				
			} else {
				numbers = input
						.replaceAll(REGEX_DASH.getvalue(), DASH_BETWEEN_WHITESPACES.getvalue()) // in order to separate English tens of the units
						.split(WHITESPACE.getvalue());

				if (validate.isInSpanish(numbers[FIRST_NUMBER_POSITION.getvalue()])) {
					validateSumLanguageString(new SpanishStringValidation(), numbers, new SpanishStringFactory());

				} else if (validate.isInEnglish(numbers[FIRST_NUMBER_POSITION.getvalue()])) {
					validateSumLanguageString(new EnglishStringValidation(), numbers, new EnglishStringFactory());
					
//				if they are words, but they aren't the Spanish, neither the English numbers considered
				} else if (numbers[FIRST_NUMBER_POSITION.getvalue()].contains(HUNDRED.getvalue())) {
					englishStringValidate.hundredTogetherIsInvalid(numbers[FIRST_NUMBER_POSITION.getvalue()]);

				} else if (numbers[FIRST_NUMBER_POSITION.getvalue()].contains(ENGLISH_TEN_ENDING.getvalue())) {
					englishStringValidate.tenTogetherIsInvalid(numbers[FIRST_NUMBER_POSITION.getvalue()]);

				} else {
					throw new NumberOutOfRangeException(ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE.getvalue());
				}
			} 
			
			sumNumbers(numbers);
			
		} catch (InvalidInputStringException e) {
			output = e.getMessage();
		} catch (NumberOutOfRangeException e) {
			output = e.getMessage();
		}
	}
	
	public void validateSumLanguageString(Validate validate, String[] numbers, LanguageStringFactory languageStringFactory) throws InvalidInputStringException, NumberOutOfRangeException {
		this.setValidate(validate);
		validate.areInThisLanguage(numbers);
		
		calculateOperation.setLanguageStringFactory(languageStringFactory);
	}
	
	public void sumNumbers(String[] numbers) {
		output = calculateOperation.calculate(numbers);
	}
	
	public void passOutput() {
		console.printResult(output);
	}

}

/*public class Calculate {

	private Console console;

	private static final int NUMERIC_MAX_RANGE_VALUE = 999;
	private static final int NUMERIC_MIN_RANGE_VALUE = 0;
	private static final int NUMERIC_HUNDRED = 100;
	private static final int FIRST_INDEX_POSITION = 0;
	private static final int INCREMENT_TO_NEXT_INDEX_POSITION = 1;
	private static final int ENGLISH_TEN_ENDING_LENGTH = 2;
	private static final int NONE = 0;

	public Calculate(Console console) {
		this.console = console;
	}

	// if calculate.solve() was called from somewhere, all exceptions could be catch from there
	public void solve() throws NumberOutOfRangeException {
		String input = getInput();

		if (input == ERROR_MESSAGE_WHEN_INPUT_IS_EMPTY.getConstant()) {
			console.printResult(input);
		} else {
			if (isDigit(input)) {
				String[] numbers = input.replaceAll(REGEX_MULITPLE_WHITESPACE_CHARACTERS.getConstant(), EMPTY_STRING.getConstant())
																.split(REGEX_DIGIT_SUM_SYMBOL.getConstant());

				// PARSE
				List<Integer> digits = Arrays.asList(numbers).stream()
						.map(Integer::parseInt)
						.collect(Collectors.toList()); // this are the digits I get from the for, in spanishNumber

				for (Integer digit : digits) {
					try {
						// validateNumber(digit);
						if (digit > NUMERIC_MAX_RANGE_VALUE || digit < NUMERIC_MIN_RANGE_VALUE) {
							throw new NumberOutOfRangeException(ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE.getConstant());
						}
					} catch (NumberOutOfRangeException e) {
						console.printResult(e.getMessage());
						return;
					}
				}

				// SUM
				//String digitString = sum(digits);
				int sumResult = sum(digits);
				
				String digitsSumResult = Integer.toString(sumResult);

				console.printResult(digitsSumResult);

			} else {
				String[] numbers = input.replaceAll(REGEX_DASH.getConstant(), DASH_BETWEEN_WHITESPACES.getConstant())
																.split(WHITESPACE.getConstant());

				if (isSpanish(numbers[FIRST_INDEX_POSITION])) {
					IParser parser = new SpanishParser();

					int digit = NONE;
					int sumResult = NONE;
					List<Integer> digits = new ArrayList<>();

					// DIGITS OBTENTION
					for (int i = FIRST_INDEX_POSITION; i < numbers.length; i++) {
//						skips the separation between ten and unit
						if (numbers[i].equals(SPANISH_TEN_UNIT_SEPARATION.getConstant())) {
							continue;
						}
//						adds the result of the sum of the first addend to digits, and skips the sum symbol
						else if (numbers[i].equals(SPANISH_SUM_SYMBOL.getConstant())) {
							digits.add(sumResult);
							sumResult = NONE;
							continue;
						}

						try {
							if (numbers[i].equals(SPANISH_HUNDRED_WITH_SPACE.getConstant())) {
								throw new InvalidInputStringException(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getConstant());
							}
							
//							if it is a ten, has to be followed by y, in Spanish, and -, in English
							if (i + INCREMENT_TO_NEXT_INDEX_POSITION < numbers.length && numbers[i].contains(SPANISH_TEN_ENDING.getConstant())) {
								if (!numbers[i + INCREMENT_TO_NEXT_INDEX_POSITION] .equals(SPANISH_TEN_UNIT_SEPARATION.getConstant())) {
									throw new InvalidInputStringException(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getConstant());
								}							
							}
							
							// PARSE
							digit = parser.getWordValue(numbers[i]); // here is where it is parsed

							sumResult += digit;

						} catch (InvalidInputStringException e) {
							console.printResult(e.getMessage());
							return;
						} catch (NumberOutOfRangeException e) {
							console.printResult(e.getMessage());
							return;
						}
					}

//					adds the result of the sum of the second addend to digits
					digits.add(sumResult);
//					result of the sum of the two addends, or the sum of the only addend, in case there is only one

					// SUM
					//String digitString = sum(digits);
					int digitString = sum(digits);

					// PARSE
					String spanishNumber = parser.getLanguageNumber(digitString);

					console.printResult(spanishNumber);

				} else if (isEnglish(numbers[FIRST_INDEX_POSITION])) {
					IParser parser = new EnglishParser();

					int digit = NONE;
					int sumResult = NONE;
					List<Integer> digits = new ArrayList<>();

					for (int i = FIRST_INDEX_POSITION; i < numbers.length; i++) {
//						skips the separation between ten and unit
						if (numbers[i].equals(ENGLISH_TEN_UNIT_SEPARATION.getConstant())) {
							continue;
						}
//						adds the result of the sum of the first addend to digits, and skips the sum symbol
						if (numbers[i].equals(ENGLISH_SUM_SYMBOL.getConstant())) {
							digits.add(sumResult);
							sumResult = NONE;
							continue;
						}

						try {
//							if it is a ten, has to be followed by y, in Spanish, and -, in English
							if (i + INCREMENT_TO_NEXT_INDEX_POSITION  < numbers.length && numbers[i].contains(ENGLISH_TEN_ENDING.getConstant())) {
								if (!numbers[i + INCREMENT_TO_NEXT_INDEX_POSITION ].equals(ENGLISH_TEN_UNIT_SEPARATION.getConstant())) {
									throw new InvalidInputStringException(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getConstant());
								}							
							}

							digit = parser.getWordValue(numbers[i]);

							if (digit == NUMERIC_HUNDRED) {
//								sumResult contained the number before hundred, from the previous iteration, and overrides it, with the hundred value
								sumResult = sumResult * digit;
							} else {
								sumResult += digit;
							}

						} catch (InvalidInputStringException e) {
							console.printResult(e.getMessage());
							return;
						} catch (NumberOutOfRangeException e) {
							console.printResult(e.getMessage());
							return;
						}

					}

//					adds the result of the sum of the second addend to digits
					digits.add(sumResult);
//					result of the sum of the two addends, or the sum of the only addend, in case there is only one
					//String digitString = sum(digits);
					int digitString = sum(digits);
					
					String englishNumber = parser.getLanguageNumber(digitString);

					console.printResult(englishNumber);

				} else {
					try {
//						validates onehundred is invalid
						if (numbers[FIRST_INDEX_POSITION].contains(HUNDRED.getConstant())) {
							String unit = input.substring(FIRST_INDEX_POSITION, input.indexOf(HUNDRED.getConstant()));
							String hundred = input.substring(input.indexOf(HUNDRED.getConstant()));
							if (isEnglish(unit) && isEnglish(hundred)) {
								throw new InvalidInputStringException(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getConstant());
							}	
//						validates fortyfour is invalid
						} else if (numbers[FIRST_INDEX_POSITION].contains(ENGLISH_TEN_ENDING.getConstant())) {
							String unit = input.substring(input.indexOf(ENGLISH_TEN_ENDING.getConstant()) + ENGLISH_TEN_ENDING_LENGTH);
							String ten = input.substring(FIRST_INDEX_POSITION, input.indexOf(unit));
							if (isEnglish(unit) && isEnglish(ten)) {
								throw new InvalidInputStringException(ERROR_MESSAGE_WHEN_INPUT_STRING_INVALID.getConstant());
							}							
						} else {
							throw new NumberOutOfRangeException(ERROR_MESSAGE_WHEN_NUMBER_OUT_OF_RANGE.getConstant());
						}
					} catch (InvalidInputStringException e) {
						console.printResult(e.getMessage());
						return;
					} catch (NumberOutOfRangeException e) {
						console.printResult(e.getMessage());
						return;
					}
				}
			}
		}
	}

	public String getInput() {
		return console.scanInput();
	}

	public boolean isDigit(String input) {
		char firstChar = input.replaceAll(REGEX_DASH.getConstant(), EMPTY_STRING.getConstant())
												 .charAt(FIRST_INDEX_POSITION);
		return Character.isDigit(firstChar);
	}

	public boolean isSpanish(String number) {
		return Arrays.stream(SpanishNumbers.class.getEnumConstants())
				.anyMatch(e -> e.name().equals(number.toUpperCase()));
	}

	private boolean isEnglish(String number) {
		return Arrays.stream(EnglishNumbers.class.getEnumConstants())
				.anyMatch(e -> e.name().equals(number.toUpperCase()));
	}
	
	public int sum(List<Integer> digits) {
		return digits.stream().reduce(NONE, (a, b) -> a + b);
	}

}*/

/*public boolean isTenUnitSeparation(String number, String tenUnitSeparation) {
		if (number.equals(tenUnitSeparation)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void isTenFollowedByTenUnitSeparation(String number, String tenUnitSeparation) throws InvalidInputString {
		if (!number.equals(tenUnitSeparation)) {
			throw new InvalidInputString();
		}
	}*/
