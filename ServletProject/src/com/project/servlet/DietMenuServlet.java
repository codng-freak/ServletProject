package com.project.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Defines method to get user request from the HTML page to the server and perform operation
 * according to the user's request.
 * @author Harsh
 *
 */
public class DietMenuServlet extends HttpServlet {
	
	/**
	 * A static final variable that stores serial version UID of the servlet.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * A static final logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(DietMenuServlet.class);
       
	/**
	 * A default constructor.
	 */
    public DietMenuServlet() {
        super();
    }
		
    /**
	 * doPost method that takes request from the user regarding the choice to create a new diet plan
	 * or view/update/delete a diet plan.
	 * @param request A HttpServletRequest object
	 * @param response A HttpServletRequest object
	 * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
	 * @throws IOException Exceptions produced by failed or interrupted I/O operations
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		LOGGER.info("DietMenuServlet initiated.");
		
		String choice = request.getParameter("flexRadioDefault");
		if(choice.equals("create") && (request.getParameter("selectOption") != null)) {
			LOGGER.info("Initiation of creating a new diet plan.");
			request.getRequestDispatcher("newDiet.html").forward(request, response);
		}
		if(choice.equals("view") && (request.getParameter("selectOption") != null)) {
			LOGGER.info("Viewing existing diet plans for the current user.");
			request.getRequestDispatcher("DisplayDietServlet").forward(request, response);
		}
	}
}
