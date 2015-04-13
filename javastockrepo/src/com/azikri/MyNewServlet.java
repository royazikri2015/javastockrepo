package com.azikri;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class MyNewServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html");
		response.getWriter().println("Hello my new Servlet");}
	

   int radius= 50;
   public final double PIE = 3.1415926;
   int circle_area = (int) (2*PIE*radius); 
   String circle_areaStr= new String ( "<h1>Area of circle with radius <radius> is <circle_area> "+"</h1>)");


}








	
		
	



