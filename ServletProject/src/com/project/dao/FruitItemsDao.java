package com.project.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.project.bean.FruitItems;

/**
 * This interface defines the methods that need to be implemented for carrying out basic operations for fruit items.
 * Contains 5 methods to view, modify, delete and insert fruit items.
 * @author Harsh Sharma
 *
 */
public interface FruitItemsDao {
	/**
	 * Method to insert a fruit item in the database. Takes a FruitItems object as a parameter and returns an integer if the insertion 
	 * was successful or not.
	 * @param fruitItems A object of FruitItems class.
	 * @return int The result after insertion i.e., 1 or 0.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int insertFruitItem(FruitItems fruitItems) throws ClassNotFoundException, SQLException;
	
	/**
	 * Method to read or retrieve all the FruitItems for a particular diet plan. All the fruit items corresponding
	 * to a particular diet name are fetched from the database and inserted in a list that is returned.
	 * @param dietName Holds the name of the diet plan whose FruitItems are to be fetched.
	 * @return ArrayList A List of FruitItems.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	ArrayList<FruitItems> readFruitItems(String dietName) throws ClassNotFoundException, SQLException;
	
	/**
	 * Method to update a particular FruitItem. It modifies the existing FruitItem with new details/values.
	 * Takes in a FruitItem as a parameter and returns a corresponding integer for success or failure of operation.
	 * @param fruitItems A object of FruitItems class.
	 * @return int The result after updating the FruitItem i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int updateFruitItems(FruitItems fruitItems) throws ClassNotFoundException,SQLException;
	
	/**
	 * Method to delete a particular FruitItem from the database. The FruitItem corresponding to particular
	 * item number is deleted. Thus this method takes in an integer that is the fruit item number and on success or failure
	 * of deletion returns a respective integer number.
	 * @param itemNum Holds the fruit item number.
	 * @return int The result after deleting the FruitItem i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int deleteFruitItems(int itemNum) throws ClassNotFoundException,SQLException;
	
	/**
	 * Method to delete more than one i.e., N(here counter) number of FruitItem from the database for a particular
	 * diet name. Takes in the diet plan name and the number of FruitItems to delete and on success or failure returns a 
	 * respective integer number.
	 * @param counter Holds the number that denotes how many FruitItems are to be deleted.
	 * @param dietName Holds the name of the diet plan.
	 * @return int The result after deleting the FruitItems i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int deleteFruitItemsByCount(int counter, String dietName) throws ClassNotFoundException,SQLException;
}
