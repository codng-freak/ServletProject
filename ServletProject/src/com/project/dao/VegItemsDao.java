package com.project.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.project.bean.VegItems;

/**
 * This interface defines the methods that need to be implemented for carrying out basic operations for veg items.
 * Contains 5 methods to view, modify, delete and insert veg items.
 * @author Harsh Sharma
 *
 */
public interface VegItemsDao {
	
	/**
	 * Interface Method to insert a vegetarian item in the database. Takes a VegItems object as a parameter and returns an integer if the insertion 
	 * was successful or not.
	 * @param vegItems A object of VegItems class.
	 * @return int The result after insertion i.e., 1 or 0.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int insertVegItems(VegItems vegItems) throws ClassNotFoundException, SQLException;
	
	/**
	 * Interface Method to read or retrieve all the VegItems for a particular diet plan. All the vegetarian items corresponding
	 * to a particular diet name are fetched from the database and inserted in a list that is returned.
	 * @param dietName Holds the name of the diet plan whose VegItems are to be fetched.
	 * @return ArrayList A List of VegItems.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	ArrayList<VegItems> readVegItems(String dietName) throws ClassNotFoundException, SQLException;
	
	/**
	 * Interface Method to update a particular VegItem. It modifies the existing VegItem with new details/values.
	 * Takes in a VegItem as a parameter and returns a corresponding integer for success or failure of operation.
	 * @param vegItems A object of VegItems class.
	 * @return int The result after updating the VegItem i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int updateVegItems(VegItems vegItems) throws ClassNotFoundException,SQLException;
	
	/**
	 * Interface Method to delete a particular VegItem from the database. The VegItem corresponding to particular
	 * item number is deleted. Thus this method takes in an integer that is the vegetarian item number and on success or failure
	 * of deletion returns a respective integer number.
	 * @param itemNum Holds the vegetarian item number.
	 * @return int The result after deleting the VegItem i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int deleteVegItems(int itemNum) throws ClassNotFoundException,SQLException;
	
	/**
	 * Interface Method to delete more than one i.e., N(here counter) number of VegItem from the database for a particular
	 * diet name. Takes in the diet plan name and the number of VegItems to delete and on success or failure returns a 
	 * respective integer number.
	 * @param counter Holds the number that denotes how many VegItems are to be deleted.
	 * @param dietName Holds the name of the diet plan.
	 * @return int The result after deleting the VegItems i.e., 1 or 0 for success or failure respectively.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	int deleteVegItemsByCount(int counter, String dietName) throws ClassNotFoundException,SQLException;
}
