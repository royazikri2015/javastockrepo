package com.azikri;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.Math;

@SuppressWarnings("serial")
public class MyNewServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
		response.setContentType("text/html");
		 
		int radius= 50;
		final double PIE = 3.1415926;
		int circle_area = (int) (2*PIE*radius); 
		
		int opposite;
	    int hypotenuse=50;
	    int angleB=30;
	    opposite= hypotenuse*angleB;

		 int base=20;
		 int exp=13;
		 double res = Math.pow(base, exp);
		 
		 
		 String circle_areaStr= new String ( "<h1>Area of circle with radius " + radius + " is " + circle_area + "</h1><br>");
		 
		 String length_of_oppositeStr= ( "<h1>Length of opposite where angle B is " + angleB + "  degrees and hypotenuse"
		 		+ "  is " + hypotenuse +  " cm is " + circle_area + "</h1><br>");
		 
		 String powerStr= ( "<h1> power of " +base+ " with exp of " + exp + " is " + res + "</h1><br>" );

		response.getWriter().println(circle_areaStr);
		response.getWriter().println (length_of_oppositeStr);
		response.getWriter().println (powerStr);
		
		}
	

  


}








	
		
	



