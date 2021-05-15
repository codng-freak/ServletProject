package com.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.project.bean.FruitItems;
import com.project.bean.NonVegItems;
import com.project.bean.VegItems;
import com.project.service.FruitItemsService;
import com.project.service.FruitItemsServiceImpl;
import com.project.service.NonVegItemsService;
import com.project.service.NonVegItemsServiceImpl;
import com.project.service.VegItemsService;
import com.project.service.VegItemsServiceImpl;

/**
 * Defines method to display the response from server to the user. It displays all the details for all the 
 * three items namely Vegetarian, Non vegetarian and fruit items for a particular diet plan.
 * @author Harsh Sharma
 *
 */
public class CompleteDietServlet extends HttpServlet {
	
	/**
	 * A static final variable that stores serial version UID of the servlet.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * A VegItemService object.
	 */
	VegItemsService vegItemsService = new VegItemsServiceImpl();
	
	/**
	 * A NonVegItemService object.
	 */
	NonVegItemsService nonVegItemsService = new NonVegItemsServiceImpl();
	
	/**
	 * A FruitItemService object.
	 */
	FruitItemsService fruitItemsService = new FruitItemsServiceImpl();
	
	/**
	 * A static final logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(CompleteDietServlet.class);

	/**
	 * doPost method which calls a doGet method.
	 * @param request A HttpServletRequest object
	 * @param response A HttpServletRequest object
	 * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
	 * @throws IOException Exceptions produced by failed or interrupted I/O operations
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Inside doPost method of CompleteDietServlet class");
		doGet(request, response);
	}

	/**
	 * doGet method that provides the response to the user regarding all types of food items for a particular diet plan.
	 * @param request A HttpServletRequest object
	 * @param response A HttpServletRequest object
	 * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
	 * @throws IOException Exceptions produced by failed or interrupted I/O operations
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Inside CompleteDietDetails Servlet class");
		PrintWriter out = response.getWriter();
		String dietName = request.getParameter("dietName");
		HttpSession session = request.getSession();
		session.setAttribute("diet", dietName);
		try {
			ArrayList<VegItems> vegItems = vegItemsService.readVegItems(dietName);
			LOGGER.info("Veg Items details are read successfully for " + dietName);
			ArrayList<NonVegItems> nonVegItems = nonVegItemsService.readNonVegItems(dietName);
			LOGGER.info("Non Veg Items details are read successfully for " + dietName);
			ArrayList<FruitItems> fruitItems = fruitItemsService.readFruitItems(dietName);
			LOGGER.info("Fruit Items details are read successfully for " + dietName);

			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset=\"utf-8\">");
			out.println("<style>");
			out.println("* {");
			out.println("box-sizing:border-box;");
			out.println("}");
			out.println("body {");
			out.println("background-image:linear-gradient(" + 320 + "deg, #84CEEB, #C1C8E4) ");
			out.println("}");
			out.println(".col-6 {");
			out.println("width:50%;");
			out.println("float:left;");
			out.println("padding:15px;");
			out.println("}");
			out.println(".col-12 {");
			out.println("width:100%;");
			out.println("float:left;");
			out.println("padding:15px;");
			out.println("}");
			out.println("h2 {");
			out.println("text-align:center;");
			out.println("}");
			out.println("tr {");
			out.println("text-align:center;");
			out.println("background-color:white;");
			out.println("}");
			out.println("td {");
			out.println("color:#FF1493;");
			out.println("font-weight:bold;");
			out.println("}");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class=row>");
			out.println("<div class=col-6>");
			out.println("<h2>Veg Items</h2>");
			out.println("<table border=2 cellpadding=2 cellspacing=2>");
			out.println("<tr>");
			out.println("<th>Item No.</th>");
			out.println("<th>Item Name</th>");
			out.println("<th>Item Type</th>");
			out.println("<th>Amount(in grams)</th>");
			out.println("<th>Calories(per 100 grams)</th>");
			out.println("<th>Total Calories</th>");
			out.println("<th colspan=3>Operaions</th>");
			out.println("</tr>");
			for (int i = 0; i < vegItems.size(); i++) {
				session.setAttribute("itemNumVeg", vegItems.get(i).getItemNum());
				out.println("<tr>");
				out.println("<td>" + (i + 1) + "</td>");
				out.println("<td>" + vegItems.get(i).getItemName() + "</td>");
				out.println("<td>" + vegItems.get(i).getItemType() + "</td>");
				out.println("<td>" + vegItems.get(i).getAmount() + "</td>");
				out.println("<td>" + vegItems.get(i).getCaloriesPer100Grams() + "</td>");
				out.println("<td>" + vegItems.get(i).getTotalCalories() + "</td>");
				out.println("<td><a href='updateVegItems.html'>Edit</a></td>");
				out.println("<td><a href=\"DeleteVegItemsServlet?dietName=" + dietName + "\">Delete</a></td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</div>");
			out.println("<div class=col-6>");
			out.println("<h2>Non-Veg Items</h2>");
			out.println("<table id=nonVeg border=2 cellpadding=2 cellspacing=2>");
			out.println("<tr>");
			out.println("<th>Item No.</th>");
			out.println("<th>Item Name</th>");
			out.println("<th>Amount(in grams)</th>");
			out.println("<th>Calories(per 100 grams)</th>");
			out.println("<th>Total Calories</th>");
			out.println("<th colspan=3>Operaions</th>");
			out.println("</tr>");
			for (int i = 0; i < nonVegItems.size(); i++) {
				session.setAttribute("itemNumNonVeg", nonVegItems.get(i).getItemNum());
				out.println("<tr>");
				out.println("<td>" + (i + 1) + "</td>");
				out.println("<td>" + nonVegItems.get(i).getItemName() + "</td>");
				out.println("<td>" + nonVegItems.get(i).getAmount() + "</td>");
				out.println("<td>" + nonVegItems.get(i).getCaloriesPer100Gram() + "</td>");
				out.println("<td>" + nonVegItems.get(i).getTotalCalories() + "</td>");
				out.println("<td><a href='updateNonVegItems.html'>Edit</a></td>");
				out.println("<td><a href=\"DeleteNonVegItemsServlet?dietName=" + dietName + "\">Delete</a></td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</div>");
			out.println("</div>");
			out.println("<div class=row>");
			out.println("<div class=col-12>");
			out.println("<center>");
			out.println("<h2>Fruit Items</h2>");
			out.println("<table border=2 cellpadding=2 cellspacing=2>");
			out.println("<tr>");
			out.println("<th>Item No.</th>");
			out.println("<th>Item Name</th>");
			out.println("<th>Amount(in grams)</th>");
			out.println("<th>Calories(per 100 grams)</th>");
			out.println("<th>Total Calories</th>");
			out.println("<th colspan=3>Operaions</th>");
			out.println("</tr>");
			for (int i = 0; i < fruitItems.size(); i++) {
				session.setAttribute("itemNumFruit", fruitItems.get(i).getItemNum());
				out.println("<tr>");
				out.println("<td>" + (i + 1) + "</td>");
				out.println("<td>" + fruitItems.get(i).getItemName() + "</td>");
				out.println("<td>" + fruitItems.get(i).getAmount() + "</td>");
				out.println("<td>" + fruitItems.get(i).getCaloriesPer100Grams() + "</td>");
				out.println("<td>" + fruitItems.get(i).getTotalCalories() + "</td>");
				out.println("<td><a href='updateFruitItems.html'>Edit</a></td>");
				out.println("<td><a href=\"DeleteFruitItemsServlet?dietName=" + dietName + "\">Delete</a></td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</center><br>");
			out.println("<center>");
			out.println("<a href=\"DisplayDietServlet\"><button>Go Back</button></a>");
			out.println("</center>");
			out.println("</div>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
			LOGGER.info("Veg, Non Veg, Fruit items details for diet " + dietName + " are successfully displayed");
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error generated while displaying veg, non veg and fruit items details for diet " + dietName
					+ ".Message: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
