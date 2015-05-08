package com.azikri.app.model;

import com.azikri.app.model.Stock;

public class Portfolio {
	
	public int getPortfolioSize() {
		return portfolioSize;
	}
	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	private static final int MAX_PORTFOLIO_SIZE=5;

	private String name;
	private int stockIndex;
	private Stock[] stocks;
	private int portfolioSize=0;
	
	public Portfolio() {
		stocks = new Stock [MAX_PORTFOLIO_SIZE];
	}
	
	public Portfolio(String name, int stockIndex) {// Constructor creation
		this();
		this.name=name;
		this.stockIndex=stockIndex;
	}
	
	public Portfolio(String name, int stockIndex,Stock[]stocks) {
		this(name,stockIndex);
		this.stocks=stocks;
	}
	/* COPY C'TOR of Portfolio */
	public Portfolio (Portfolio portfolio){
		this(new String(portfolio.getName()),portfolio.portfolioSize);
		
	   
		for(int i=0; i< portfolio.getPortfolioSize(); i++)
		{
			this.addStock(new Stock(portfolio.getStockByIndex(i)));
			
		}
	
	
	}
		
	public void addStock (Stock stock){ // add stock to the Portfolio
		
		if(stocks != null && stockIndex < MAX_PORTFOLIO_SIZE) {
			this.stocks[stockIndex] = stock;
			stockIndex++;
		} else {
			System.out.println("Sorry, portfolio is full, or stock is null!");	
		}
	}
	
	public void removeStock(String deleteSymbol){ // remove stock from the portfolio
		if (stocks[portfolioSize-1].getSymbol().equals(deleteSymbol))
		{
			stocks[portfolioSize-1] = null;
			portfolioSize--;
		}
		else
		{
			for (int i=0; i < portfolioSize; i++)
			{
				if (this.stocks[i].getSymbol().equals(deleteSymbol))
				{
					this.stocks[i] = this.stocks[portfolioSize-1];
					this.stocks[portfolioSize-1] = null;
					portfolioSize--;
				}
			}
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
	
	public Stock getStockByIndex(int index) {
		if (index < 0 || index >= portfolioSize) {
			return null;
		}
		return stocks[index];
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = stocks;
	}
}