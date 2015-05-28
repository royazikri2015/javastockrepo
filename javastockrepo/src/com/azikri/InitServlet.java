package com.azikri;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;

import org.algo.service.ServiceManager;

import com.azikri.app.model.service.PortfolioManager;


public class InitServlet extends HttpServlet {

	@Override
 	public void init() throws ServletException {
	 	super.init();
	 	PortfolioManager pm = new PortfolioManager();
	 	ServiceManager.setPortfolioManager(pm);
  
	
	}
}
