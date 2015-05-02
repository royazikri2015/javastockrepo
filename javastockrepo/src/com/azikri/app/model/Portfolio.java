package com.azikri.app.model;

import com.azikri.Stock;

public class Portfolio {
	
	private static final int MAX_PORTFOLIO_SIZE=5;

	private String name;
	private int stockIndex;
	private Stock[] stocks;
	
	public Portfolio() {
	
		stocks= new Stock [MAX_PORTFOLIO_SIZE];
	}
		
	public void addStock (Stock stock){
		
		if(stocks != null && stockIndex < MAX_PORTFOLIO_SIZE) {
			this.stocks[stockIndex] = stock;
			stockIndex++;
		} else {
			System.out.println("Sorry, portfolio is full, or stock is null!");	
		}
	}
	
public String getHtmlString() {
		
		String ret = new String( "<h1>" + getName() + "</h1>" );
		
		for(int i = 0; i < stockIndex ;i++) {
			Stock current = this.stocks[i];
			ret += current.getHtmlDescription() + "<br>";
		}
		
		return ret;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public Stock[] getStocks() {
		return stocks;
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
}