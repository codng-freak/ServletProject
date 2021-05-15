package com.project.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.project.bean.DietPlan;

/**
 * This interface defines the methods that need to be implemented for carrying out basic operations for diet plans(DietPlan).
 * Contains 4 methods to view, modify, delete and insert a diet plan(DietPlan) into the database.
 * @author Harsh Sharma
 *
 */
public interface NewDietPlanDao {
	
	/**
	 * Interface Method to insert a diet plan in the database. Takes a DietPlan object as a parameter and returns an integer if the insertion 
	 * was successful or not.
	 * @param diet A object of DietPlan class.
	 * @return int The result after insertion i.e., 1 or 0.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int insertDietDetails(DietPlan diet) throws ClassNotFoundException, SQLException;
	
	/**
	 * Interface Method to read or retrieve all the DietPlans for a particular user. All the diet plans corresponding
	 * to a particular user name are fetched from the database and inserted in a list that is returned.
	 * @param userName Holds the name of the user whose DietPlan are to be fetched.
	 * @return ArrayList A List of DietPlans.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	ArrayList<DietPlan> readDietDetails(String userName) throws ClassNotFoundException, SQLException;
	
	/**
	 * Interface Method to update a particular DietPlan. It modifies the existing DietPlan with new details/values.
	 * Takes in a DietPlan object as a parameter and returns a corresponding integer for success or failure of operation.
	 * @param diet A object of DietPlan class.
	 * @return int The result after updating the DietPlan i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int updateDietDetails(DietPlan diet) throws ClassNotFoundException,SQLException;
	
	/**
	 * Interface Method to delete a particular DietPlan from the database. The DietPlan corresponding to particular
	 * diet number is deleted. Thus this method takes in an integer that is the diet plan number and on success or failure
	 * of deletion returns a respective integer number.
	 * @param dietNum Holds the diet plan number.
	 * @return int The result after deleting the DietPlan i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int deleteDietPlan(int dietNum) throws ClassNotFoundException,SQLException;
}
