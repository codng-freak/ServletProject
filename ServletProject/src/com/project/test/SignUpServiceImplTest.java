package com.project.test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import com.project.bean.SignUpBean;
import com.project.service.SignUpService;
import com.project.service.SignUpServiceImpl;

/**
 * Methods to test service layer methods of SignUpBean.
 * @author Harsh Sharma
 *
 */
class SignUpServiceImplTest {
	
	/**
	 * A SignUpService object.
	 */
	private SignUpService signUpService = new SignUpServiceImpl();

	/**
	 * Method to test insertUser method of SignUpService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void insertUserTest() throws ClassNotFoundException, SQLException {
		SignUpBean signUpBean = new SignUpBean();
		signUpBean.setFirstName("Varun");
		signUpBean.setLastName("Verma");
		signUpBean.setUserName("varun1234");
		signUpBean.setPassword("12345");
		signUpBean.setCity("Mumbai");
		
		int expected = 1;
		int actual = signUpService.insertUser(signUpBean);
		assertEquals(expected, actual);
	}
	
	/**
	 * Method to test loginUser method of SignUpService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void loginUserTest() throws ClassNotFoundException, SQLException{
		String userName = "vipul1398";
		String password = "1310";
		boolean expected = true;
		boolean actual = signUpService.loginUser(userName, password);
		assertEquals(expected, actual);
	}
	
	/**
	 * Method to test readUserDetails method of SignUpService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void readUserDetailsTest() throws ClassNotFoundException, SQLException{
		String userName = "vipul1398";
		SignUpBean signUpBean = signUpService.readUserDetails(userName);
		String expected = "Harsh";
		String actual = signUpBean.getFirstName();
		assertEquals(expected, actual);
	}

}
