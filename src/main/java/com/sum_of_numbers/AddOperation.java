package com.sum_of_numbers;
// AddOperation, a low-level module, depends on the CalculateOperation interface, the abstraction
public class AddOperation implements ICalculateOperation {

	private LanguageStringFactory languageStringFactory;

	public AddOperation(LanguageStringFactory languageStringFactory) {
		this.languageStringFactory = languageStringFactory;
	}
	
	public AddOperation() {}

	@Override
	public String calculate(String[] numbers) {
		return languageStringFactory.sum(numbers);
	}
	
	@Override
	public void setLanguageStringFactory(LanguageStringFactory languageStringFactory) {
		this.languageStringFactory = languageStringFactory;
	}
	
	public LanguageStringFactory getLanguageStringFactory() {
		return languageStringFactory;
	}

}

