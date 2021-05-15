package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.jdbc.code.SignUpUtility;
import com.project.bean.FruitItems;

/**
 * This class implements the FruitItemsDao interface and defines all it's methods. The methods are for inserting, retrieving, modifying
 * and deleting FruitItems from the database for a particular diet plan. This class takes the help of SignUpUtility class to establish and close
 * the connection with the database to perform the required operations.
 * @author Harsh Sharma
 *
 */
public class FruitItemsDaoImpl implements FruitItemsDao {
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
	static final Logger LOGGER = Logger.getLogger(FruitItemsDaoImpl.class);

	@Override
	public int insertFruitItem(FruitItems fruitItems) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("Inside insertFruitItems method of dao class");
			int rowsUpdateCount = 0;
			String itemName = fruitItems.getItemName();
			String dietName = fruitItems.getDietName();
			double amount = fruitItems.getAmount();
			double calories = fruitItems.getCaloriesPer100Grams();
			double totalCalories = fruitItems.getTotalCalories();

			String insertData = "INSERT INTO fruit_items (diet_name, item_name, amount_in_grams, calories_per_100_grams, total_calories) VALUES (?,?,?,?,?)";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(insertData);
			preparedStatement.setString(1, dietName);
			preparedStatement.setString(2, itemName);
			preparedStatement.setDouble(3, amount);
			preparedStatement.setDouble(4, calories);
			preparedStatement.setDouble(5, totalCalories);

			rowsUpdateCount = preparedStatement.executeUpdate();
			if (rowsUpdateCount > 0) {
				LOGGER.warn("Fruit item details successfully inserted in database");
				System.out.println("Insertion succesfull");
			} else {
				System.out.println("Insertion unsuccessful");
			}
			return rowsUpdateCount;
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error generated while inserting values in database. Insertion unsuccessful. Message: "
					+ e.getMessage());
			throw e;
		} finally {
			SignUpUtility.getDbConnectionClose();
		}
	}

	@Override
	public ArrayList<FruitItems> readFruitItems(String dietName) throws ClassNotFoundException, SQLException {
		ArrayList<FruitItems> fruitItems = new ArrayList<FruitItems>();
		try {
			LOGGER.info("Inside readFruitItems method of dao class");
			String retrieveData = "SELECT fruit_items.item_no, fruit_items.item_name, fruit_items.amount_in_grams, fruit_items.calories_per_100_grams"
					+ ", fruit_items.total_calories FROM fruit_items INNER JOIN diet_plan ON fruit_items.diet_name = diet_plan.diet_name and diet_plan.diet_name ='"
					+ dietName + "'";
			connection = SignUpUtility.getDbConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(retrieveData);
			while (rs.next()) {
				String itemName = rs.getString("item_name");
				double amount = rs.getDouble("amount_in_grams");
				double calories = rs.getDouble("calories_per_100_grams");
				double totalCalories = rs.getDouble("total_calories");
				int itemNum = rs.getInt("item_no");

				fruitItems.add(new FruitItems(itemNum, itemName, amount, calories, totalCalories));
			}
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error while fetching fruit items details for diet " + dietName + " from database. Message: "
					+ e.getMessage());
			throw e;
		} finally {
			SignUpUtility.getDbConnectionClose();
		}
		LOGGER.warn("Fruit Items details for diet " + dietName + " successfully fetched from database.");
		return fruitItems;
	}

	@Override
	public int updateFruitItems(FruitItems fruitItems) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("Inside updateFruitItems method of dao class");
			int rowsUpdateCount = 0;
			String updateData = "update fruit_items set item_name = ?, amount_in_grams = ?, calories_per_100_grams = ?, total_calories = ? where item_no = ?";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(updateData);
			preparedStatement.setString(1, fruitItems.getItemName());
			preparedStatement.setDouble(2, fruitItems.getAmount());
			preparedStatement.setDouble(3, fruitItems.getCaloriesPer100Grams());
			preparedStatement.setDouble(4, fruitItems.getTotalCalories());
			preparedStatement.setInt(5, fruitItems.getItemNum());

			rowsUpdateCount = preparedStatement.executeUpdate();
			if (rowsUpdateCount > 0) {
				LOGGER.warn("Fruit item is successfully updated in the database.");
				System.out.println("Fruit Item updated");
			} else {
				System.out.println("Fruit Item not updated.");
			}
			return rowsUpdateCount;
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(
					"Error generated while updating values in database. Updation unsuccessful. Message: \\\"+e.getMessage()");
			throw e;
		} finally {
			SignUpUtility.getDbConnectionClose();
		}
	}

	@Override
	public int deleteFruitItems(int itemNum) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("Inside deleteFruitItems method of dao class");
			int rowsUpdateCount = 0;
			String deleteData = "delete from fruit_items where item_no = ?";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(deleteData);
			preparedStatement.setInt(1, itemNum);

			rowsUpdateCount = preparedStatement.executeUpdate();
			if (rowsUpdateCount > 0) {
				LOGGER.warn("Fruit Item detials are successfully deleted from the database.");
				System.out.println("Fruit Item deleted successfully");
			} else {
				System.out.println("Fruit Item not deleted successfully");
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

	@Override
	public int deleteFruitItemsByCount(int counter, String dietName) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("Inside deleteFruitItemsByCount method of dao class");
			int rowsUpdateCount = 0;
			String deleteData = "delete from fruit_items where diet_name = ? order by item_no desc limit ?";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(deleteData);
			preparedStatement.setString(1, dietName);
			preparedStatement.setInt(2, counter);
			rowsUpdateCount = preparedStatement.executeUpdate();
			if (rowsUpdateCount > 0) {
				LOGGER.warn(counter + " Fruit Item details are successfully deleted from the database.");
				System.out.println("Fruit Item deleted successfully");
			} else {
				System.out.println("Fruit Item not deleted successfully");
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