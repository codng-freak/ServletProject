package com.project.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.project.bean.DietPlan;
import com.project.service.NewDietPlanService;
import com.project.service.NewDietPlanServiceImpl;

/**
 * Defines method to request updated diet plan data from a user and forward that data to the service layer for further operations.
 * It takes data from the user via HTML and then calls the service layer methods to perform operations on the data. This servlet helps to 
 * retrieve diet plan details from user that he wants to update and manipulate requests on the client side.
 * @author Harsh Sharma
 *
 */
public class UpdateDietDetailsServlet extends HttpServlet {
	
	/**
	 * A static final variable that stores serial version UID of the servlet.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * A static final logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(UpdateDietDetailsServlet.class);

	/**
	 * Method to request diet plan data that is to be updated from the HTML page.
	 * @param request A HttpServletRequest object.
	 * @param response A HttpServletResponse object.
	 * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
	 * @throws IOException Exceptions produced by failed or interrupted I/O operations
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LOGGER.info("Inside doPost method of UpdateDietDetailsServlet class");
		HttpSession session = request.getSession();
		int dietNum = (int) session.getAttribute("dietNum");
		String userName = (String) session.getAttribute("user");
		response.setContentType("text/html");

		NewDietPlanService newDietPlanService = new NewDietPlanServiceImpl();
		try {

			if (request.getParameter("forward") != null) {
				String dietName = request.getParameter("dname");
				String dietTime = "";
				if (request.getParameter("inlineRadioOptions").equals("option1")) {
					dietTime = "Breakfast";
				}
				if (request.getParameter("inlineRadioOptions").equals("option2")) {
					dietTime = "Lunch";
				}
				if (request.getParameter("inlineRadioOptions").equals("option3")) {
					dietTime = "Dinner";
				}
				int dietDuration = Integer.parseInt(request.getParameter("duration"));

				DietPlan dietPlan = new DietPlan();
				dietPlan.setDietName(dietName);
				dietPlan.setTimeOfDiet(dietTime);
				dietPlan.setDietDurationInDays(dietDuration);
				dietPlan.setDietNum(dietNum);
				int result = newDietPlanService.updateDietDetails(dietPlan);
				if (result > 0) {
					LOGGER.info("Diet details updated succesfully for the diet: " + dietNum
							+ ". User forwarded to next page.");
					request.getRequestDispatcher("DisplayDietServlet").forward(request, response);
				}
			}
			if (request.getParameter("backward") != null) {
				LOGGER.warn("Diet details updation cancelled and user reverted back to previous page.");
				request.getRequestDispatcher("DisplayDietServlet?user=" + userName).forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error generated while executing servlet to update diet details.Message: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
