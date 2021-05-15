package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.jdbc.code.SignUpUtility;
import com.project.bean.VegItems;

/**
 * This class implements the VegItemsDao interface and defines all it's methods. The methods are for inserting, retrieving, modifying
 * and deleting VegItems from the database for a particular diet plan. This class takes the help of SignUpUtility class to establish and close
 * the connection with the database to perform the required operations.
 * @author Harsh Sharma
 *
 */
public class VegItemsDaoImpl implements VegItemsDao {
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
	static final Logger LOGGER = Logger.getLogger(VegItemsDaoImpl.class);

	@Override
	public int insertVegItems(VegItems vegItems) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("Inside insertVegItems method of dao class");
			int rowsUpdateCount = 0;
			String itemName = vegItems.getItemName();
			String itemType = vegItems.getItemType();
			String dietName = vegItems.getDietName();
			double amount = vegItems.getAmount();
			double calories = vegItems.getCaloriesPer100Grams();
			double total_calories = vegItems.getTotalCalories();

			String inserData = "INSERT INTO veg_items (diet_name, item_name, item_type, amount_in_grams, calories_per_100_grams, total_calories) VALUES (?,?,?,?,?,?)";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(inserData);
			preparedStatement.setString(1, dietName);
			preparedStatement.setString(2, itemName);
			preparedStatement.setString(3, itemType);
			preparedStatement.setDouble(4, amount);
			preparedStatement.setDouble(5, calories);
			preparedStatement.setDouble(6, total_calories);

			rowsUpdateCount = preparedStatement.executeUpdate();
			if (rowsUpdateCount > 0) {
				LOGGER.warn("Veg item details successfully inserted in database");
				System.out.println("Veg item inserted");
			} else {
				System.out.println("Veg item not inserted");
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
	public ArrayList<VegItems> readVegItems(String dietName) throws ClassNotFoundException, SQLException {
		ArrayList<VegItems> vegItems = new ArrayList<VegItems>();
		try {
			LOGGER.info("Inside readVegItems method of dao class");
			String retrieveData = "SELECT veg_items.item_no, veg_items.item_name, veg_items.item_type, veg_items.amount_in_grams, veg_items.calories_per_100_grams"
					+ ", veg_items.total_calories FROM veg_items INNER JOIN diet_plan ON diet_plan.diet_name = veg_items.diet_name AND diet_plan.diet_name = ?";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(retrieveData);
			preparedStatement.setString(1, dietName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int itemNum = rs.getInt("item_no");
				String itemName = rs.getString("item_name");
				String itemType = rs.getString("item_type");
				double amount = rs.getDouble("amount_in_grams");
				double calories = rs.getDouble("calories_per_100_grams");
				double totalCalories = rs.getDouble("total_calories");

				vegItems.add(new VegItems(itemNum, itemName, itemType, amount, calories, totalCalories));
			}
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error while fetching veg items details for diet " + dietName + " from database. Message: "
					+ e.getMessage());
			throw e;
		} finally {
			SignUpUtility.getDbConnectionClose();
		}
		LOGGER.warn("Veg Items details for diet " + dietName + " successfully fetched from database.");
		return vegItems;
	}

	@Override
	public int updateVegItems(VegItems vegItems) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("Inside updateVegItems method of dao class");
			int rowsUpdateCount = 0;
			String updateData = "update veg_items set item_name = ?, item_type = ?, amount_in_grams = ?, calories_per_100_grams = ?, total_calories = ? where item_no = ?";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(updateData);
			preparedStatement.setString(1, vegItems.getItemName());
			preparedStatement.setString(2, vegItems.getItemType());
			preparedStatement.setDouble(3, vegItems.getAmount());
			preparedStatement.setDouble(4, vegItems.getCaloriesPer100Grams());
			preparedStatement.setDouble(5, vegItems.getTotalCalories());
			preparedStatement.setInt(6, vegItems.getItemNum());

			rowsUpdateCount = preparedStatement.executeUpdate();
			if (rowsUpdateCount > 0) {
				LOGGER.warn("Veg item is successfully updated in the database.");
				System.out.println("Veg Item updated");
			} else {
				System.out.println("Veg Item not updated.");
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
	public int deleteVegItems(int itemNum) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("Inside deleteVegItems method of dao class");
			int rowsUpdateCount = 0;
			String deleteData = "delete from veg_items where item_no = ?";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(deleteData);
			preparedStatement.setInt(1, itemNum);

			rowsUpdateCount = preparedStatement.executeUpdate();
			if (rowsUpdateCount > 0) {
				LOGGER.warn("Veg Item details are successfully deleted from the database.");
				System.out.println("Veg Item deleted successfully");
			} else {
				System.out.println("Veg Item not deleted successfully");
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
	public int deleteVegItemsByCount(int counter, String dietName) throws ClassNotFoundException, SQLException {
		try {
			LOGGER.info("Inside deleteVegItemsByCount method of dao class");
			int rowsUpdateCount = 0;
			String deleteData = "delete from veg_items where diet_name = ? order by item_no desc limit ?";
			connection = SignUpUtility.getDbConnection();
			preparedStatement = connection.prepareStatement(deleteData);
			preparedStatement.setString(1, dietName);
			preparedStatement.setInt(2, counter);
			rowsUpdateCount = preparedStatement.executeUpdate();
			if (rowsUpdateCount > 0) {
				LOGGER.warn(counter + " Veg Item details are successfully deleted from the database.");
				System.out.println("Veg Item deleted successfully");
			} else {
				System.out.println("Veg Item not deleted successfully");
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
