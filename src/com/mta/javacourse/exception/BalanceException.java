package com.mta.javacourse.exception;
/*
 * Exception while balance is negative
 */
public class BalanceException extends Exception{
	
	public BalanceException() {
		super("Your balance is negative, therefore you can't purchase more stocks!");
	}

}
