package com.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.project.bean.SignUpBean;
import com.project.service.SignUpService;
import com.project.service.SignUpServiceImpl;

/**
 * Defines method to request user details from the user and forward it to the service layer.
 * It takes data from the user via HTML and then calls the service layer methods to perform operations on the data. This servlet helps to 
 * retrieve user's SignUp and Login details from user and manipulate requests on the client side. It provides SignUp and Login functionality.
 * @author Harsh Sharma
 *
 */
public class ProjectServlet extends HttpServlet {

	/**
	 * A static final variable that stores serial version UID of the servlet.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * A static final logger for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(ProjectServlet.class);

	/**
	 * Method to request user details for SignUp and Login from the HTML page. Different operation is performed on different button click and
	 * the request generated is handled. This method takes user details and if the SignUp is requires it creates a SignUpBean object and forwards that
	 * object with the respective data to the service class.
	 * If the login operation is selected the userName and password of the user is verified and the proper response is provided by redirecting to the 
	 * next page.
	 * @param request A HttpServletRequest object.
	 * @param response A HttpServletResponse object.
	 * @throws ServletException Defines a general exception a servlet can throw when it encounters difficulty.
	 * @throws IOException Exceptions produced by failed or interrupted I/O operations
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = null;
		response.setContentType("text/html");
		LOGGER.info("Initial Servlet Executed");

		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String userName = request.getParameter("uname");
		String password = request.getParameter("pwd");
		String city = request.getParameter("cty");
		session = request.getSession();
		session.setAttribute("user", userName);

		if (request.getParameter("signUpForm") != null) {
			LOGGER.info("Sign Up process started");
			SignUpBean signUpBean = new SignUpBean();
			signUpBean.setFirstName(firstName);
			signUpBean.setLastName(lastName);
			signUpBean.setUserName(userName);
			signUpBean.setPassword(password);
			signUpBean.setCity(city);

			SignUpService signUpServiceImpl = new SignUpServiceImpl();
			try {
				int result = signUpServiceImpl.insertUser(signUpBean);
				if (result > 0) {
					LOGGER.info("Sign Up process completed for the user " + userName);
					request.getRequestDispatcher("signUpResponse.html").forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e) {
				LOGGER.error("Error while sign up process. Message:  " + e.getMessage());
				e.printStackTrace();
			}
		}
		if (request.getParameter("loginForm") != null) {

			LOGGER.info("Login Process started.");
			SignUpService signUpServiceImpl = new SignUpServiceImpl();
			try {
				boolean userPresent = signUpServiceImpl.loginUser(userName, password);
				if (userPresent == true) {
					LOGGER.info("Login successful for user "+userName);
					request.getRequestDispatcher("workingPage.html").forward(request, response);
				} else {
					LOGGER.error("Login unsuccessful.");
					PrintWriter out = response.getWriter();
					out.println("<center>");
					out.println("<h3>Wrong login credentials<h3></br>");
					out.println("<a href='loginPage.html'>Login again</a>");
					out.println("</center>");
				}
			} catch (ClassNotFoundException | SQLException e) {
				LOGGER.error("Error while login process. Message: "+e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
