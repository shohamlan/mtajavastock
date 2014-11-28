package com.mta.javacourse;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StockDetailsServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws IOException {
		resp.setContentType("text/html");
		
		stock stock1= new stock();
		stock stock2= new stock();
		stock stock3= new stock();
		java.util.Date currentDate= new java.util.Date(); 
		
		currentDate.setDate(15);
		currentDate.setMonth(11);
		currentDate.setYear(2014);
		
		stock1.setSymbol("PIH");
		stock1.setAsk(12.4f);
		stock1.setBid(13.1f);
		stock1.setDate(currentDate);
		
		stock2.setSymbol("AAL");
		stock2.setAsk(5.5f);
		stock2.setBid(5.78f);
		stock2.setDate(currentDate);
		
		stock3.setSymbol("CAAS");
		stock3.setAsk(31.5f);
		stock3.setBid(31.2f);
		stock3.setDate(currentDate);
		
		resp.getWriter().println(stock1.getHtmlDescription() + "<br>");
		resp.getWriter().println(stock2.getHtmlDescription() + "<br>");
		resp.getWriter().println(stock3.getHtmlDescription() + "<br>");
	}
}
