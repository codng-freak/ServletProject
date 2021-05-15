package com.project.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.project.service.NewDietPlanService;
import com.project.service.NewDietPlanServiceImpl;

/**
 * Defines method to display the response from server to the user. Displays the diet plans list after a particular diet
 * plan is deleted.
 * @author Harsh Sharma
 *
 */
public class DeleteDietServlet extends HttpServlet {
	
	/**
	 * A static final variable that stores serial version UID of the servlet.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * A static final logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(DeleteDietServlet.class);

	/**
	 * doPost method that takes request from the user regarding which diet plan has to be deleted.
	 * @param request A HttpServletRequest object
	 * @param response A HttpServletRequest object
	 * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
	 * @throws IOException Exceptions produced by failed or interrupted I/O operations
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Inside doPost method of DeleteDietServlet class");
		HttpSession session = request.getSession();
		int dietNum = (int) session.getAttribute("dietNum");
		int rowsUpdateCount = 0;
		NewDietPlanService newDietPlanService = new NewDietPlanServiceImpl();
		try {
			rowsUpdateCount = newDietPlanService.deleteDietPlan(dietNum);
			if (rowsUpdateCount > 0) {
				LOGGER.info("Diet Plan is successfully deleted as per user's request.");
				request.getRequestDispatcher("DisplayDietServlet").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(
					"Error generated while executing servlet to forward request to DisplayDietServlet.Message: \"+e.getMessage()");
			e.printStackTrace();
		}
	}

	/**
	 * doGet method that calls the doPost method inside it to show the response from the server.
	 * @param request A HttpServletRequest object
	 * @param response A HttpServletRequest object
	 * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
	 * @throws IOException Exceptions produced by failed or interrupted I/O operations
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
