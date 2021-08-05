package com.mycompany.myapp.service;

import java.text.MessageFormat;

public class CalculadorResultadoPruebaException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CalculadorResultadoPruebaException(String message, Object... args) {
		super(MessageFormat.format(message, args));
	}
	
	public CalculadorResultadoPruebaException(Throwable e, String message, Object... args) {
		super(MessageFormat.format(message, args), e);
	}


}
