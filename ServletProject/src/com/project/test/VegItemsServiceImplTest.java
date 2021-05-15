package com.project.test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import com.project.bean.VegItems;
import com.project.service.VegItemsService;
import com.project.service.VegItemsServiceImpl;

/**
 * Methods to test service layer methods of VegItems.
 * @author Harsh Sharma
 *
 */
class VegItemsServiceImplTest {
	
	/**
	 * A NonVegItemsService object.
	 */
	private VegItemsService vegItemsService = new VegItemsServiceImpl();

	/**
	 * Method to test insertVegItems method of VegItemsService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void insertVegItemsTest() throws ClassNotFoundException, SQLException {
		VegItems vegItems = new VegItems();
		vegItems.setItemName("Ladyfinger");
		vegItems.setItemType("Vegetable");
		vegItems.setDietName("Fitness Plan");
		vegItems.setAmount(200);
		vegItems.setCaloriesPer100Grams(120);

		int expected = 1;
		int actual = vegItemsService.insertVegItems(vegItems);
		assertEquals(expected, actual);
	}

	/**
	 * Method to test readVegItems method of VegItemsService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void readVegItemTest() throws ClassNotFoundException, SQLException {
		String dietName = "Fitness Plan";
		ArrayList<VegItems> vegItems = new ArrayList<VegItems>();
		vegItems = vegItemsService.readVegItems(dietName);
		boolean expected = true;
		boolean actual = false;
		for (int i = 0; i < vegItems.size(); i++) {
			if (!vegItems.get(i).getItemName().equals(null)) {
				actual = true;
			}
		}
		assertEquals(expected, actual);
	}

	/**
	 * Method to test updateVegItems method of VegItemsService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void updateVegItemsTest() throws ClassNotFoundException, SQLException {
		VegItems vegItems = new VegItems();
		vegItems.setItemNum(34);
		vegItems.setItemName("test");
		vegItems.setItemType("nuts");
		vegItems.setAmount(100);
		vegItems.setCaloriesPer100Grams(200);

		int expected = 1;
		int actual = vegItemsService.updateVegItems(vegItems);
		assertEquals(expected, actual);
	}

	/**
	 * Method to test deleteVegItems method of VegItemsService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void deleteVegItemsTest() throws ClassNotFoundException, SQLException {
		int itemNum = 94;
		int expected = 1;
		int actual = vegItemsService.deleteVegItems(itemNum);
		assertEquals(expected, actual);
	}

	/**
	 * Method to test deleteVegItemsByCount method of VegItemsService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void deleteVegItemsByCount() throws ClassNotFoundException, SQLException {

		int counter = 2;
		String dietName = "NewTest";
		int actual = vegItemsService.deleteVegItemsByCount(counter, dietName);
		int expected = 2;
		assertEquals(expected, actual);
		
	}
}
