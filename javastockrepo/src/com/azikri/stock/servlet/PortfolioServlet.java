package com.azikri.stock.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azikri.app.model.Portfolio;
import com.azikri.app.model.Stock;
import com.azikri.app.model.service.PortfolioManager;
import java.util.Date;

public class PortfolioServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request,HttpServletResponse resp)
			throws ServletException, IOException{
			
			resp.setContentType("text/html");
			
			String response = "";
			
			PortfolioManager PortfolioManager = new PortfolioManager();
			Portfolio portfolio = PortfolioManager.getPortfolio();
			
			response += portfolio.getHtmlString();
			
			Portfolio portfolio2 = new Portfolio(portfolio);
			portfolio2.setName ("Portfolio #2"); /* 1.c*/
			
			response += portfolio2.getHtmlString();
			
			portfolio.removeStock(portfolio.getStocks()[0].getSymbol());
			
			portfolio2.getStocks()[portfolio2.getPortfolioSize()-1].setBid((float)55.55);
			
			response +=  "<h1>After remove stock and change bid</h1>";
			response += portfolio.getHtmlString();
			response += portfolio2.getHtmlString();
			
			resp.getWriter().println(response);
			}

	

}
