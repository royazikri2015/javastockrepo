package com.azikri.app.model;

import org.algo.model.StockInterface;
import org.algo.model.PortfolioInterface;







import com.azikri.app.exception.BalanceException;
import com.azikri.app.exception.PortfolioFullException;
import com.azikri.app.exception.StockAlreadyExistsException;
import com.azikri.app.exception.StockNotExistException;
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
	public void addStock (Stock stock) throws PortfolioFullException,
	StockAlreadyExistsException, StockNotExistException {
		if (this.portfolioSize == MAX_PORTFOLIO_SIZE) {
	throw new PortfolioFullException();
}
		if (stock == null) {
	throw new StockNotExistException ();
}
		for (int i = 0; i < this.portfolioSize; i++) {
	if (stock.getSymbol().equals(stocks[i].getSymbol())) {
		throw new StockAlreadyExistsException(
				"You already have this stock!");
	}
}
   if (this.portfolioSize < MAX_PORTFOLIO_SIZE) {
	stocks[this.portfolioSize] = stock;
	((Stock) stocks[this.portfolioSize]).setStockQuantity(0); 
	this.portfolioSize++;
}
}
	
	public void sellStock(String symbol, int quantity) throws StockNotExistException
	 {
boolean Exist = false;
if (symbol == null || quantity < -1) { // validance check
	throw new StockNotExistException("Invalid input, please try again!");
}
for (int i = 0; i < this.portfolioSize && Exist == false; i++) {
	if (symbol.equals(stocks[i].getSymbol())) {
		Exist = true;
		if (((Stock) this.stocks[i]).getStockQuantity() - quantity < 0) {
			throw new SellingOverQuantityException();

		} else if (quantity == -1) {
			this.balance += ((Stock) this.stocks[i]).getStockQuantity()
					* this.stocks[i].getBid();
			((Stock) this.stocks[i]).setStockQuantity(0);
			System.out.println("All stocks of " + symbol
					+ " were sold!");

		} else { // any other case
			this.balance += quantity * this.stocks[i].getBid();
			((Stock) this.stocks[i])
					.setStockQuantity(((Stock) stocks[i])
							.getStockQuantity() - quantity);
			System.out.println("An amount of " + quantity
					+ " of stock: " + symbol + " were sold!");
		}
	}
}
if (Exist == false) {
	throw new StockNotExistException(
			"The stock you're trying to sell doesn't appear in portfolio");
}
}{ // sell stocks

		Object symbol = null;
		float quantity = 0;
		if(symbol == null || quantity < -1){
			System.out.println("Sorry, the stock symbol or stock quantity are wrong");
			//return false;
		}

		int i = this.searchStock (symbol);

		if(i>-1){	
			if(((Stock) this.stocks[i]).getStockQuantity() - quantity < 0){
				System.out.println("You don't have enough stocks to sell");
				//return false;

			}else if(quantity == -1){
				this.updateBalance(((Stock) this.stocks[i]).getStockQuantity()*this.stocks[i].getBid());
				((Stock) this.stocks[i]).setStockQuantity(0);
				System.out.println("the quantity you wanted was sold");
				//return true;
			}
			else{

				this.updateBalance(quantity * this.stocks[i].getBid());
				((Stock) this.stocks[i]).setStockQuantity(((Stock) stocks[i]).getStockQuantity() - quantity);
				System.out.println("the wanted amount was sold");
				//return true;
			}
		}
		System.out.println("the stock is not in the Portfolio");
		//return false; 
	}
	
	private int searchStock(Object symbol) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void removeStock (String deleteSymbol) throws StockNotExistException,
	BalanceException { 
		
		boolean StillRun = true;
		if (deleteSymbol == null) { 
								
			throw new StockNotExistException(deleteSymbol);
		}
		if (portfolioSize == 1
				|| deleteSymbol.equals(stocks[portfolioSize - 1].getSymbol())) {
			
			
			this.sellStock(stocks[portfolioSize - 1].getSymbol(), -1);

			stocks[portfolioSize - 1] = null;
			portfolioSize--;
			
		}
		for (int i = 0; i < portfolioSize && StillRun; i++) { 
																
															
			if (deleteSymbol.equals(stocks[i].getSymbol())) {
				this.sellStock(stocks[portfolioSize - 1].getSymbol(), -1);
				stocks[i] = stocks[portfolioSize - 1];
				stocks[portfolioSize - 1] = null;
				portfolioSize--;
				StillRun = false; 
			}
		}
		if (StillRun == true){
			throw new StockNotExistException();
		}
	}
	
	public void buyStock(Stock stock, int quantity) // buy stocks
	{
		throws BalanceException,PortfolioFullException, StockNotExists, StockAlreadyExistsException {
	boolean Exist = false;
	int i = 0;
	if (stock == null || quantity < -1) {
		throw new StockNotExistException();
	}
	if (quantity * stock.getAsk() > this.balance) {
		throw new BalanceException();
	}	
	for (i = 0; i < this.portfolioSize; i++) {
		if (stock.getSymbol().equals(this.stocks[i].getSymbol())) { 
			Exist = true;
			if (quantity == -1) { 
				int numOfStock = (int) this.balance
						/ (int) this.stocks[i].getAsk();
				this.balance -= numOfStock * this.stocks[i].getAsk();
				((Stock) this.stocks[i])
						.setStockQuantity(((Stock) this.stocks[i])
								.getStockQuantity() + numOfStock);
				System.out.println("All stocks of " + stock.getSymbol()
						+ " Were bought successfuly!");

			} else { 
				this.balance -= quantity * this.stocks[i].getAsk();
				((Stock) this.stocks[i])
						.setStockQuantity(((Stock) stocks[i])
								.getStockQuantity() + quantity);
				System.out.println("Amount of " + quantity
						+ " stocks  of the stock " + stock.getSymbol()
						+ " Were bought!");
			
			}
			break; 
		}
	}
	
	if (i == MAX_PORTFOLIO_SIZE) { 
		throw new PortfolioFullException();
	} 
}
	}

	public void updateBalance(float amount) throws BalanceException {
		float temp = this.balance + amount;
		if (temp < 0) {
			throw new BalanceException();
		} else {
			this.balance = temp;
			System.out.println("Balance updated!");
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