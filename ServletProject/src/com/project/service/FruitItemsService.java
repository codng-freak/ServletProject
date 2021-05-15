package com.project.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.project.bean.FruitItems;

/**
 * This interface defines the methods that need to be implemented for carrying out basic operations for fruit items.
 * Contains 5 methods to view, modify, delete and insert fruit items. The methods that are defined here are used to perform
 * the operations by calling the DAO layer methods.
 * @author Harsh Sharma
 *
 */
public interface FruitItemsService {
	
	/**
	 * Method to insert the fruit item by calling the respective insertFruitItems method of the DAO layer(FruitItemsDao). 
	 * Takes a FruitItems class object and returns an integer if the DAO layer method is called successfully or not.
	 * @param fruitItems An object of FruitItems class.
	 * @return int The result after calling the DAO method i.e., 1 or 0;
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int insertFruitItems(FruitItems fruitItems) throws ClassNotFoundException, SQLException;
	
	/**
	 * Method to read or retrieve all the FruitItems for a particular diet plan. All the fruit items corresponding
	 * to a particular diet name are fetched by calling the respective DAO layer method.
	 * @param dietName Holds the name of the diet plan whose FruitItems are to be fetched.
	 * @return ArrayList A List of FruitItems.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	ArrayList<FruitItems> readFruitItems(String dietName) throws ClassNotFoundException, SQLException;
	
	/**
	 * Method to update a particular FruitItem. It modifies the existing FruitItem with new details/values.
	 * Takes in a FruitItem as a parameter and returns a corresponding integer for success or failure of calling respective DAO layer method.
	 * @param fruitItems A object of FruitItems class.
	 * @return int The result i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int updateFruitItems(FruitItems fruitItems) throws ClassNotFoundException,SQLException;
	
	/**
	 * Method to delete a particular FruitItem by calling the respective DAO layer method. The FruitItem corresponding to particular
	 * item number is deleted. Thus this method takes in an integer that is the fruit item number and on success or failure
	 * of calling DAO layer method returns a respective integer number.
	 * @param itemNum Holds the fruit item number.
	 * @return int The result i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int deleteFruitItems(int itemNum) throws ClassNotFoundException,SQLException;
	
	/**
	 * Method to delete more than one i.e., N(here counter) number of FruitItem by calling the respective DAO layer method for a particular
	 * diet name. Takes in the diet plan name and the number of FruitItems to delete and on success or failure returns a 
	 * respective integer number.
	 * @param counter Holds the number that denotes how many FruitItems are to be deleted.
	 * @param dietName Holds the name of the diet plan.
	 * @return int The result i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int deleteFruitItemsByCount(int counter, String dietName) throws ClassNotFoundException,SQLException;
}
