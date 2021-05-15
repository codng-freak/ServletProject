package com.project.test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import com.project.bean.DietPlan;
import com.project.service.NewDietPlanService;
import com.project.service.NewDietPlanServiceImpl;

/**
 * Methods to test service layer methods of DietPlan.
 * @author Harsh Sharma
 *
 */
class NewDietPlanServiceImplTest {
	
	/**
	 * A NewDietPlanService object.
	 */
	private NewDietPlanService newDietPlanService = new NewDietPlanServiceImpl();

	/**
	 * Method to test insertDietDetails method of NewDietPlanService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void insertDietDetailsTest() throws ClassNotFoundException, SQLException{
		DietPlan dietPlan = new DietPlan();
		dietPlan.setDietName("Test");
		dietPlan.setDietNum(5);
		dietPlan.setDietDurationInDays(5);
		dietPlan.setTimeOfDiet("Lunch");
		dietPlan.setUserName("vipul1398");
		
		int expected = 1;
		int actual = newDietPlanService.insertDietDetails(dietPlan);
		assertEquals(expected, actual);
	}
	
	/**
	 * Method to test readDietDetails method of NewDietPlanService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void readDietDetailsTest() throws ClassNotFoundException, SQLException{
		String userName = "vipul1398";
		ArrayList<DietPlan> dietPlan = new ArrayList<DietPlan>();
		dietPlan = newDietPlanService.readDietDetails(userName);
		boolean actual = false;
		boolean expected = true;
		for(int i=0;i<dietPlan.size();i++) {
			if(!dietPlan.get(i).getDietName().equals(null)) {
				actual = true;
			}
		}
		assertEquals(expected, actual);
	}

	/**
	 * Method to test updateDietDetails method of NewDietPlanService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void updateDietDetailsTest() throws ClassNotFoundException,SQLException{
		DietPlan dietPlan = new DietPlan();
		dietPlan.setDietNum(6);
		dietPlan.setDietName("TestForUpdateSuccess");
		dietPlan.setTimeOfDiet("Dinner");
		dietPlan.setDietDurationInDays(12);
		
		int expected = 1;
		int actual = newDietPlanService.updateDietDetails(dietPlan);
		assertEquals(expected, actual);
	}
	
	/**
	 * Method to test deleteDietDetails method of NewDietPlanService class.
	 * @throws ClassNotFoundException Thrown when the requested class is not found in the class path.
	 * @throws SQLException Throws when an error occurs due to any wrong implementation of a SQL query or any database calls.
	 */
	@Test
	void deleteDietPlanTest() throws ClassNotFoundException,SQLException{
		int dietNum = 7;
		int expected = 1;
		int actual = newDietPlanService.deleteDietPlan(dietNum);
		assertEquals(expected, actual);
	}
}
