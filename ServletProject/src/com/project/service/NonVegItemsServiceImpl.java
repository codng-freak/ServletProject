package com.project.service;

import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.project.bean.NonVegItems;
import com.project.dao.NonVegItemsDao;
import com.project.dao.NonVegItemsDaoImpl;

/**
 * This class implements the NonVegItemsService interface and defines all it's methods. The methods are for inserting, retrieving, modifying
 * and deleting NonVegItems from the database for a particular diet plan by calling the respective DAO layer methods.
 * @author Harsh Sharma
 *
 */
public class NonVegItemsServiceImpl implements NonVegItemsService {

	/**
	 *  Object of FruitItemsDao class.
	 */
	private NonVegItemsDao nonVegItemsDao = new NonVegItemsDaoImpl();
	
	/**
	 * A final Logger variable that is initialized for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(NonVegItemsServiceImpl.class);

	@Override
	public int insertNonVegItems(NonVegItems nonVegItems) throws ClassNotFoundException, SQLException {
		int rowsUpdateCount = 0;
		try {
			LOGGER.info("Inserting non veg item details using dao class object.");
			rowsUpdateCount = nonVegItemsDao.insertNonVegItems(nonVegItems);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error generated while calling dao class method. Message: " + e.getMessage());
			e.printStackTrace();
		}
		return rowsUpdateCount;
	}

	@Override
	public ArrayList<NonVegItems> readNonVegItems(String dietName) throws ClassNotFoundException, SQLException {
		ArrayList<NonVegItems> nonVegItems = new ArrayList<NonVegItems>();
		try {
			LOGGER.info("Reading non veg items details for diet " + dietName + " using dao class object.");
			nonVegItems = nonVegItemsDao.readNonVegItems(dietName);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error while calling readNonVegItems method of dao class.Message: " + e.getMessage());
			e.printStackTrace();
		}
		return nonVegItems;
	}

	@Override
	public int updateNonVegItems(NonVegItems nonVegItems) throws ClassNotFoundException, SQLException {
		int rowsUpdateCount = 0;
		try {
			LOGGER.info("Updating non veg items details for item number " + nonVegItems.getItemNum()
					+ " using dao class object.");
			rowsUpdateCount = nonVegItemsDao.updateNonVegItems(nonVegItems);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error while calling updateNonVegItems method of dao class.Message: " + e.getMessage());
			e.printStackTrace();
		}
		return rowsUpdateCount;
	}

	@Override
	public int deleteNonVegItems(int itemNum) throws ClassNotFoundException, SQLException {
		int rowsUpdateCount = 0;
		try {
			LOGGER.info("Deleting non veg items details for item number " + itemNum + " using dao class object.");
			rowsUpdateCount = nonVegItemsDao.deleteNonVegItems(itemNum);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error while calling deleteNonVegItems method of dao class.Message: " + e.getMessage());
			e.printStackTrace();
		}
		return rowsUpdateCount;
	}

	@Override
	public int deleteNonVegItemsByCount(int counter, String dietName) throws ClassNotFoundException, SQLException {
		int rowsUpdateCount = 0;
		try {
			LOGGER.info("Deleting " + counter + " non veg items by calling dao class method.");
			rowsUpdateCount = nonVegItemsDao.deleteNonVegItemsByCount(counter, dietName);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error while calling deleteNonVegItemsByCount method of dao class.Message: " + e.getMessage());
			e.printStackTrace();
		}
		return rowsUpdateCount;
	}
}
