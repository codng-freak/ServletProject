package com.project.test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import com.project.bean.FruitItems;
import com.project.service.FruitItemsService;
import com.project.service.FruitItemsServiceImpl;


/**
 * Methods to test service layer methods of FruitItems.
 * @author Harsh Sharma
 *
 */
class FruitItemsServiceImplTest {

	/**
	 * A FruitItemsService object.
	 */
	private FruitItemsService fruitItemsService = new FruitItemsServiceImpl();

	/**
	 * Method to test insertFruitItem method of FruitItemsService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void insertFruitItemTest() throws ClassNotFoundException,SQLException{
		FruitItems fruitItems = new FruitItems();
		fruitItems.setItemName("Guava");
		fruitItems.setDietName("Fitness Plan");
		fruitItems.setAmount(100);
		fruitItems.setCaloriesPer100Grams(80);
		
		int actual = fruitItemsService.insertFruitItems(fruitItems);
		int expected = 1;
		assertEquals(expected, actual);
	}
	
	/**
	 * Method to test readFruitItems method of FruitItemsService class
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void readFruitItemsTest() throws ClassNotFoundException,SQLException{
		String dietName = "Fitness Plan";
		ArrayList<FruitItems> fruitItems = new ArrayList<FruitItems>();
		fruitItems = fruitItemsService.readFruitItems(dietName);
		boolean expected = true;
		boolean actual = false;
		for(int i=0;i<fruitItems.size();i++) {
			if(!fruitItems.get(i).getItemName().equals(null)) {
				actual = true;
			}
		}
		assertEquals(expected, actual);
	}
	
	/**
	 * Method to test updateFruitItems method of FruitItemsService class
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void updateFruitItemsTest() throws ClassNotFoundException,SQLException{
		FruitItems fruitItems = new FruitItems();
		fruitItems.setItemNum(33);
		fruitItems.setItemName("test");
		fruitItems.setAmount(100);
		fruitItems.setCaloriesPer100Grams(250);
		
		int expected = 1;
		int actual = fruitItemsService.updateFruitItems(fruitItems);
		assertEquals(expected, actual);
	}
	
	/**
	 * Method to test deleteFruitItems method of FruitItemsService class
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void deleteFruitItemsTest() throws ClassNotFoundException,SQLException{
		int itemNum = 48;
		int expected = 1;
		int actual = fruitItemsService.deleteFruitItems(itemNum);
		assertEquals(expected, actual);
	}

	/**
	 * Method to test deleteFruitItemsByCount method of FruitItemsService class
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void deleteFruitItemsByCount() throws ClassNotFoundException, SQLException {

		int counter = 2;
		String dietName = "NewTest";
		int actual = fruitItemsService.deleteFruitItemsByCount(counter, dietName);
		int expected = 2;
		assertEquals(expected, actual);
		
	}
}
