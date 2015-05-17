package com.azikri.app.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Stock {
	
	/*private static final int BUY=0;
	private static final int SELL=1;
	private static final int REMOVE=2;
	private static final int HOLD=3;*/
	
	private int recommendation;
	private int stockQuantity;
	private float ask,bid;
	private String symbol;
	private Date creation;
	
	public Stock(String symbol,float bid,float ask, Date date) {//stock creation
		
		this.symbol= symbol;
		this.ask=ask;
		this.bid=bid;
		this.creation=date; 
		
		/*this.recommendation= recommendation;
		this.stockQuantity= stockQuantity;*/
	}
/* COPY C'TOR of stock */
	
	public Stock (Stock stock){
		this (new String (stock.getSymbol()), stock.getAsk(),stock.getBid(),stock.getCreation());
	}
	
	public float getAsk() {
		return ask;
	}

	public void setAsk(float ask) {
			this.ask = ask;
		}
	
	public float getBid() {
			return bid;
		}
	
	public void setBid(float bid) {
			this.bid = bid;
		}
	
	public int getRecommendation() {
			return recommendation;
		}
	
	public void setRecommendation(int recommendation) {
			this.recommendation = recommendation;
		}
	
	public int getStockQuantity() {
			return stockQuantity;
		}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setName(String value) {
		this.symbol = value;
	}
	/*public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}                    */
	public Date getCreation() {
		return creation;
	}
	public void setCreation(Date creation) {
		this.creation = creation;
	}
	public String getHtmlDescription() {
		
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		//String dateStr = df.format(getDate());
		String ret=	" <b>symbol: </br>" + getSymbol() +
				" <br>ask: </b> " + getAsk()
				+" <br>bid: </b> " + getBid();
				//+", <b> Date: </b>" +dateStr;
		
		return ret;
	}
}
