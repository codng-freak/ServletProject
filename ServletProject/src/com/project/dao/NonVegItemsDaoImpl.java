package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.jdbc.code.SignUpUtility;
import com.project.bean.NonVegItems;

/**
 * This class implements the NonVegItemsDao interface and defines all it's methods. The methods are for inserting, retrieving, modifying
 * and deleting NonVegItems from the database for a particular diet plan. This class takes the help of SignUpUtility class to establish and close
 * the connection with the database to perform the required operations.
 * @author Harsh Sharma
 *
 */
public class NonVegItemsDaoImpl implements NonVegItemsDao {
	
	/**
	 * A static Connection object set to null.
	 */
	private static Connection connection = null;
	
	/**
	 * A static PreparedStatement object set to null.
	 */
	private static PreparedStatement preparedStatement = null;
	
	/**
	 * A final Logger variable that is initialized for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(NonVegItemsDaoImpl.class);

	@Override
	public int insertNonVegItems(NonVegItems nonVegItems) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("Inside insertNonVegItems method of dao class");
			int rowsUpdateCount = 0;
			String dietName = nonVegItems.getDietName();
			String itemName = nonVegItems.getItemName();
			double amount = nonVegItems.getAmount();
			double calories = nonVegItems.getCaloriesPer100Gram();
			double totalCalories = nonVegItems.getTotalCalories();

			String insertData = "INSERT INTO non_veg_items (diet_name, item_name, amount_in_grams, calories_per_100_grams, total_calories) VALUES (?,?,?,?,?)";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(insertData);
			preparedStatement.setString(1, dietName);
			preparedStatement.setString(2, itemName);
			preparedStatement.setDouble(3, amount);
			preparedStatement.setDouble(4, calories);
			preparedStatement.setDouble(5, totalCalories);

			rowsUpdateCount = preparedStatement.executeUpdate();
			if (rowsUpdateCount > 0) {
				LOGGER.warn("non Veg item details successfully inserted in database");
				System.out.println("Non veg item inserted");
			} else {
				System.out.println("Non veg item not inserted");
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
	public ArrayList<NonVegItems> readNonVegItems(String dietName) throws ClassNotFoundException, SQLException {
		ArrayList<NonVegItems> nonVegItems = new ArrayList<NonVegItems>();
		try {
			LOGGER.info("Inside readNonVegItems method of dao class");
			String retrieveData = "SELECT non_veg_items.item_no, non_veg_items.item_name, non_veg_items.amount_in_grams, non_veg_items.calories_per_100_grams, non_veg_items.total_calories "
					+ "FROM non_veg_items INNER JOIN diet_plan ON non_veg_items.diet_name = diet_plan.diet_name and diet_plan.diet_name = ? ";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(retrieveData);
			preparedStatement.setString(1, dietName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String itemName = rs.getString("item_name");
				double amount = rs.getDouble("amount_in_grams");
				double calories = rs.getDouble("calories_per_100_grams");
				double total_calories = rs.getDouble("total_calories");
				int itemNum = rs.getInt("item_no");

				nonVegItems.add(new NonVegItems(itemNum, itemName, amount, calories, total_calories));
			}
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error while fetching non veg items details for diet " + dietName + " from database. Message: "
					+ e.getMessage());
			throw e;
		} finally {
			SignUpUtility.getDbConnectionClose();
		}
		LOGGER.warn("Non Veg Items details for diet " + dietName + " successfully fetched from database.");
		return nonVegItems;
	}

	@Override
	public int updateNonVegItems(NonVegItems nonVegItems) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("Inside updateNonVegItems method of dao class");
			int rowsUpdateCount = 0;
			String updateData = "update non_veg_items set item_name = ?, amount_in_grams = ?, calories_per_100_grams = ?, total_calories = ? where item_no = ?";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(updateData);
			preparedStatement.setString(1, nonVegItems.getItemName());
			preparedStatement.setDouble(2, nonVegItems.getAmount());
			preparedStatement.setDouble(3, nonVegItems.getCaloriesPer100Gram());
			preparedStatement.setDouble(4, nonVegItems.getTotalCalories());
			preparedStatement.setInt(5, nonVegItems.getItemNum());

			rowsUpdateCount = preparedStatement.executeUpdate();
			if (rowsUpdateCount > 0) {
				LOGGER.warn("Non Veg item is successfully updated in the database.");
				System.out.println("Non Veg Item updated");
			} else {
				System.out.println("Non Veg Item not updated.");
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
	public int deleteNonVegItems(int itemNum) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("Inside deleteNonVegItems method of dao class");
			int rowsUpdateCount = 0;
			String deleteData = "delete from non_veg_items where item_no = ?";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(deleteData);
			preparedStatement.setInt(1, itemNum);

			rowsUpdateCount = preparedStatement.executeUpdate();
			if (rowsUpdateCount > 0) {
				LOGGER.warn("Non Veg Item detials are successfully deleted from the database.");
				System.out.println("Non Veg Item deleted successfully");
			} else {
				System.out.println("Non Veg Item not deleted successfully");
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
	public int deleteNonVegItemsByCount(int counter, String dietName) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("Inside deleteNonVegItemsByCount method of dao class");
			int rowsUpdateCount = 0;
			String deleteData = "delete from non_veg_items where diet_name = ? order by item_no desc limit ?";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(deleteData);
			preparedStatement.setString(1, dietName);
			preparedStatement.setInt(2, counter);
			rowsUpdateCount = preparedStatement.executeUpdate();
			if (rowsUpdateCount > 0) {
				LOGGER.warn(counter + " Non Veg Item details are successfully deleted from the database.");
				System.out.println("Non Veg Item deleted successfully");
			} else {
				System.out.println("Non Veg Item not deleted successfully");
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
