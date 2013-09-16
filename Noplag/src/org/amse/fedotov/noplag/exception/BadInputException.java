package org.amse.fedotov.noplag.exception;

@SuppressWarnings("serial")
public class BadInputException extends RuntimeException {

//	public BadInputException() {
//		super();
//	}
	
	public BadInputException(String message) {
		super(message);
	}

}
