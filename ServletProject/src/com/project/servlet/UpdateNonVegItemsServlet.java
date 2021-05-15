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

/**
 * Defines method to request updated Non vegetarian items data from a user and forward that data to the service layer for further operations.
 * It takes data from the user via HTML and then calls the service layer methods to perform operations on the data. This servlet helps to 
 * retrieve updated non vegetarian items details from user and manipulate requests on the client side.
 * @author Harsh Sharma
 *
 */
public class UpdateNonVegItemsServlet extends HttpServlet {
	
	/**
	 * A static final variable that stores serial version UID of the servlet.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * A static final logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(UpdateNonVegItemsServlet.class);

	/**
	 * Method to request updated non vegetarian items data from the HTML page.
	 * @param request A HttpServletRequest object.
	 * @param response A HttpServletResponse object.
	 * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
	 * @throws IOException Exceptions produced by failed or interrupted I/O operations
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Inside doPost method of UpdateNonVegItemsServlet class");
		HttpSession session = request.getSession();
		int itemNum = (int) session.getAttribute("itemNumNonVeg");
		String dietName = (String) session.getAttribute("diet");
		response.setContentType("text/html");

		NonVegItemsService nonVegItemsService = new NonVegItemsServiceImpl();
		try {
			if (request.getParameter("forward") != null) {
				String itemName = request.getParameter("iname");
				double amount = Double.parseDouble(request.getParameter("amount"));
				double calories = Double.parseDouble(request.getParameter("calories"));

				NonVegItems nonVegItems = new NonVegItems();
				nonVegItems.setItemName(itemName);
				nonVegItems.setAmount(amount);
				nonVegItems.setCaloriesPer100Gram(calories);
				nonVegItems.setItemNum(itemNum);

				int result = nonVegItemsService.updateNonVegItems(nonVegItems);
				if (result > 0) {
					LOGGER.info("Non Veg Item updated successfully for item number " + itemNum
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
