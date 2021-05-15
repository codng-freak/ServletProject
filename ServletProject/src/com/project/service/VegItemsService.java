package com.project.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.project.bean.VegItems;

/**
 * This interface defines the methods that need to be implemented for carrying out basic operations for vegetarian items.
 * Contains 5 methods to view, modify, delete and insert vegetarian items. The methods that are defined here are used to perform
 * the operations by calling the DAO layer methods.
 * @author Harsh Sharma
 *
 */
public interface VegItemsService {
	
	/**
	 * Method to insert the vegetarian item by calling the respective insertVegItems method of the DAO layer(VegItemsDao). 
	 * Takes a VegItems class object and returns an integer if the DAO layer method is called successfully or not.
	 * @param vegItems An object of VegItems class.
	 * @return int The result after calling the DAO method i.e., 1 or 0;
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int insertVegItems(VegItems vegItems) throws ClassNotFoundException, SQLException;
	
	/**
	 * Method to read or retrieve all the VegItems for a particular diet plan. All the vegetarian items corresponding
	 * to a particular diet name are fetched by calling the respective DAO layer method.
	 * @param dietName Holds the name of the diet plan whose VegItems are to be fetched.
	 * @return ArrayList A List of VegItems.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	ArrayList<VegItems> readVegItems(String dietName) throws ClassNotFoundException, SQLException;
	
	/**
	 * Method to update a particular VegItem. It modifies the existing VegItem with new details/values.
	 * Takes in a VegItem as a parameter and returns a corresponding integer for success or failure of calling respective DAO layer method.
	 * @param vegItems A object of NonVegItems class.
	 * @return int The result i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int updateVegItems(VegItems vegItems) throws ClassNotFoundException, SQLException;
	
	/**
	 * Method to delete a particular VegItem by calling the respective DAO layer method. The VegItem corresponding to particular
	 * item number is deleted. Thus this method takes in an integer that is the vegetarian item number and on success or failure
	 * of calling DAO layer method returns a respective integer number.
	 * @param itemNo Holds the vegetarian item number.
	 * @return int The result i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int deleteVegItems(int itemNo) throws ClassNotFoundException,SQLException;
	
	/**
	 * Method to delete more than one i.e., N(here counter) number of VegItem by calling the respective DAO layer method for a particular
	 * diet name. Takes in the diet plan name and the number of VegItems to delete and on success or failure returns a 
	 * respective integer number.
	 * @param counter Holds the number that denotes how many VegItems are to be deleted.
	 * @param dietName Holds the name of the diet plan.
	 * @return int The result i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int deleteVegItemsByCount(int counter, String dietName) throws ClassNotFoundException,SQLException;
}
