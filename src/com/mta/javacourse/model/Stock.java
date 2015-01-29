package com.mta.javacourse.model;

import java.util.Date;
/*
 * must change to protected because using extends in stockstatus
 */

public class Stock {
	
	protected String symbol;
	protected float ask;
	protected float bid;
	protected Date date;;
	
	/**
	 * copy constructor
	 * @param stocks 
	 */

	public Stock(Stock stock ) {
		setSymbol(stock.getSymbol());
		setAsk(stock.getAsk());
		setBid(stock.getBid());
		date = new Date();
		setDate(new Date(stock.date.getTime()));
	}
	
	/*
	 * constructor 
	 */
	
	public Stock(){
		
		symbol = "";
		ask = 0;
		bid = 0;
		date = new Date();
		 
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
		return ask;
	}
	public void setAsk(float ask) {
		this.ask = ask;
	}
	public float getBid() {
		return bid;
	}
	public void setBid(float bid) {
		this.bid = bid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getHtmlDescription(){
		String stockDescription=" <b> Stock symbol </b> : " +getSymbol()+ " <b> ask </b> : " +getAsk()+ " <b> Bid </b> : " +getBid()+ " <b> date </b> : " + getDate();
		return stockDescription;
	}
	
}