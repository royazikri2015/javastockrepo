	package com.azikri.app.model.service;
	
	import java.util.Calendar;
	import java.util.Date;
	
	import com.azikri.Stock;
	import com.azikri.app.model.Portfolio;
	
		
	/*public void addPortfolio (PortfolioManager portfolio){
		
	}*/	
	public class PortfolioManager{
		
	
		public Portfolio getPortfolio(){
			Portfolio portfolio = new Portfolio();
			
			Calendar cal= Calendar.getInstance();
			cal.set (2014,10,15);
			Date date1= cal.getTime();
			
			cal.set(2014,10,15);
			Date date2 = (Date) cal.getTime();
			
			cal.set(2014,10,15);
			Date date3 = (Date) cal.getTime();
			
			
			Stock stock1= new Stock("PIH", (float) 13.1,(float) 12.4, date1);
			Stock stock2 = new Stock ("AAL",(float) 5.78, (float)5.5, date2); 
			Stock stock3 = new Stock ("CAAS",(float)32.2, (float)31.5, date3);
			
			portfolio.setName ("Roy's portfolio");
			
			return portfolio;
			/*portfolio.addStock (stock1);
			 */		
	}
	}

	
	
	
	
	
	
