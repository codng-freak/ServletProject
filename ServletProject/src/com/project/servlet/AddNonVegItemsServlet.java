package com.project.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.project.bean.NonVegItems;
import com.project.service.NonVegItemsService;
import com.project.service.NonVegItemsServiceImpl;
import com.project.service.VegItemsService;
import com.project.service.VegItemsServiceImpl;


/**
 * Defines method to request Non vegetarian items data from a user and forward that data to the service layer for further operations.
 * It takes data from the user via HTML and then calls the service layer methods to perform operations on the data. This servlet helps to 
 * retrieve non vegetarian items details from user and manipulate requests on the client side.
 * @author Harsh Sharma
 *
 */
public class AddNonVegItemsServlet extends HttpServlet {
	
	/**
	 * A static final variable that stores serial version UID of the servlet.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * A static final logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(AddNonVegItemsServlet.class);
	
	/**
	 * A static counter variable with a value of 0.
	 */
	private static int counter = 0;

	/**
	 * A Default Constructor that has a super method.
	 */
	public AddNonVegItemsServlet() {
		super();
	}

	/**
	 * Method to request non vegetarian items data from the HTML page.
	 * @param request A HttpServletRequest object.
	 * @param response A HttpServletResponse object.
	 * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
	 * @throws IOException Exceptions produced by failed or interrupted I/O operations
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("In AddNonVegItemsServlet class");
		HttpSession session = request.getSession();
		String dietName = (String) session.getAttribute("dietName");
		int counterVeg = (int) session.getAttribute("counterVeg");

		NonVegItemsService nonVegItemsService = new NonVegItemsServiceImpl();
		VegItemsService vegItemsService = new VegItemsServiceImpl();
		try {
			if (request.getParameter("repeat") != null) {
				String itemName = request.getParameter("iname");
				double amount = Double.parseDouble(request.getParameter("amount"));
				double calories = Double.parseDouble(request.getParameter("calories"));

				NonVegItems nonVegItems = new NonVegItems();
				nonVegItems.setItemName(itemName);
				nonVegItems.setAmount(amount);
				nonVegItems.setCaloriesPer100Gram(calories);
				nonVegItems.setDietName(dietName);
				int result = nonVegItemsService.insertNonVegItems(nonVegItems);
				if (result > 0) {
					counter++;
					LOGGER.info("Non Veg Item " + itemName
							+ " added successfully and request for adding another non veg item is generated.");
					request.getRequestDispatcher("addNonVegItems.html").forward(request, response);
				}
			}
			if (request.getParameter("forward") != null) {
				String itemName = request.getParameter("iname");
				double amount = Double.parseDouble(request.getParameter("amount"));
				double calories = Double.parseDouble(request.getParameter("calories"));

				NonVegItems nonVegItems = new NonVegItems();
				nonVegItems.setItemName(itemName);
				nonVegItems.setAmount(amount);
				nonVegItems.setCaloriesPer100Gram(calories);
				nonVegItems.setDietName(dietName);
				int result = nonVegItemsService.insertNonVegItems(nonVegItems);
				if (result > 0) {
					counter += 1;
					session.setAttribute("counterNonVeg", counter);
					counter = 0;
					LOGGER.info("Non Veg Item " + itemName + " added successfully and request to next page is made.");
					request.getRequestDispatcher("addFruitItems.html").forward(request, response);
				}
			}
			if (request.getParameter("backward") != null) {
				LOGGER.info("Request to previous page is made and the changes are rollbacked from database.");
				if (counter == 1) {
					vegItemsService.deleteVegItemsByCount(counterVeg, dietName);
					nonVegItemsService.deleteNonVegItemsByCount(counter, dietName);
					request.getRequestDispatcher("addVegItems.html").forward(request, response);
				}else if(counter == 0){
					vegItemsService.deleteVegItemsByCount(counterVeg, dietName);
					request.getRequestDispatcher("addVegItems.html").forward(request, response);
				}else {
					counter -= 1;
					nonVegItemsService.deleteNonVegItemsByCount(counter, dietName);
					request.getRequestDispatcher("addNonVegItems.html").forward(request, response);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error generated while forwarding request from servlet. Message: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
