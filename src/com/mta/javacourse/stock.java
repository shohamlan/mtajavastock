package com.mta.javacourse;

public class stock {
	private String symbol;
	private float Ask;
	private float Bid;
	private java.util.Date Date;
	
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
