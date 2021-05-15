package com.project.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.project.bean.FruitItems;
import com.project.service.FruitItemsService;
import com.project.service.FruitItemsServiceImpl;
import com.project.service.NonVegItemsService;
import com.project.service.NonVegItemsServiceImpl;

/**
 * Defines method to request Fruit items data from a user and forward that data to the service layer for further operations.
 * It takes data from the user via HTML and then calls the service layer methods to perform operations on the data. This servlet helps to 
 * retrieve fruit items details from user and manipulate requests on the client side.
 * @author Harsh Sharma
 *
 */
public class AddFruitItemsServlet extends HttpServlet {
	
	/**
	 * A static final variable that stores serial version UID of the servlet.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * A static final logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(AddFruitItemsServlet.class);
	
	/**
	 * A static counter variable with a value of 0.
	 */
	private static int counter = 0;

	/**
	 * A Default Constructor that has a super method.
	 */
	public AddFruitItemsServlet() {
		super();
	}

	/**
	 * Method to request fruit items data from the HTML page.
	 * @param request A HttpServletRequest object.
	 * @param response A HttpServletResponse object.
	 * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
	 * @throws IOException Exceptions produced by failed or interrupted I/O operations
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("In AddFruitItemsServlet class");
		HttpSession session = request.getSession();
		String dietName = (String) session.getAttribute("dietName");
		int counterNonVeg = (int) session.getAttribute("counterNonVeg");

		FruitItemsService fruitItemsService = new FruitItemsServiceImpl();
		NonVegItemsService nonVegItemsService = new NonVegItemsServiceImpl();
		try {
			/*
			 * If the Add button on the HTML page is pressed then this if condition gets satisfied and the following code
			 * gets executed.
			 */
			if (request.getParameter("repeat") != null) {
				String itemName = request.getParameter("iname");
				double amount = Double.parseDouble(request.getParameter("amount"));
				double calories = Double.parseDouble(request.getParameter("calories"));

				/*
				 * Creating a fruit item object and calling the insertFruitItems method through service layer object.
				 */
				FruitItems fruitItem = new FruitItems();
				fruitItem.setItemName(itemName);
				fruitItem.setDietName(dietName);
				fruitItem.setAmount(amount);
				fruitItem.setCaloriesPer100Grams(calories);
				int result = fruitItemsService.insertFruitItems(fruitItem);
				if (result > 0) {
					counter++;
					LOGGER.info("Fruit Item " + itemName
							+ " added successfully and request for adding another fruit item is generated.");
					request.getRequestDispatcher("addFruitItems.html").forward(request, response);
				}
			}
			
			/*
			 * If the Finish button on the HTML page is pressed then this if condition gets satisfied and the following code
			 * gets executed.
			 */
			if (request.getParameter("forward") != null) {
				String itemName = request.getParameter("iname");
				double amount = Double.parseDouble(request.getParameter("amount"));
				double calories = Double.parseDouble(request.getParameter("calories"));

				FruitItems fruitItem = new FruitItems();
				fruitItem.setItemName(itemName);
				fruitItem.setDietName(dietName);
				fruitItem.setAmount(amount);
				fruitItem.setCaloriesPer100Grams(calories);
				int result = fruitItemsService.insertFruitItems(fruitItem);
				if (result > 0) {
					counter += 1;
					LOGGER.info("Fruit Item " + itemName + " added successfully and request to next page is made.");
					request.getRequestDispatcher("dietPlanCreated.html").forward(request, response);
				}
			}
			/*
			 * If the Back button on the HTML page is pressed then this if condition gets satisfied and the following code
			 * gets executed.The counter is used to count how many items have been already inserted into the database. If the 
			 * back button is pressed a single item is deleted from the database. If the back button is pressed after inserting only one
			 * item, then it deletes that item and goes back to the previous page. 
			 */
			if (request.getParameter("backward") != null) {
				if (counter == 1) {
					LOGGER.info("Request to previous page is made and the changes are rollbacked from database.");
					fruitItemsService.deleteFruitItemsByCount(counter, dietName);
					nonVegItemsService.deleteNonVegItemsByCount(counterNonVeg, dietName);
					request.getRequestDispatcher("addNonVegItems.html").forward(request, response);
				}else {
					counter -= 1; // decrement counter by 1.
					fruitItemsService.deleteFruitItemsByCount(counter, dietName);
					request.getRequestDispatcher("addFruitItems.html").forward(request, response);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error generated while forwarding request from servlet. Message: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
