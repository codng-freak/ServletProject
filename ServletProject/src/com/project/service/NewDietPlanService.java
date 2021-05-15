package com.project.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.project.bean.DietPlan;

/**
 * This interface defines the methods that need to be implemented for carrying out basic operations for diet plans.
 * Contains 4 methods to view, modify, delete and insert diet plans. The methods that are defined here are used to perform
 * the operations by calling the DAO layer methods.
 * @author Harsh Sharma
 *
 */
public interface NewDietPlanService {
	
	/**
	 * Method to insert a diet plan by calling the respective insertDietDetails method of the DAO layer(NewDietPlanDao). 
	 * Takes a DietPlan class object and returns an integer if the DAO layer method is called successfully or not.
	 * @param diet An object of DietPlan class.
	 * @return int The result after calling the DAO method i.e., 1 or 0;
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int insertDietDetails(DietPlan diet) throws ClassNotFoundException, SQLException;
	
	/**
	 * Method to read or retrieve all the DietPlan for a particular user name. All the diet plans corresponding
	 * to a particular user name are fetched by calling the respective DAO layer method.
	 * @param userName Holds the user name of the user whose DietPlan are to be fetched.
	 * @return ArrayList A List of DietPlan.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	ArrayList<DietPlan> readDietDetails(String userName) throws ClassNotFoundException, SQLException;
	
	/**
	 * Method to update a particular DietPlan. It modifies the existing DietPlan with new details/values.
	 * Takes in a DietPlan as a parameter and returns a corresponding integer for success or failure of calling respective DAO layer method.
	 * @param diet A object of DietPlan class.
	 * @return int The result i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int updateDietDetails(DietPlan diet) throws ClassNotFoundException,SQLException;
	
	/**
	 * Method to delete a particular DietPlan by calling the respective DAO layer method. The DietPlan corresponding to particular
	 * diet number is deleted. Thus this method takes in an integer that is the diet plan number and on success or failure
	 * of calling DAO layer method returns a respective integer number.
	 * @param dietNum Holds the diet plan number.
	 * @return int The result i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int deleteDietPlan(int dietNum) throws ClassNotFoundException,SQLException;
}
