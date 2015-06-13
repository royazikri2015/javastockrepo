package com.azikri.app.exception;

import org.algo.exception.PortfolioException;


public class StockNotExistException extends PortfolioException{
	public StockNotExistException(String string) {
		// TODO Auto-generated constructor stub
	}
	public void StockNotExists(){
		super("Stock's not exists");
	}
	public void StockNotExists(String message){
		super(message);
	}
}
 


