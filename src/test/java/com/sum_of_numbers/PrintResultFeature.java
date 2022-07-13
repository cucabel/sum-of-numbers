package com.sum_of_numbers;

import static com.sum_of_numbers.Constant.SUM_ACTION;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PrintResultFeature {
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
				{ "some number is out of range (0-999)", "1000" },
				{ "some number is out of range (0-999)", "-1" },
				{ "some number is out of range (0-999)", "-1 + 1" },
				{ "some number is out of range (0-999)", "999 + 1000" },
				{ "some number is out of range (0-999)", "-1 + 1000" },

				{ "some number is out of range (0-999)", "mil" },
				{ "some number is out of range (0-999)", "menos uno" },

				{ "some number is out of range (0-999)", "one thousand" },
				{ "some number is out of range (0-999)", "minus one" },

				{ "invalid string", "onehundred" },
				{ "invalid string", "fortyfour" },
				{ "invalid string", "forty - four" },

				{ "invalid string", "cincuenta uno" }, 
				{ "invalid string", "tres cientos" },

				{ "0", "0" }, 
				{ "1", "1" }, 
				{ "2", "2" }, 
				{ "101", "101" }, 
				{ "215", "215" }, 
				{ "345", "345" },

				{ "1", "0 + 1" }, 
				{ "1000", "999 + 1" }, 
				{ "1998", "999 + 999" },

				{ "cinco", "cinco" }, 
				{ "veintinueve", "veintinueve" },
				{ "cuarenta", "cuarenta" },
				{ "cincuenta", "cincuenta" },
				{ "cuatrocientos", "cuatrocientos" }, 
				{ "cuarenta y cinco", "cuarenta y cinco" }, 
				{ "cien", "cien" },
				{ "ciento uno", "ciento uno" }, 
				{ "doscientos quince", "doscientos quince" },
				{ "trescientos cuarenta y cinco", "trescientos cuarenta y cinco" },

				{ "uno", "cero mas uno" }, 
				{ "novecientos noventa y nueve", "novecientos noventa y ocho mas uno" },
				
				{ "five", "five" }, 
				{ "one", "zero plus one" }, 
				{ "thirty", "thirty" }, 
				{ "forty", "forty" },
				{ "forty-five", "forty-five" },
				{ "one hundred one", "one hundred one" },
				{ "two hundred fifteen", "two hundred fifteen" },
				{ "three hundred forty-five", "three hundred forty-five" },

				{ "nine hundred ninety-nine", "nine hundred ninety-eight plus one" },
		});
	}
    
    private Console console = new Console();
    private Validate validate = new DigitsValidation();
    
    private final Calculate calculate;
    private final String result;
    private final String sum;
    
	public PrintResultFeature(String result, String sum) {
		this.calculate = new Calculate(console, validate);
		this.result = result;
		this.sum = sum;
	}
	
	@Test
	public void print_sum_result () throws IOException, NumberOutOfRangeException {
		
		mockSystemIn(sum);
		mockSystemOut(result);
		
		calculate.getInput();
		String action = calculate.getAction();
		
		if (action.equals(SUM_ACTION.getvalue())) {
			ICalculateOperation addOperation = new AddOperation();
			calculate.setCalculateOperation(addOperation);
		}
		
		calculate.validateSumString();
		calculate.passOutput();
		
		assertEquals(result, console.getOutput());
	}
	
	private void mockSystemIn(String input) {
		InputStream mockedInput;
		mockedInput = new ByteArrayInputStream(input.getBytes());
		System.setIn(mockedInput);
	}
	
	private void mockSystemOut(String output) throws IOException {
		OutputStream mockedOutput = new ByteArrayOutputStream();
		mockedOutput.write(output.getBytes());
		System.setOut(new PrintStream(mockedOutput));
	}

}
