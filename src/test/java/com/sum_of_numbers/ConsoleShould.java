package com.sum_of_numbers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsoleShould {
	
	private Console console;
	
	@Before
	public void setUp() {
		console = new Console();
	}
	
	@Test
	public void scan_the_system_input() {
		mockSystemIn("-1");
		
		String actual = console.scanInput();
		
		assertEquals("-1", actual);
	}
	
	private void mockSystemIn(String input) {
		InputStream mockedInput;
		mockedInput = new ByteArrayInputStream(input.getBytes());
		System.setIn(mockedInput);
	}
	
}
