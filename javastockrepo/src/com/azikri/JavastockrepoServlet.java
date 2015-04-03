package com.azikri;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class JavastockrepoServlet extends HttpServlet {
	int num1=4;
	int num2=3;
	int num3=7;
	int result= (num1+num2)*num3;
	int var1= num1+num2;
	int var2=num3;
	String resultStr = new String("<h1>Result of"+var1+"*"+var2+"="+result+"</h1>");
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		
		resp.getWriter().println("Result of (num1+num2) * num3 is: result");
		resp.getWriter().println( resultStr );
	}
}
