package com.mta.javacourse.service;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;

public class PortfolioService {
	
	public Portfolio getPortfolio(){
		Portfolio myPortfolio = new Portfolio();
		java.util.Date currentDate= new java.util.Date();
		
		Stock stock1 = new Stock("PIH",12.4f,13.1f,currentDate);
		Stock stock2= new Stock("AAL",5.5f,5.78f,currentDate);
		Stock stock3= new Stock("CAAS",31.5f,31.2f,currentDate);
		 
		
		currentDate.setDate(15);
		currentDate.setMonth(11);
		currentDate.setYear(2014);
		
		
		myPortfolio.addStock(stock1);
		myPortfolio.addStock(stock2);
		myPortfolio.addStock(stock3);
		
		myPortfolio.setTitle(" <h1> Portfolio 1# </h1> ");
		
		return myPortfolio;
	}

}
