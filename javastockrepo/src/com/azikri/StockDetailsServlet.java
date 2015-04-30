package com.azikri;

import java.io.IOException;
import java.util.Date;
import java.util.Calendar;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
			
			resp.setContentType("text/html");
			
			Calendar cal= Calendar.getInstance();
			cal.set (2014,10,15);
			Date date1= cal.getTime();
			
			cal.set(2014,10,15);
			Date date2 = (Date) cal.getTime();
			
			cal.set(2014,10,15);
			Date date3 = (Date) cal.getTime();
			
			Stock stock1 = new Stock ("PIH", (float) 13.1,(float) 12.4, date1); 
			Stock stock2 = new Stock ("AAL",(float) 5.78, (float)5.5, date2); 
			Stock stock3 = new Stock ("CAAS",(float)32.2, (float)31.5, date3); 
			
			resp.getWriter().println(stock1.getHtmlDescription());
			resp.getWriter().println("<p>");
			resp.getWriter().println(stock2.getHtmlDescription());
			resp.getWriter().println("<p>");
			resp.getWriter().println(stock3.getHtmlDescription());
	
	}
}
