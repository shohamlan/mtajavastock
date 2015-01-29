package com.mta.javacourse.model;

import java.util.Date;

import com.mta.javacourse.exception.BalanceException;
import com.mta.javacourse.exception.PortfolioFullException;
import com.mta.javacourse.exception.StockAlreadyExistsException;
import com.mta.javacourse.exception.StockNotExistException;

public class Portfolio {
	
	private String title;
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private StockStatus[] stocksStatus;
	private int portfolioSize;
	private float balance = 0;
	private int stockStatusSize;
	
	public static enum  ALGO_RECOMMENDATION{DO_NOTHING, BUY, SELL};
	
	//getters:

	public float getBalance() {
		return balance;
	}

	public String getTitle() {
		return title;
	}

	public int getPortfolioSize(){
		return portfolioSize = 0;
	}

	public StockStatus[] getStocksStatus() {
		return stocksStatus;
	}
	
	public Stock[] getStocks(){
		
		return stocksStatus;
	}
	
	/*
	 * get the stock value
	 */

	public float getStocksValue()
	{
		float stocksValue = 0;
		int i = 0;
		while(stocksStatus[i] != null)
		{
			stocksValue += stocksStatus[i].bid*stocksStatus[i].getStockQuantity();
			i++;
		}
		return stocksValue;
	}
	
	/*
	 * get the total value
	 */
	
	public float getTotalValue()
	{
		return getBalance() + getStocksValue();
	}

