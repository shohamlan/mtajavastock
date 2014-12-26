package com.mta.javacourse.model;

import java.util.Date;

public class Portfolio {
	private String title;
	private final static int MAX_PORTFOLIO_SIZE=5;
	private Stock[] stocks;
	private StockStatus[] stocksStatus;
	private int portfolioSize;
	private float balance = 0;
	private int stockStatusSize;
	
	private static enum  ALGO_RECOMMENDATION{DO_NOTHING, BUY, SELL};
	
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
		
		return stocks;
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
			stocksValue += stocksStatus[i].currentBid*stocksStatus[i].stockQuantity;
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

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}

	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	public void setStocksStatus(StockStatus[] stocksStatus) {
		this.stocksStatus = stocksStatus;
	}

	//constructor
	public Portfolio(){
		
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
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
			stocks[i] = new Stock(portfolio.getStocks()[i]);
			stocksStatus[i] = new StockStatus(portfolio.getStocksStatus()[i]);
		}
	}

	/**
	 * constructor
	 * @param portfolio
	 */

	public Portfolio(String title1,Stock[] stocks1,StockStatus[] stockStatus1,int portfolioSize1){
		setTitle(title1);
		setStocks(stocks1);
		setStocksStatus(stockStatus1);
		setPortfolioSize(portfolioSize1);
	}

	
	public class StockStatus{
		private String symbol;
		private float currentBid;
		private float currentAsk;
		private Date date;
		private ALGO_RECOMMENDATION recommendation;
		private int stockQuantity;
		
		/** copy constructor
		 * @param StockStatus
		 */

		public StockStatus(){

		}

		/**
		 * constructor
		 * @param StockStatus
		 */

		public StockStatus(StockStatus stockStatus){
			
				this.symbol = stockStatus.symbol;
				this.currentAsk = stockStatus.currentAsk;
				this.currentAsk = stockStatus.currentBid;
				this.date = stockStatus.date;
				this.recommendation = stockStatus.recommendation;
				this.stockQuantity = stockStatus.stockQuantity;
			
		}

		//getters:

		public String getSymbol() {
			return symbol;
		}

		public float getCurrentBid() {
			return currentBid;
		}

		public float getCurrentAsk() {
			return currentAsk;
		}

		public Date getDate() {
			return date;
		}

		public ALGO_RECOMMENDATION getRecommendation() {
			return recommendation;
		}

		public int getStockQuantity() {
			return stockQuantity;
		}

		//setters:

		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public void setCurrentBid(float currentBid) {
			this.currentBid = currentBid;
		}

		public void setCurrentAsk(float currentAsk) {
			this.currentAsk = currentAsk;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
			this.recommendation = recommendation;
		}

		public void setStockQuantity(int stockQuantity) {
			this.stockQuantity = stockQuantity;
		}

	}

	/*
	 * method that update the balance
	 */
	public void updateBalance(float amount){
		this.balance += amount;
	}
	
	public void addStock(Stock stock){
		int i = 0;
		while(stocks[i]!=null)
		{
			if(stock.getSymbol().equals(stocks[i].getSymbol()))
			{
				System.out.println("You already own this kind of stock , no need to add the stock!");
				return;
			}
			i++;
		}
		if(portfolioSize >= MAX_PORTFOLIO_SIZE)
		{
			System.out.println(" Can't add new stock, portfolio can have only "+MAX_PORTFOLIO_SIZE+" stocks");
		}
		else
		{
			stocks[portfolioSize] = stock;
			portfolioSize++;

			StockStatus stockStatus = new StockStatus();
			stockStatus.currentAsk = stock.getAsk();
			stockStatus.currentBid = stock.getBid();
			stockStatus.symbol = stock.getSymbol();
			stockStatus.stockQuantity = 0;
			stockStatus.date = new Date(stock.getDate().getTime());
			stockStatus.recommendation = ALGO_RECOMMENDATION.DO_NOTHING;

			this.stocksStatus[stockStatusSize] = stockStatus;
			stockStatusSize++;

		}
	}
	
	/*
	 * remove stock method. must take care about the item i delete
	 */
	
	public boolean removeStock (String stockSymbol){
	
		for(int i = 0; i<portfolioSize; i++){
			
			if(stockSymbol.equals(this.stocks[i].getSymbol())){
				
				sellStock(this.stocks[i].getSymbol(),this.stocksStatus[i].getStockQuantity());
				
				this.stocks[i] = null;
				
				if(i == portfolioSize){
					
					this.portfolioSize--;
					
				}
				
				else{
					
					for(int j = i; j<portfolioSize; j++){
						
						this.stocks[j] = this.stocks[j+1];
						this.stocksStatus[j] = this.stocksStatus[j+1];
					}
					this.portfolioSize--;
				}
				System.out.println("The stock "+stockSymbol+" was removed successfully");
				return true;
			}
		}
		System.out.println("The stock "+stockSymbol+" dosent exist in the portfolio");
		return false;
	}
	
	/*
	 *	sell stock.
	 *  method should get symbol & quantity 
	 *  true=sold, false=impossible
	 */
	
	public boolean sellStock(String symbol,int quantity){
		
		for(int i = 0; i<portfolioSize; i++){
			
			if(this.stocks[i].getSymbol().equals(symbol)){
				
					if(quantity == -1){
						
						updateBalance(stocksStatus[i].getStockQuantity() * this.stocksStatus[i].getCurrentBid());
						System.out.println(stocksStatus[i].getStockQuantity() + " Stocks of " +symbol+ " were sold"); 
						this.stocksStatus[i].setStockQuantity(0);
						
					}
					
					else if(quantity > stocksStatus[i].getStockQuantity()){
						
						System.out.println("Not enough stocks to sell");
				
					}
					
					else if(quantity > 0){
						
						this.stocksStatus[i].setStockQuantity(this.stocksStatus[i].getStockQuantity() - quantity);
						updateBalance(quantity * this.stocksStatus[i].getCurrentBid());
						System.out.println(quantity + " Stocks of " +symbol+ " were sold"); 
					
					}
					
					else{      //quantity is lower then -1 
					
						System.out.println("Cant delete a negative number of quantity");
						return false;
					}
					
					return true;
					
			}
			
		}
		
		return false;
		
	}
	
	/*
	 * buy stock
	 */
	
	public boolean buyStock(String symbol, int quantity){
		
		for(int i = 0; i<portfolioSize; i++){
		
			if(this.stocks[i].getSymbol().equals(symbol)){
		
				if(quantity == -1)
				{
					
					//float res = getBalance() /(stocksStatus[i].getCurrentAsk());
					
					quantity = (int)(getBalance() /(stocksStatus[i].getCurrentAsk()))
							;
					this.stocksStatus[i].stockQuantity +=  quantity;
					updateBalance(-(quantity * this.stocksStatus[i].getCurrentAsk()));
					System.out.println(this.stocksStatus[i].getStockQuantity() + " Stocks of " +symbol+ " were bought"); 
				}
				

				else if(quantity > 0){
					
					
					if(quantity * this.stocksStatus[i].getCurrentAsk() <= getBalance())
					{	
						this.stocksStatus[i].setStockQuantity(this.stocksStatus[i].getStockQuantity() + quantity);
						updateBalance(-(quantity * this.stocksStatus[i].getCurrentAsk()));
						System.out.println(this.stocksStatus[i].getStockQuantity() + " Stocks of " +symbol+ " were bought"); 
					}

					else
						System.out.println("Not enough balance to complete purchase"); 

				}
				
				else //lower then -1 
				{
					System.out.println("Cant delete a negative number of quantity");
					return false;
				}
				
				return true;
			}
			
		}
		
		return false;
		
	}
		
	public void removeStock(int index){
		if(index == portfolioSize)
			this.portfolioSize--;
		else 
		{
			this.portfolioSize--;
			for(int i = index; i <= portfolioSize-1; i++)
			{
				this.stocks[i] = this.stocks[i+1];
			}
		}
	}
	
	public String getHtmlPortfolio(){
		String getHtmlPortfolio = getTitle();
		for(int i=0; i<portfolioSize; i++)
		{
			getHtmlPortfolio += stocks[i].getHtmlDescription() + "<br>";
		}
		getHtmlPortfolio += "<br>"+"Total Portfolio Value: "+getTotalValue()
				+"$, "+"<br>"+"Total Stocks value:" + getStocksValue()
				+"$, "+"<br>"+"Balance: "+getBalance()+"$"+"<br>";
		return getHtmlPortfolio;
	}
}