package com.project.service;

import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.project.bean.FruitItems;
import com.project.dao.FruitItemsDao;
import com.project.dao.FruitItemsDaoImpl;

/**
 * This class implements the FruitItemsService interface and defines all it's methods. The methods are for inserting, retrieving, modifying
 * and deleting FruitItems from the database for a particular diet plan by calling the respective DAO Layer method.
 * @author Harsh Sharma
 *
 */
public class FruitItemsServiceImpl implements FruitItemsService {
	
	/**
	 *  Object of FruitItemsDao class.
	 */
	private FruitItemsDao fruitItemsDao = new FruitItemsDaoImpl();
	
	/**
	 * A final Logger variable that is initialized for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(FruitItemsServiceImpl.class);

	@Override
	public int insertFruitItems(FruitItems fruitItem) throws ClassNotFoundException, SQLException {
		int rowsUpdateCount = 0;
		try {
			LOGGER.info("Inserting fruit item details using dao class object.");
			rowsUpdateCount = fruitItemsDao.insertFruitItem(fruitItem);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error generated while calling dao class method. Message: " + e.getMessage());
			e.printStackTrace();
		}
		return rowsUpdateCount;
	}

	public ArrayList<FruitItems> readFruitItems(String dietName) throws ClassNotFoundException, SQLException {
		ArrayList<FruitItems> fruitItems = new ArrayList<>();
		try {
			LOGGER.info("Reading fruit items details for diet " + dietName + " using dao class object.");
			fruitItems = fruitItemsDao.readFruitItems(dietName);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error while calling readFruitItems method of dao class.Message: " + e.getMessage());
			e.printStackTrace();
		}
		return fruitItems;
	}

	@Override
	public int updateFruitItems(FruitItems fruitItems) throws ClassNotFoundException, SQLException {
		int rowsUpdateCount = 0;
		try {
			LOGGER.info("Updating fruit items details for item number " + fruitItems.getItemNum()
					+ " using dao class object.");
			rowsUpdateCount = fruitItemsDao.updateFruitItems(fruitItems);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error while calling updateFruitItems method of dao class.Message: " + e.getMessage());
			e.printStackTrace();
		}
		return rowsUpdateCount;
	}

	@Override
	public int deleteFruitItems(int itemNum) throws ClassNotFoundException, SQLException {
		int rowsUpdateCount = 0;
		try {
			LOGGER.info("Deleting fruit items details for item number " + itemNum + " using dao class object.");
			rowsUpdateCount = fruitItemsDao.deleteFruitItems(itemNum);
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error("Error while calling deleteFruitItems method of dao class.Message: " + e.getMessage());
			e.printStackTrace();
		}
		return rowsUpdateCount;
	}
	
	@Override
	public int deleteFruitItemsByCount(int counter, String dietName) throws ClassNotFoundException,SQLException{
		int rowsUpdateCount = 0;
		try {
			LOGGER.info("Deleting "+counter+" fruit items by calling dao class method.");
			rowsUpdateCount = fruitItemsDao.deleteFruitItemsByCount(counter, dietName);
		}catch (ClassNotFoundException|SQLException e) {
			LOGGER.error("Error while calling deleteFruitItemsByCount method of dao class.Message: "+e.getMessage());
			e.printStackTrace();
		}
		return rowsUpdateCount;
	}
}
