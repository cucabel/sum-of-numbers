package com.sum_of_numbers;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class AddOperationShould {
	
	@InjectMocks
	private AddOperation addOperation;
	@Mock
	private LanguageStringFactory languageStringFactory;
	
	@Test
	public void set_the_language_string_factory() {
		addOperation = (new AddOperation());
		LanguageStringFactory spanishStringFactory = new SpanishStringFactory();		
		
		addOperation.setLanguageStringFactory(spanishStringFactory);
		
		LanguageStringFactory languageStringFactory = addOperation.getLanguageStringFactory();
		assertEquals(languageStringFactory, spanishStringFactory);
	}
	
	@Test
	public void sum_the_numbers() {
		addOperation = (new AddOperation(languageStringFactory));
		String[] numbers = {"cinco"};
		when(languageStringFactory.sum(Mockito.any(String[].class))).thenReturn("cinco");
		
		addOperation.calculate(numbers);
		
		verify(languageStringFactory).sum(numbers);
	}

}
