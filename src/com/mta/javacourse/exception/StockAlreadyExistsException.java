package com.mta.javacourse.exception;

/*
 * Exception while the stock is already exists
 */
public class StockAlreadyExistsException extends Exception {

	public StockAlreadyExistsException(String symbol) {
		super("Sorry, the stock " + symbol + " is already exists in the portfolio!");
	}
}
