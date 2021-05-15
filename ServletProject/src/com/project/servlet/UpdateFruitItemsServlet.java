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

/**
 * Defines method to request Fruit items data that has to be updated from a user and forward that data to the service layer for further operations.
 * It takes data from the user via HTML and then calls the service layer methods to perform operations on the data. This servlet helps to 
 * retrieve updated fruit items details from user and manipulate requests on the client side.
 * @author Harsh Sharma
 *
 */
public class UpdateFruitItemsServlet extends HttpServlet {
	
	/**
	 * A static final variable that stores serial version UID of the servlet.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * A static final logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(UpdateFruitItemsServlet.class);

	/**
	 * Method to request updated fruit items data from the HTML page.
	 * @param request A HttpServletRequest object.
	 * @param response A HttpServletResponse object.
	 * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
	 * @throws IOException Exceptions produced by failed or interrupted I/O operations
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Inside doPost method of UpdateFruitItemsServlet class");
		HttpSession session = request.getSession();
		int itemNum = (int) session.getAttribute("itemNumFruit");
		String dietName = (String) session.getAttribute("diet");
		response.setContentType("text/html");

		FruitItemsService fruitItemsService = new FruitItemsServiceImpl();
		try {
			if (request.getParameter("forward") != null) {
				String itemName = request.getParameter("iname");
				double amount = Double.parseDouble(request.getParameter("amount"));
				double calories = Double.parseDouble(request.getParameter("calories"));

				FruitItems fruitItems = new FruitItems();
				fruitItems.setItemName(itemName);
				fruitItems.setAmount(amount);
				fruitItems.setCaloriesPer100Grams(calories);
				fruitItems.setItemNum(itemNum);
				int result = fruitItemsService.updateFruitItems(fruitItems);
				if (result > 0) {
					LOGGER.info("Fruit Item updated successfully for item number " + itemNum
							+ " and request to next page is made.");
					request.getRequestDispatcher("CompleteDietServlet?dietName=" + dietName).forward(request, response);
				}
			}
			if (request.getParameter("backward") != null) {
				LOGGER.info("Request to previous page is made.");
				request.getRequestDispatcher("CompleteDietServlet?dietName=" + dietName).forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error generated while forwarding request from servlet. Message: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
