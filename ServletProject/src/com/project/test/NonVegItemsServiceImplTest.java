package com.project.test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import com.project.bean.NonVegItems;
import com.project.service.NonVegItemsService;
import com.project.service.NonVegItemsServiceImpl;

/**
 * Methods to test service layer methods of NonVegItems.
 * @author Harsh Sharma
 *
 */
class NonVegItemsServiceImplTest {
	
	/**
	 * A NonVegItemsService object.
	 */
	private NonVegItemsService nonVegItemService = new NonVegItemsServiceImpl();

	/**
	 * Method to test insertNonVegItems method of NonVegItemsService class.
	 * @throws ClassNotFoundException  Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void insertNonVegItemTest() throws ClassNotFoundException, SQLException {
		NonVegItems nonVegItems = new NonVegItems();
		nonVegItems.setItemName("Prawns");
		nonVegItems.setDietName("Fitness Plan");
		nonVegItems.setAmount(300);
		nonVegItems.setCaloriesPer100Gram(200);

		int expected = 1;
		int actual = nonVegItemService.insertNonVegItems(nonVegItems);
		assertEquals(expected, actual);
	}

	/**
	 * Method to test readNonVegItems method of NonVegItemsService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void readNonVegItemsTest() throws ClassNotFoundException, SQLException {
		String dietName = "Fitness Plan";
		ArrayList<NonVegItems> nonVegItems = new ArrayList<NonVegItems>();
		nonVegItems = nonVegItemService.readNonVegItems(dietName);
		boolean expected = true;
		boolean actual = false;
		for (int i = 0; i < nonVegItems.size(); i++) {
			if (!nonVegItems.get(i).getItemName().equals(null)) {
				actual = true;
			}
		}
		assertEquals(expected, actual);
	}

	/**
	 * Method to test updateNonVegItems method of NonVegItemsService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void updateNonVegItemsTest() throws ClassNotFoundException, SQLException {
		NonVegItems nonVegItems = new NonVegItems();
		nonVegItems.setItemNum(23);
		nonVegItems.setItemName("test");
		nonVegItems.setAmount(200);
		nonVegItems.setCaloriesPer100Gram(300);

		int expected = 1;
		int actual = nonVegItemService.updateNonVegItems(nonVegItems);
		assertEquals(expected, actual);
	}

	/**
	 * Method to test deleteNonVegItems method of NonVegItemsService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void deleteNonVegItemsTest() throws ClassNotFoundException, SQLException {
		int itemNum = 42;
		int expected = 1;
		int actual = nonVegItemService.deleteNonVegItems(itemNum);
		assertEquals(expected, actual);
	}
	
	/**
	 * Method to test deleteNonVegItemsByCount method of NonVegItemsService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void deleteNonVegItemsByCount() throws ClassNotFoundException, SQLException {

		int counter = 2;
		String dietName = "NewTest";
		int actual = nonVegItemService.deleteNonVegItemsByCount(counter, dietName);
		int expected = 2;
		assertEquals(expected, actual);
		
	}
}
