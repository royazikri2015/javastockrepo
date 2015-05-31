package com.azikri.app.model;

import org.algo.model.StockInterface;
import org.algo.model.PortfolioInterface;

//import com.azikri.*;
import com.azikri.app.model.Stock;


public class Portfolio implements PortfolioInterface {
	public enum ALGO_RECOMMENDATION{
		BUY, SELL, REMOVE, HOLD
	}

	private String name;
	private StockInterface[] stocks;
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
		stocks = (StockInterface[]) new Stock [MAX_PORTFOLIO_SIZE];
		portfolioSize = 0;
		balance=0;
	}
	
	public Portfolio(String name, int stockIndex, ALGO_RECOMMENDATION toSet) {// Constructor creation
		this();
		this.name=name;
		this.algo=toSet;
		this.balance=balance;
		
	}
	
	public Portfolio(String name, int stockIndex,StockInterface[] stocks,ALGO_RECOMMENDATION toSet ) {
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
		
	public Portfolio(Stock[] stockArray) {
		// TODO Auto-generated constructor stub
	}
	public Portfolio(Portfolio portfolio) {
		// TODO Auto-generated constructor stub
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
			this.stocks[portfolioSize] = (StockInterface) new Stock(stock.getSymbol(),stock.getAsk(), stock.getBid(),stock.getCreation());
			portfolioSize++;
			returnValue = "Added a stock";
			System.out.println(returnValue);	
		}
	}
	
	public boolean sellStock(String symbol, int quantity){ // sell stocks

		if(symbol == null || quantity < -1){
			System.out.println("Sorry, the stock symbol or stock quantity are wrong");
			return false;
		}

		int i = this.searchStock (symbol);

		if(i>-1){	
			if(((Stock) this.stocks[i]).getStockQuantity() - quantity < 0){
				System.out.println("You don't have enough stocks to sell");
				return false;

			}else if(quantity == -1){
				this.updateBalance(((Stock) this.stocks[i]).getStockQuantity()*this.stocks[i].getBid());
				((Stock) this.stocks[i]).setStockQuantity(0);
				System.out.println("the quantity you wanted was sold");
				return true;
			}
			else{

				this.updateBalance(quantity * this.stocks[i].getBid());
				((Stock) this.stocks[i]).setStockQuantity(((Stock) stocks[i]).getStockQuantity() - quantity);
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

		if (i>-1) {
			if(quantity == -1){
				int howMuchToBuy = (int)this.balance/(int)this.stocks[i].getAsk();
				this.updateBalance(-howMuchToBuy*this.stocks[i].getAsk());
				((Stock) this.stocks[i]).setStockQuantity(((Stock) this.stocks[i]).getStockQuantity()+howMuchToBuy);
				System.out.println("The requested stock quantity, that could be bought, was succefully bought");
				return true;
			}
			else{
				this.updateBalance(-quantity*this.stocks[i].getAsk());
				((Stock) this.stocks[i]).setStockQuantity(((Stock) stocks[i]).getStockQuantity()+quantity);
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
			((Stock) this.stocks[i]).setStockQuantity(((Stock) this.stocks[this.portfolioSize-1]).getStockQuantity()+toBuy);
			System.out.println("The requested stock quantity, that could be bought, was succefully bought");
			return true;
		} 

		else{
			
			this.addStock(stock);
			this.updateBalance(-quantity*this.stocks[portfolioSize -1].getAsk());
			((Stock) this.stocks[this.portfolioSize -1]).setStockQuantity(quantity);
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
			StockInterface current = this.stocks[i];
			ret += ((Stock) current).getHtmlDescription() + "<br>";
		}
		
		ret += "<br>" + "Total Portfolio Value: " + getTotalValue() + "$<br>"
				+ "Total Stocks Value: " + this.getStocksValue() + "$<br>"
				+ "Balance: " + getBalance() + "$";
		return ret;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StockInterface[] getStocks() {
		return stocks;
	}
	
	public StockInterface getStockByIndex(int index) {
		if (index < 0 || index >= portfolioSize) {
			return null;
		}
		return stocks[index];
	}

	public void setStocks(Stock[] stocks) {
		this.stocks = (StockInterface[]) stocks;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	public double getStocksValue(){
		double total = 0;
		for (int i=0; i < portfolioSize; i++){

			total = total + (((Stock) this.stocks[i]).getStockQuantity() * this.stocks[i].getBid());
		}
		return total;
	}

	public double getTotalValue(){

		return this.getStocksValue()+getBalance();
	
	
	}
	public static int getMaxPortfolioSize() {  
		return MAX_PORTFOLIO_SIZE;
	}
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	public StockInterface findStock(String symbol) {
		
		int i = 0;
		boolean isFound = false;
		if (symbol == null){
			return null;
		}
		for (i = 0; i < this.portfolioSize && isFound == false; i++){
			if (symbol.equals(this.stocks[i].getSymbol())){
				isFound = true;
			}
		}
		return this.stocks[i];
	}
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		
	}
	
	/*@Override
	public StockInterface[] getStocks() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	*/

}