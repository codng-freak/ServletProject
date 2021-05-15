package com.project.service;

import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.project.bean.VegItems;
import com.project.dao.VegItemsDao;
import com.project.dao.VegItemsDaoImpl;

/**
 * This class implements the VegItemsService interface and defines all it's methods. The methods are for inserting, retrieving, modifying
 * and deleting VegItems from the database for a particular diet plan by calling the respective DAO layer methods.
 * @author Harsh Sharma
 *
 */
public class VegItemsServiceImpl implements VegItemsService {

	/**
	 *  Object of FruitItemsDao class.
	 */
	private VegItemsDao vegItemsDao = new VegItemsDaoImpl();
	
	/**
	 * A final Logger variable that is initialized for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(VegItemsServiceImpl.class);
	
	@Override
	public int insertVegItems(VegItems vegItems) throws ClassNotFoundException, SQLException{
		int rowsUpdateCount = 0;
		try {
			LOGGER.info("Inserting veg item details using dao class object.");
			rowsUpdateCount = vegItemsDao.insertVegItems(vegItems);
		}catch (ClassNotFoundException|SQLException e) {
			LOGGER.error("Error generated while calling dao class method. Message: "+e.getMessage());
			e.printStackTrace();
		}
		return rowsUpdateCount;
	}
	
	@Override
	public ArrayList<VegItems> readVegItems(String dietName) throws ClassNotFoundException, SQLException{
		ArrayList<VegItems> vegItems = new ArrayList<VegItems>();
		try {
			LOGGER.info("Reading veg items details for diet "+dietName+" using dao class object.");
			vegItems = vegItemsDao.readVegItems(dietName);
		}catch (ClassNotFoundException|SQLException e) {
			LOGGER.error("Error while calling readVegItems method of dao class.Message: "+e.getMessage());
			e.printStackTrace();
		}
		return vegItems;
	}
	
	@Override
	public int updateVegItems(VegItems vegItems) throws ClassNotFoundException,SQLException{
		int rowsUpdateCount = 0;
		try {
			LOGGER.info("Updating veg items details for item number "+vegItems.getItemNum()+" using dao class object.");
			rowsUpdateCount = vegItemsDao.updateVegItems(vegItems);
		}catch (ClassNotFoundException|SQLException e) {
			LOGGER.error("Error while calling updateVegItems method of dao class.Message: "+e.getMessage());
			e.printStackTrace();
		}
		return rowsUpdateCount;
	}
	
	@Override
	public int deleteVegItems(int itemNum) throws ClassNotFoundException,SQLException{
		int rowsUpdateCount = 0;
		try {
			LOGGER.info("Deleting veg items details for item number "+itemNum+" using dao class object.");
			rowsUpdateCount = vegItemsDao.deleteVegItems(itemNum);
		}catch (ClassNotFoundException|SQLException e) {
			LOGGER.error("Error while calling deleteVegItems method of dao class.Message: "+e.getMessage());
			e.printStackTrace();
		}
		return rowsUpdateCount;
	}
	
	@Override
	public int deleteVegItemsByCount(int counter, String dietName) throws ClassNotFoundException,SQLException{
		int rowsUpdateCount = 0;
		try {
			LOGGER.info("Deleting "+counter+" veg items by calling dao class method.");
			rowsUpdateCount = vegItemsDao.deleteVegItemsByCount(counter, dietName);
		}catch (ClassNotFoundException|SQLException e) {
			LOGGER.error("Error while calling deleteVegItemsByCount method of dao class.Message: "+e.getMessage());
			e.printStackTrace();
		}
		return rowsUpdateCount;
	}
}
