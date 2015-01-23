package com.mta.javacourse.model;

import com.mta.javacourse.model.Portfolio.ALGO_RECOMMENDATION;

public class StockStatus extends Stock {
	
	private ALGO_RECOMMENDATION recommendation;
	protected int stockQuantity;
	
	public StockStatus(){
		
		super();
		recommendation = ALGO_RECOMMENDATION.DO_NOTHING;
		stockQuantity = 0;
	}

	public StockStatus(StockStatus stockStatus){
		
		super();
		recommendation = stockStatus.getRecommendation();
		stockQuantity = stockStatus.getStockQuantity();
		
	}

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
