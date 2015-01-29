package com.mta.javacourse.model;

import com.mta.javacourse.model.Portfolio.ALGO_RECOMMENDATION;

/*
 * new class of stock status that extends stock. we  don't need to write here the stocks parameters 
 */

public class StockStatus extends Stock {
	
	private ALGO_RECOMMENDATION recommendation;
	private int stockQuantity;
	
	/*
	 * 2 inner classes for StockStatus using super() for stock parameters
	 */
	public StockStatus(){
		
		super();
		recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
		stockQuantity = 0;
	}

	public StockStatus(StockStatus stockStatus){
		
		super(stockStatus);
		recommendation = stockStatus.getRecommendation();
		stockQuantity = stockStatus.getStockQuantity();
		
	}

	/*
	 * getters & setters of StockStatus parameters (not including stock parameters)
	 */
	
	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
		this.recommendation = recommendation;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
}