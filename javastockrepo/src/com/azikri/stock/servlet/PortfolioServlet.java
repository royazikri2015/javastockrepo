package com.azikri.stock.servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.azikri.app.model.Portfolio;
import com.azikri.app.model.service.PortfolioManager;

public class PortfolioServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
			
			response.setContentType("text/html");
			
			PortfolioManager PortfolioManager = new PortfolioManager();
			Portfolio Portfolio = PortfolioManager.getPortfolio();
			
			response.getWriter().println(Portfolio.getHtmlString());
			}

	

}
