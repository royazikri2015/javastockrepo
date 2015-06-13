package com.azikri.app.exception;

import org.algo.exception.PortfolioException;

public class StockAlreadyExistsException extends PortfolioException{
	public StockAlreadyExistsException(){
		super("Stock's already exists");
	}
	public StockAlreadyExistsException(String message){
		super(message);
	}
} 


