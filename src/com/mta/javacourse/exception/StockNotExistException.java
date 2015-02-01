package com.mta.javacourse.exception;

/*
 * Exception while the stock doesn't exists
 */
public class StockNotExistException extends Exception {

	public StockNotExistException (String symbol) {
		super("Stock " + symbol + " doesn't exists!");
	}
}
