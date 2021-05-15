package com.project.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.project.bean.NonVegItems;

/**
 * This interface defines the methods that need to be implemented for carrying out basic operations for non vegetarian items.
 * Contains 5 methods to view, modify, delete and insert non vegetarian items. The methods that are defined here are used to perform
 * the operations by calling the DAO layer methods.
 * @author Harsh Sharma
 *
 */
public interface NonVegItemsService {
	
	/**
	 * Method to insert the non vegetarian item by calling the respective insertNonVegItems method of the DAO layer(NonVegItemsDao). 
	 * Takes a NonVegItems class object and returns an integer if the DAO layer method is called successfully or not.
	 * @param nonVegItems An object of NonVegItems class.
	 * @return int The result after calling the DAO method i.e., 1 or 0;
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int insertNonVegItems(NonVegItems nonVegItems) throws ClassNotFoundException, SQLException;
	
	/**
	 * Method to read or retrieve all the NonVegItems for a particular diet plan. All the non vegetarian items corresponding
	 * to a particular diet name are fetched by calling the respective DAO layer method.
	 * @param dietName Holds the name of the diet plan whose NonVegItems are to be fetched.
	 * @return ArrayList A List of NonVegItems.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	ArrayList<NonVegItems> readNonVegItems(String dietName) throws ClassNotFoundException, SQLException;
	
	/**
	 * Method to update a particular NonVegItem. It modifies the existing NonVegItem with new details/values.
	 * Takes in a NonVegItem as a parameter and returns a corresponding integer for success or failure of calling respective DAO layer method.
	 * @param nonVegItems A object of NonVegItems class.
	 * @return int The result i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int updateNonVegItems(NonVegItems nonVegItems) throws ClassNotFoundException,SQLException;
	
	/**
	 * Method to delete a particular NonVegItem by calling the respective DAO layer method. The NonVegItem corresponding to particular
	 * item number is deleted. Thus this method takes in an integer that is the non vegetarian item number and on success or failure
	 * of calling DAO layer method returns a respective integer number.
	 * @param itemNum Holds the non vegetarian item number.
	 * @return int The result i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int deleteNonVegItems(int itemNum) throws ClassNotFoundException,SQLException;
	
	/**
	 * Method to delete more than one i.e., N(here counter) number of NonVegItem by calling the respective DAO layer method for a particular
	 * diet name. Takes in the diet plan name and the number of NonVegItems to delete and on success or failure returns a 
	 * respective integer number.
	 * @param counter Holds the number that denotes how many NonVegItems are to be deleted.
	 * @param dietName Holds the name of the diet plan.
	 * @return int The result i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int deleteNonVegItemsByCount(int counter, String dietName) throws ClassNotFoundException, SQLException;
}
