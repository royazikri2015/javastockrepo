package com.azikri;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

	public class Stock {
	
		private String symbol;
		private Float ask;
		private Float bid;
		private Date date;
	
	public Stock (String symbol,Float ask, Float bid, Date date) {
		this.symbol = symbol;
		this.ask    = ask;
		this.bid    =  bid;
		this.date   = date;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Float getAsk() {
		return ask;
	}
	public void setAsk(Float ask) {
		this.ask = ask;
	}
	public Float getBid() {
		return bid;
	}
	public void setBid(Float bid) {
		this.bid = bid;
		}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getHtmlDescription(){
		
	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
	String dateStr = df.format(getDate());
       
	String ret= "<b>Stock:: Symbol: </b>" + getSymbol() + ", <b>Ask: </b>" + getAsk()
			+ ",<b>Bid: </b>" + getBid()+ ", <b> Date: </b>" +dateStr;
			
	return ret;

 }
}



