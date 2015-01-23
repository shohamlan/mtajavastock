package com.mta.javacourse.service;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;

public class PortfolioService {
	
	public Portfolio getPortfolio(){
		
		Portfolio myPortfolio = new Portfolio();
		java.util.Date currentDate= new java.util.Date();
		
		myPortfolio.setBalance(10000);
		
		Stock stock1 = new Stock("PIH",12.4f,13.1f,currentDate);
		Stock stock2= new Stock("AAL",5.5f,5.78f,currentDate);
		Stock stock3= new Stock("CAAS",31.5f,31.2f,currentDate);
		 
		myPortfolio.addStock(stock1);
 		myPortfolio.addStock(stock2);
 		myPortfolio.addStock(stock3);
	
 		myPortfolio.buyStock("PIH",20);
		myPortfolio.buyStock("AAL",30);
		myPortfolio.buyStock("CAAS",40);
		
 		myPortfolio.sellStock("AAL", -1);
 		myPortfolio.removeStock("CAAS");
 		
		currentDate.setDate(15);
		currentDate.setMonth(12);
		currentDate.setYear(2014);
		
		
		myPortfolio.setTitle(" <h1> Exercise 7 portfolio </h1> ");
		
		return myPortfolio;
	}

}
