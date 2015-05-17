package com.azikri.app.model;

import com.azikri.*;
import com.azikri.app.model.Stock;

public class Portfolio {
	
	private String name;
	private Stock[] stocks;
	private int portfolioSize;
	private float balance;

	private ALGO_RECOMMENDATION algo;
	
	public int getPortfolioSize() {
		return portfolioSize;
	}
	public void setPortfolioSize(int portfolioSize) {
		this.portfolioSize = portfolioSize;
	}

	private static final int MAX_PORTFOLIO_SIZE=5;

		public Portfolio() {
		stocks = new Stock [MAX_PORTFOLIO_SIZE];
		portfolioSize = 0;
		balance=0;
	}
	
	public Portfolio(String name, int stockIndex, ALGO_RECOMMENDATION toSet) {// Constructor creation
		this();
		this.name=name;
		this.algo=toSet;
		this.balance=balance;
		
	}
	
	public Portfolio(String name, int stockIndex,Stock[]stocks,ALGO_RECOMMENDATION toSet ) {
		this(name,stockIndex, toSet);
		this.stocks=stocks;
	}
	/* COPY C'TOR of Portfolio */
	public Portfolio (Portfolio portfolio,ALGO_RECOMMENDATION toSet){
		this(new String(portfolio.getName()),portfolio.portfolioSize, toSet);
		
	   
		for(int i=0; i < portfolio.getPortfolioSize(); i++)
		{
			this.addStock(new Stock(portfolio.getStockByIndex(i)));
			}
		}
		
	public void addStock (Stock stock){ // add stock to the Portfolio
		
		String returnValue= null;
		boolean exist= false;
		
		if (this.getPortfolioSize() == MAX_PORTFOLIO_SIZE) // if there's room
		{
			returnValue= "Portfolio is full";
			System.out.println(returnValue);
			return;
		}

		for (int i=0; i < portfolioSize; i++){

			if (this.stocks[i].getSymbol().equals(stock.getSymbol()))
			{
				exist = true;
				returnValue = "Stock already exists";
				System.out.println(returnValue);
				return;
			}
		}
		if (exist == false) 
		{
			this.stocks[portfolioSize] = new Stock(stock.getSymbol(), stock.getAsk(), stock.getBid(),stock.getDate=(),stock.getStockQuantity());
			portfolioSize++;
			returnValue = "Added a stock";
			System.out.println(returnValue);	
		}
	}
	
	public boolean sellStock(String symbol, int quantity){ // sell stocks

		if(symbol == null || quantity < -1){
			System.out.println("sorry, the stock symbol or stock quantity are wrong");
			return false;
		}

		int i = this.searchStock (symbol);

		if(i>-1){	
			if(this.stocks[i].getStockQuantity() - quantity < 0){
				System.out.println("You don't have enough stocks to sell");
				return false;

			}else if(quantity == -1){
				this.updateBalance(this.stocks[i].getStockQuantity()*this.stocks[i].getBid());
				this.stocks[i].setStockQuantity(0);
				System.out.println("the quantity you wanted was sold");
				return true;
			}
			else{

				this.updateBalance(quantity * this.stocks[i].getBid());
				this.stocks[i].setStockQuantity(stocks[i].getStockQuantity() - quantity);
				System.out.println("the wanted amount was sold");
				return true;
			}
		}
		System.out.println("the stock is not in the Portfolio");
		return false; 
	}
	
	public boolean removeStock(String deleteSymbol){ // remove stock from the portfolio
		
		if (deleteSymbol == null){
			System.out.println("Error, stock is null");
			return false;
		}

		int i = this.searchStock (deleteSymbol);

		if(i>-1){
			if (portfolioSize > 1){

				this.sellStock(stocks[i].getSymbol(), -1);
				stocks[i] = stocks[this.portfolioSize-1];
				stocks[this.portfolioSize-1]=null;	
			}
			else  if (this.portfolioSize == 1){

				this.sellStock(stocks[i].getSymbol(), -1);
				stocks[i] = null;
			}

			portfolioSize--;
			System.out.println("the requested stock is deleted");
			return true;
		}

		System.out.println("Stock is NOT inside this portfolio");
		return false;
		
		
	}
	
	public boolean buyStock(Stock stock, int quantity) // buy stocks
	{
		if(stock == null || quantity < -1){
			System.out.println("Error, stock symbol or stock quntity are incorrect");
			return false;
		}
		if(quantity*stock.getAsk() > this.balance){
			System.out.println("Not enough balance to complete purchase.");
			return false;
		}

		int i = this.searchStock (stock.getSymbol());

		if(i>-1){
			if(quantity == -1){
				int howManyToBuy = (int)this.balance/(int)this.stocks[i].getAsk();
				this.updateBalance(-howManyToBuy*this.stocks[i].getAsk());
				this.stocks[i].setStockQuantity(this.stocks[i].getStockQuantity()+howManyToBuy);
				System.out.println("The requested stock quantity, that could be bought, was succefully bought");
				return true;
			}
			else{
				this.updateBalance(-quantity*this.stocks[i].getAsk());
				this.stocks[i].setStockQuantity(stocks[i].getStockQuantity()+quantity);
				System.out.println("The requested amount was bought");
				return true;
			}
		}


		if(i == MAX_PORTFOLIO_SIZE-1){
			System.out.println("Portfolio has no room...");
			return false;
		}

		if (quantity == -1){
			this.addStock(stock);
			int toBuy = (int)this.balance/(int)this.stocks[i].getAsk();
			this.updateBalance(-(toBuy*this.stocks[this.portfolioSize-1].getAsk()));
			this.stocks[i].setStockQuantity(this.stocks[this.portfolioSize-1].getStockQuantity()+toBuy);
			System.out.println("The requested stock quantity, that could be bought, was succefully bought");
			return true;
		} 

		else{
			
			this.addStock(stock);
			this.updateBalance(-quantity*this.stocks[portfolioSize -1].getAsk());
			this.stocks[this.portfolioSize -1].setStockQuantity(quantity);
			System.out.println("Stock was added  to the portfolio");
			return true;

		}
	}

	private int searchStock (String stockToFind){
		for(int i = 0; i< this.portfolioSize; i++){
			if(stockToFind.equals(this.stocks[i].getSymbol())){
				return i;
			}
		}
		return -1;
	}

	public void updateBalance(float amount){ //update the balance of the account
		
		if( balance + amount >= 0 ){
			
			this.balance+= amount;
		}
		else {System.out.println("Sorry, this action is NOT available, the balance must be positive!");	
		}
		}
	
	public String getHtmlString() {
		
		String ret = new String( "<h1>" + getName() + "</h1>" );
		
		for(int i = 0; i < portfolioSize ;i++) {
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
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
}