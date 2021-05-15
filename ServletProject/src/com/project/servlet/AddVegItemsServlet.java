package com.project.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.project.bean.VegItems;
import com.project.service.NewDietPlanService;
import com.project.service.NewDietPlanServiceImpl;
import com.project.service.VegItemsService;
import com.project.service.VegItemsServiceImpl;

/**
 * Defines method to request vegetarian items data from a user and forward that data to the service layer for further operations.
 * It takes data from the user via HTML and then calls the service layer methods to perform operations on the data. This servlet helps to 
 * retrieve vegetarian items details from user and manipulate requests on the client side.
 * @author Harsh Sharma
 *
 */
public class AddVegItemsServlet extends HttpServlet {
	/**
	 * A static final variable that stores serial version UID of the servlet.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * A static final logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(AddVegItemsServlet.class);
	
	/**
	 * A static counter variable with a value of 0.
	 */
	private static int counter = 0;

	/**
	 * A Default Constructor that has a super method.
	 */
	public AddVegItemsServlet() {
		super();

	}

	/**
	 * Method to request vegetarian items data from the HTML page.
	 * @param request A HttpServletRequest object.
	 * @param response A HttpServletResponse object.
	 * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
	 * @throws IOException Exceptions produced by failed or interrupted I/O operations
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Inside AddVegItemsServlet class");
		HttpSession session = request.getSession();
		String dietName = (String) session.getAttribute("dietName");
		NewDietPlanService newDietPlanService = new NewDietPlanServiceImpl();
		int dietPlanNum = (int) session.getAttribute("DietPlanNum");

		VegItemsService vegItemsService = new VegItemsServiceImpl();
		try {
			if (request.getParameter("repeat") != null) {
				String itemName = request.getParameter("iname");
				String itemType = request.getParameter("type");
				double amount = Double.parseDouble(request.getParameter("amount"));
				double calories = Double.parseDouble(request.getParameter("calories"));

				VegItems vegItems = new VegItems();
				vegItems.setItemName(itemName);
				vegItems.setItemType(itemType);
				vegItems.setAmount(amount);
				vegItems.setCaloriesPer100Grams(calories);
				vegItems.setDietName(dietName);
				int result = vegItemsService.insertVegItems(vegItems);
				if (result > 0) {
					counter++;
					LOGGER.info("Veg Item " + itemName
							+ " added successfully and request for adding another veg item is generated.");
					request.getRequestDispatcher("addVegItems.html").forward(request, response);
				}
			}
			if (request.getParameter("forward") != null) {
				String itemName = request.getParameter("iname");
				String itemType = request.getParameter("type");
				double amount = Double.parseDouble(request.getParameter("amount"));
				double calories = Double.parseDouble(request.getParameter("calories"));

				VegItems vegItems = new VegItems();
				vegItems.setItemName(itemName);
				vegItems.setItemType(itemType);
				vegItems.setAmount(amount);
				vegItems.setCaloriesPer100Grams(calories);
				vegItems.setDietName(dietName);
				int result = vegItemsService.insertVegItems(vegItems);
				if (result > 0) {
					counter += 1;
					session.setAttribute("counterVeg", counter);
					counter = 0;
					LOGGER.info("Veg Item " + itemName + " added successfully and request to next page is made.");
					request.getRequestDispatcher("addNonVegItems.html").forward(request, response);
				}
			}
			if (request.getParameter("backward") != null) {
				LOGGER.info("Request to previous page is made.");
				if (counter == 1 || counter == 0) {
					newDietPlanService.deleteDietPlan(dietPlanNum);
					request.getRequestDispatcher("newDiet.html").forward(request, response);
				} else {
					counter -= 1;
					vegItemsService.deleteVegItemsByCount((counter), dietName);
					request.getRequestDispatcher("addVegItems.html").forward(request, response);
				}

			}
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error generated while forwarding request from servlet. Message: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
