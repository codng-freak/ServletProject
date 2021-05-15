package com.project.dao;

import java.sql.SQLException;
import com.project.bean.SignUpBean;

/**
 * This interface defines the methods that need to be implemented for carrying out basic operations for users.
 * Contains 3 methods to insert a new user, login user to the application and read the details of a particular user.
 * @author Harsh Sharma
 *
 */
public interface SignUpDao {
	
	/**
	 * Method to insert a new user in the database. It takes a SignUpBean object as a parameter that contains the details regarding
	 * the user. It returns an integer if the insertion was successful or not.
	 * @param signUpBean A object of SignUpBean class.
	 * @return int The result after insertion i.e., 1 or 0.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
    int insertUser(SignUpBean signUpBean) throws ClassNotFoundException, SQLException;
    
    /**
     * Method to check if the user with given details exists in the database or not. If the user exists, the method return true and if not
     * then it returns false. Thus it gives a functionality of login and checking user credentials.
     * @param userName A String that holds the user's user name.
     * @param password A String that holds the user's password.
     * @return boolean True if user exists and false if the user doesn't exist in the database.
     * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
     * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
     */
    boolean loginUser(String userName, String password) throws ClassNotFoundException, SQLException;
    
    /**
	 * Method to read or retrieve all the user details for a particular user name. The user details as a object corresponding
	 * to a particular user name are fetched from the database and returned.
	 * @param userName Holds the name of the user whose details are to be fetched.
	 * @return SignUpBean An Object of SignUpBean.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
    SignUpBean readUserDetails(String userName) throws ClassNotFoundException, SQLException;
}
