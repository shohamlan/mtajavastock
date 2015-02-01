package com.mta.javacourse.exception;

/*
 * Exception while portfolio is full
 */
public class PortfolioFullException extends Exception {
	
	public PortfolioFullException() {
		super("You had reached maximum portfolio size!");
	}

}
