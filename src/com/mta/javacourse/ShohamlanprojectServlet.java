package com.mta.javacourse;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class ShohamlanprojectServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		int radius=50;
		double area=(Math.pow(radius,2)) * (Math.PI);
		String areasize = new String ("<h1>Area of circle with radius "+radius+" is: "+area+" cm</h1>");
		
		
		int hypotenuse=50;
		int angleB=30;
		double lengthOfOpposite=(Math.toRadians(angleB))*hypotenuse;
		String trianglesize = new String ("<h1>Length of opposite where angle B is 30 degrees and Hypotenuse length is 50 cm is: "+lengthOfOpposite+" cm </h1>");
		
		int base=20;
		int exp=13;
		double basePowExp=(Math.pow(base,exp));
		String resultOfPow = new String ("<h1>Power of 20 with exp of 13 is "+basePowExp+"</h1>");
		
		
		String line1 = new String (areasize);
		String line2 = new String(trianglesize);
		String line3 = new String(resultOfPow);
		
		String resultStr = line1 + "<br>" + line2 + "<br>" +line3;
		resp.getWriter().println(resultStr);
	}
}