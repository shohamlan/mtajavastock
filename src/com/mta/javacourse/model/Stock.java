package com.mta.javacourse.model;

import java.util.Date;

/*
 * must change to protected because using extends in stockstatus
 */

public class Stock {
	
	protected String symbol;
	protected float Ask;
	protected float Bid;
	protected java.util.Date Date;
	
	/**
	 * copy constructor
	 * @param stocks 
	 */

	public Stock(Stock stock){
		this(stock.getSymbol(),stock.getAsk(),stock.getBid(),new Date(stock.getDate().getTime()));
	}
	
	/*
	 * constructor 
	 */
	
	public Stock(){
		
		symbol = "";
		Ask = 0;
		Bid = 0;
		Date = new Date();
		 
	}

	/**
	 * constructor
	 * @param stocks 
	 */

	public Stock(String stockSymbol1, float ask1, float bid1, Date date) {
		if(stockSymbol1 != null)
		{
			setSymbol(stockSymbol1);
			setAsk(ask1);
			setBid(bid1);
			setDate(date);
		}
	}
	
	/*
	 * getters & setters
	 */
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public float getAsk() {
		return Ask;
	}
	public void setAsk(float ask) {
		Ask = ask;
	}
	public float getBid() {
		return Bid;
	}
	public void setBid(float bid) {
		Bid = bid;
	}
	public java.util.Date getDate() {
		return Date;
	}
	public void setDate(java.util.Date date) {
		Date = date;
	}
	public String getHtmlDescription(){
		String stockDescription=" <b> Stock symbol </b> : " +getSymbol()+ " <b> ask </b> : " +getAsk()+ " <b> Bid </b> : " +getBid()+ " <b> date </b> : " +Date.getDate()+ "/" +Date.getMonth()+ "/" +Date.getYear(); 
		return stockDescription;
	}
	
}