	//setters:

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.stocksStatus = stocksStatus;
	}

	//constructor
	
	public Portfolio(){
		
		stocksStatus = new StockStatus[MAX_PORTFOLIO_SIZE];
		
	}

	/**
	 * copy constructor
	 * @param portfolio
	 */

	public Portfolio(Portfolio portfolio){
		this();
		this.title = portfolio.getTitle();
		this.portfolioSize = portfolio.portfolioSize;
		this.stockStatusSize = portfolio.stockStatusSize;

		for(int i = 0; i < portfolio.portfolioSize ; i++){
			
			stocksStatus[i] = new StockStatus(portfolio.getStocksStatus()[i]);
			
		}
	}

	
	/*
	 * method that update the balance
	 */
	public void updateBalance(float amount){
		balance += amount;
	}
	
	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException{
		int i = 0;
		while(stocksStatus[i]!=null)
		{
			if(stock.getSymbol().equals(stocksStatus[i].getSymbol()))
			{
				System.out.println("You already own this kind of stock , no need to add the stock!");
				throw new StockAlreadyExistsException(stock.getSymbol());
			}
			i++;
		}
		if(portfolioSize >= MAX_PORTFOLIO_SIZE)
		{
			System.out.println(" Can't add new stock, portfolio can have only "+MAX_PORTFOLIO_SIZE+" stocks");
			throw new PortfolioFullException();
		}
		else
		{
			
			StockStatus stockStatus = new StockStatus();
			stockStatus.setAsk(stock.getAsk());
			stockStatus.setBid(stock.getBid());
			stockStatus.setSymbol(stock.getSymbol());
			stockStatus.setStockQuantity(0);
			stockStatus.date = new Date(stock.getDate().getTime());
			stockStatus.setRecommendation(ALGO_RECOMMENDATION.DO_NOTHING);

			this.stocksStatus[stockStatusSize] = stockStatus;
			stockStatusSize++;
			portfolioSize++;

		}
	}
	
	/*
	 * remove stock method. must take care about the item i delete
	 */
	
	public void removeStock (String stockSymbol) throws StockNotExistException, PortfolioFullException, BalanceException{
	
		for(int i = 0; i<portfolioSize; i++){
			
			if(stockSymbol.equals(this.stocksStatus[i].getSymbol())){
				
				sellStock(this.stocksStatus[i].getSymbol(),this.stocksStatus[i].getStockQuantity());
				
				this.stocksStatus[i] = null;
				
				if(i == portfolioSize){
					
					this.portfolioSize--;
					
				}
				
				else{
					
					for(int j = i; j<portfolioSize; j++){
						
						this.stocksStatus[j] = this.stocksStatus[j+1];
						this.stocksStatus[j] = this.stocksStatus[j+1];
					}
					this.portfolioSize--;
				}
				System.out.println("The stock "+stockSymbol+" was removed successfully");
			}
		}
		System.out.println("The stock "+stockSymbol+" dosent exist in the portfolio");
		throw new StockNotExistException (stockSymbol);
	}
	
	/*
	 *	sell stock.
	 *  method should get symbol & quantity 
	 *  true=sold, false=impossible
	 */
	
	public void sellStock(String symbol,int quantity) throws BalanceException{
		
		for(int i = 0; i<portfolioSize; i++){
			
			if(this.stocksStatus[i].getSymbol().equals(symbol)){
				
					if(quantity == -1){
						
						updateBalance(stocksStatus[i].getStockQuantity() * this.stocksStatus[i].getBid());
						System.out.println(stocksStatus[i].getStockQuantity() + " Stocks of " +symbol+ " were sold"); 
						this.stocksStatus[i].setStockQuantity(0);
						
					}
					
					else if(quantity > stocksStatus[i].getStockQuantity()){
						
						System.out.println("Not enough stocks to sell");
						throw new BalanceException();
				
					}
					
					else if(quantity > 0){
						
						this.stocksStatus[i].setStockQuantity(this.stocksStatus[i].getStockQuantity() - quantity);
						updateBalance(quantity * this.stocksStatus[i].getBid());
						System.out.println(quantity + " Stocks of " +symbol+ " were sold"); 
					
					}
					
					else{      //quantity is lower then -1 
					
						System.out.println("Cant delete a negative number of quantity");
						/*
						 * there isn't a correct answer here, so i decide to enter as more similar as possible
						 */
						throw new BalanceException();
					}
					
					
					
			}
			
		}
		
		
		
	}
	
	/*
	 * buy stock
	 */
	
	public void buyStock(String symbol, int quantity) throws BalanceException{
		
		for(int i = 0; i<portfolioSize; i++){
		
			if(this.stocksStatus[i].getSymbol().equals(symbol)){
		
				if(quantity == -1)
				{
					
					//float res = getBalance() /(stocksStatus[i].getCurrentAsk());
					
					quantity = (int)(getBalance() /(stocksStatus[i].getAsk()));
					
					int newQ = this.stocksStatus[i].getStockQuantity() + quantity;
					this.stocksStatus[i].setStockQuantity(newQ);
					
					updateBalance(-(quantity * this.stocksStatus[i].getAsk()));
					System.out.println(this.stocksStatus[i].getStockQuantity() + " Stocks of " +symbol+ " were bought"); 
				}
				

				else if(quantity > 0){
					
					
					if(quantity * this.stocksStatus[i].getAsk() <= getBalance())
					{	
						this.stocksStatus[i].setStockQuantity(this.stocksStatus[i].getStockQuantity() + quantity);
						updateBalance(-(quantity * this.stocksStatus[i].getAsk()));
						System.out.println(this.stocksStatus[i].getStockQuantity() + " Stocks of " +symbol+ " were bought"); 
					}

					else
					{
						System.out.println("Not enough balance to complete purchase"); 
						throw new BalanceException();
					}

				}
				
				else //lower then -1 
				{
					System.out.println("Cant buy a negative number of quantity");
				}
				
				
			}
			
		}
		
		
		
	}
		
	public void removeStock(int index){
		if(index == portfolioSize)
			this.portfolioSize--;
		else 
		{
			this.portfolioSize--;
			for(int i = index; i <= portfolioSize-1; i++)
			{
				this.stocksStatus[i] = this.stocksStatus[i+1];
			}
		}
	}
	
	public String getHtmlPortfolio(){
		String getHtmlPortfolio = getTitle();
		for(int i=0; i<portfolioSize; i++)
		{
			getHtmlPortfolio += stocksStatus[i].getHtmlDescription() + "<br>";
		}
		getHtmlPortfolio += "<br>"+"Total Portfolio Value: "+getTotalValue()
				+"$ "+"<br>"+"Total Stocks value:" + getStocksValue()
				+"$ "+"<br>"+"Balance: "+getBalance()+"$"+"<br>";
		return getHtmlPortfolio;
	}
}