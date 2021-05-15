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

import com.project.bean.DietPlan;
import com.project.bean.FruitItems;
import com.project.bean.NonVegItems;
import com.project.bean.SignUpBean;
import com.project.bean.VegItems;
import com.project.service.FruitItemsService;
import com.project.service.FruitItemsServiceImpl;
import com.project.service.NewDietPlanService;
import com.project.service.NewDietPlanServiceImpl;
import com.project.service.NonVegItemsService;
import com.project.service.NonVegItemsServiceImpl;
import com.project.service.SignUpService;
import com.project.service.SignUpServiceImpl;
import com.project.service.VegItemsService;
import com.project.service.VegItemsServiceImpl;

/**
 * Defines method to display the response from server to the user. It displays the details 
 * of all the diet plans for a particular user.
 * @author Harsh Sharma
 *
 */
public class DisplayDietServlet extends HttpServlet {
	
	/**
	 * A static final variable that stores serial version UID of the servlet.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * A SignUpService object.
	 */
	SignUpService signUpService = new SignUpServiceImpl();
	
	/**
	 * A NewDietPlanService object.
	 */
	NewDietPlanService newDietPlanService = new NewDietPlanServiceImpl();
	
	/**
	 * A VegItemsService object.
	 */
	VegItemsService vegItemsService = new VegItemsServiceImpl();
	
	/**
	 * A NonVegItemsService object.
	 */
	NonVegItemsService nonVegItemsService = new NonVegItemsServiceImpl();
	
	/**
	 * A FruitItemsService object.
	 */
	FruitItemsService fruitItemsService = new FruitItemsServiceImpl();
	
	/**
	 * A static final logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(DisplayDietServlet.class);

	/**
	 * A default constructor.
	 */
	public DisplayDietServlet() {
		super();
	}
	
	/**
	 * doPost method which calls a doGet method.
	 * @param request A HttpServletRequest object
	 * @param response A HttpServletRequest object
	 * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
	 * @throws IOException Exceptions produced by failed or interrupted I/O operations
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Inside doPost method of DisplayDietServlet class");
		doGet(request, response);
	}

	/**
	 * doGet method that provides the response to the user regarding all the diet plans that the current user has.
	 * It also displays user details on the web page.
	 * @param request A HttpServletRequest object
	 * @param response A HttpServletRequest object
	 * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
	 * @throws IOException Exceptions produced by failed or interrupted I/O operations
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Inside doGet method of DisplayDietServlet class");
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("user");
		PrintWriter out = response.getWriter();

		try {
			SignUpBean signUpBean = signUpService.readUserDetails(userName);
			LOGGER.info("User details are read successfully for " + signUpBean.getUserName());
			ArrayList<DietPlan> dietPlan = newDietPlanService.readDietDetails(userName);
			LOGGER.info("All the Diet plans details are read successfully for " + signUpBean.getUserName());
			LOGGER.info("Method to calculate total calories of all diet plans for user " + userName + " called.");
			ArrayList<Double> totalCaloriesOfDiets = getTotalCalories(dietPlan);
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
			out.println("<center>");
			out.println("<h2>User Details</h2>");
			out.println("<table border=2 cellpadding=2 cellspacing=2>");
			out.println("<tr>");
			out.println("<th>" + "First Name" + "</th>");
			out.println("<th>" + "Last Name" + "</th>");
			out.println("<th>" + "City" + "</th>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>" + signUpBean.getFirstName() + "</td>");
			out.println("<td>" + signUpBean.getLastName() + "</td>");
			out.println("<td>" + signUpBean.getCity() + "</td>");
			out.println("</tr>");
			out.println("</table");
			out.println("</center>");
			out.println("<center>");
			out.println("<br>");
			out.println("<br>");
			out.println("<h2> Diet Plans </h2>");
			out.println("<table border=2 cellpadding=2 cellspacing=2>");
			out.println("<tr>");
			out.println("<th>" + "Diet No." + "</th>");
			out.println("<th>" + "Diet Name" + "</th>");
			out.println("<th>" + "Time Of Diet" + "</th>");
			out.println("<th>" + "Duration Of Diet" + "</th>");
			out.println("<th>" + "Total Calories" + "</th>");
			out.println("<th colspan = 3>" + "Operations" + "</th>");
			out.println("</tr>");
			for (int i = 0; i < dietPlan.size(); i++) {
				session.setAttribute("dietNum", dietPlan.get(i).getDietNum());
				out.println("<tr>");
				out.println("<td>" + (i+1) + "</td>");
				out.println("<td>" + dietPlan.get(i).getDietName() + "</td>");
				out.println("<td>" + dietPlan.get(i).getTimeOfDiet() + "</td>");
				out.println("<td>" + dietPlan.get(i).getDietDurationInDays() + "</td>");
				out.println("<td>" + totalCaloriesOfDiets.get(i) + "</td>");
				out.println("<td><a href=\"CompleteDietServlet?dietName=" + dietPlan.get(i).getDietName()
						+ "\">View</a></td>");
				out.println("<td><a href='updateDietDetails.html'>Edit</a></td>");
				out.println("<td><a href='DeleteDietServlet'>Delete</a></td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("<br><br><a href='workingPage.html'><button>Back to Menu</button></a>");
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			LOGGER.info("Diet plan details for user " + userName + " are successfully displayed");
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error generated while displaying diet details for user " + userName + ".Message: "
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<Double> getTotalCalories(ArrayList<DietPlan> dietPlans) {
		ArrayList<Double> totalCalories = new ArrayList<Double>();
		for (int i = 0; i < dietPlans.size(); i++) {
			String dietName = dietPlans.get(i).getDietName();
			try {
				ArrayList<VegItems> vegItems = vegItemsService.readVegItems(dietName);
				ArrayList<NonVegItems> nonVegItems = nonVegItemsService.readNonVegItems(dietName);
				ArrayList<FruitItems> fruitItems = fruitItemsService.readFruitItems(dietName);
				double totalCal = dietPlans.get(i).getTotalCaloriesOfDiet(vegItems, nonVegItems, fruitItems);
				System.out.println(totalCal);
				totalCalories.add(totalCal);
			} catch (ClassNotFoundException | SQLException e) {
				LOGGER.error("Error generated while calculating total calories in getTotalCalories method.Message: "
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		LOGGER.info("Total calories for all diet plans are calculated successfully and added in the arraylist.");
		return totalCalories;
	}
}
