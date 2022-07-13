package com.sum_of_numbers;

import static com.sum_of_numbers.Constant.SUM_ACTION;

public class CalculatorProgram {

	public static void main(String[] args) {
		
	    Console console = new Console();
	    Validate validate = new DigitsValidation();
		Calculate calculate = new Calculate(console, validate);
		
		calculate.getInput();
		String action = calculate.getAction();
				
		if (action.equals(SUM_ACTION.getvalue())) {
			ICalculateOperation addOperation = new AddOperation();
			calculate.setCalculateOperation(addOperation);
		}
		
		calculate.validateSumString();
		calculate.passOutput();
	}
	
}
