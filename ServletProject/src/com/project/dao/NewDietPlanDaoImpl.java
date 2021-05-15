package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.jdbc.code.SignUpUtility;
import com.project.bean.DietPlan;

/**
 * This class implements the NewDietPlanDao interface and defines all it's methods. The methods are for inserting, retrieving, modifying
 * and deleting DietPlan from the database for a particular user. This class takes the help of SignUpUtility class to establish and close
 * the connection with the database to perform the required operations.
 * @author Harsh Sharma
 *
 */
public class NewDietPlanDaoImpl implements NewDietPlanDao {
	/**
	 * A static Connection object set to null.
	 */
	private static Connection connection = null;
	
	/**
	 * A static PreparedStatement object set to null.
	 */
	private static PreparedStatement preparedStatement = null;
	
	/**
	 * A static Statement object set to null.
	 */
	private static Statement statement = null;
	
	/**
	 * A final Logger variable that is initialized for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(NewDietPlanDaoImpl.class);

	@Override
	public int insertDietDetails(DietPlan diet) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("Inside insertDietDetails method of dao class");
			int rowsUpdateCount = 0;
			String dietName = diet.getDietName();
			int dietno = diet.getDietNum();
			String dietTime = diet.getTimeOfDiet();
			int dietDuration = diet.getDietDurationInDays();
			String userName = diet.getUserName();

			String insertData = "insert into diet_plan values" + "('" + userName + "','" + dietName + "'," + dietno
					+ ",'" + dietTime + "'," + dietDuration + ")";

			connection = SignUpUtility.getDbConnection();
			statement = connection.createStatement();
			rowsUpdateCount = statement.executeUpdate(insertData);
			if (rowsUpdateCount > 0) {
				LOGGER.warn("Diet details successfully inserted in the database.");
				System.out.println("Employee Registered successfully");
			} else {
				System.out.println("Something went wrong. Insertion unsuccessful");
			}

			return rowsUpdateCount;
		} catch (Exception e) {
			LOGGER.error("Diet details not inserted and resulted in a error. Message: " + e.getMessage());
			throw e;
		} finally {
			SignUpUtility.getDbConnectionClose();
		}
	}

	@Override
	public ArrayList<DietPlan> readDietDetails(String userName) throws ClassNotFoundException, SQLException {
		ArrayList<DietPlan> dietPlans = new ArrayList<DietPlan>();
		try {
			LOGGER.info("Inside readDietDetails method of dao class");
			String retrieveData = "select diet_plan.diet_name,diet_plan.diet_no,diet_plan.time_of_diet"
					+ ",diet_plan.diet_duration_in_days from diet_plan inner join signup on "
					+ "diet_plan.user_name = signup.user_name and signup.user_name ='" + userName
					+ "' order by diet_plan.diet_no asc";
			System.out.println(retrieveData);
			connection = SignUpUtility.getDbConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(retrieveData);
			while (rs.next()) {
				String dietName = rs.getString("diet_name");
				int dietNum = rs.getInt("diet_no");
				String dietTime = rs.getString("time_of_diet");
				int dietDuration = rs.getInt("diet_duration_in_days");

				dietPlans.add(new DietPlan(dietTime, dietDuration, dietName, dietNum));
			}
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error generated while fetching diet details from database.");
			throw e;
		} finally {
			SignUpUtility.getDbConnectionClose();
		}
		LOGGER.warn("Diet details for " + userName + " are fetched successfully from the database.");
		return dietPlans;
	}

	@Override
	public int updateDietDetails(DietPlan diet) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("Inside updateDietDetails method of dao class");
			int rowsUpdateCount = 0;
			String updateData = "update diet_plan set diet_name = ?, time_of_diet = ?, diet_duration_in_days = ? where diet_no = ?";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(updateData);
			preparedStatement.setString(1, diet.getDietName());
			preparedStatement.setString(2, diet.getTimeOfDiet());
			preparedStatement.setInt(3, diet.getDietDurationInDays());
			preparedStatement.setInt(4, diet.getDietNum());

			rowsUpdateCount = preparedStatement.executeUpdate();
			if (rowsUpdateCount > 0) {
				LOGGER.warn("Diet Plan detials for "+diet.getDietNum()+" are successfully updated in the database.");
				System.out.println("Diet plan updated");
			} else {
				System.out.println("Updation of diet plan unsuccessful");
			}
			return rowsUpdateCount;
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(
					"Error generated while updating values in database. Updation unsuccessful. Message: \"+e.getMessage()");
			throw e;
		} finally {
			SignUpUtility.getDbConnectionClose();
		}
	}

	@Override
	public int deleteDietPlan(int dietNum) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("Inside deleteDietPlan method of dao class");
			int rowsUpdateCount = 0;
			String deleteData = "delete from diet_plan where diet_no = ?";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(deleteData);
			preparedStatement.setInt(1, dietNum);

			rowsUpdateCount = preparedStatement.executeUpdate();
			if (rowsUpdateCount > 0) {
				LOGGER.warn("Diet Plan detials are successfully deleted from the database.");
				System.out.println("Diet plan deleted successfully");
			} else {
				System.out.println("Diet plan not deleted successfully");
			}
			return rowsUpdateCount;
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(
					"Error generated while deleting values in database. Deletion unsuccessful. Message: \"+e.getMessage()");
			throw e;
		} finally {
			SignUpUtility.getDbConnectionClose();
		}
	}
}
