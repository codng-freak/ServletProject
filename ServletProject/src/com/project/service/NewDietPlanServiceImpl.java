package com.project.service;

import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.project.bean.DietPlan;
import com.project.dao.NewDietPlanDao;
import com.project.dao.NewDietPlanDaoImpl;

/**
 * This class implements the NewDietPlanService interface and defines all it's methods. The methods are for inserting, retrieving, modifying
 * and deleting DietPlan from the database for a particular user by calling the respective DAO layer methods.
 * @author Harsh Sharma
 *
 */
public class NewDietPlanServiceImpl implements NewDietPlanService {
	
	/**
	 *  Object of FruitItemsDao class.
	 */
	private NewDietPlanDao newDietPlanDao = new NewDietPlanDaoImpl();
	
	/**
	 * A final Logger variable that is initialized for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(NewDietPlanServiceImpl.class);

	@Override
	public int insertDietDetails(DietPlan diet) throws ClassNotFoundException, SQLException {
		int rowsUpdateCount = 0;
		try{
			LOGGER.info("Inserting Diet details using Dao class object.");
            rowsUpdateCount = newDietPlanDao.insertDietDetails(diet);
        }catch(ClassNotFoundException|SQLException e)
        {
        	LOGGER.error("Error generated while calling dao class method. Message: "+e.getMessage());
            throw e;
        }
        return rowsUpdateCount;
	}
	
	@Override
	public ArrayList<DietPlan> readDietDetails(String userName) throws ClassNotFoundException, SQLException{
		ArrayList<DietPlan> dietPlans = new ArrayList<DietPlan>();
		try {
			LOGGER.info("Diet details for "+userName+" are read using dao class object");
			dietPlans = newDietPlanDao.readDietDetails(userName);
		}catch (ClassNotFoundException|SQLException e) {
			LOGGER.error("Error generated while calling readDietDetails method of dao class.");
			e.printStackTrace();
		}
		return dietPlans;
	}
	
	@Override
	public int updateDietDetails(DietPlan diet) throws ClassNotFoundException, SQLException{
		int rowsUpdateCount = 0;
		try {
			LOGGER.info("Diet Details for diet number "+diet.getDietNum()+" are updated using dao object.");
			rowsUpdateCount = newDietPlanDao.updateDietDetails(diet);
		}catch (ClassNotFoundException|SQLException e) {
			LOGGER.error("Error generated while calling updateDietDetails method of dao class.");
			e.printStackTrace();
		}
		return rowsUpdateCount;
	}
	
	@Override
	public int deleteDietPlan(int dietNum) throws ClassNotFoundException,SQLException{
		int rowsUpdateCount = 0;
		try {
			LOGGER.info("Diet Details for diet number "+dietNum+" are deleted using dao object.");
			rowsUpdateCount = newDietPlanDao.deleteDietPlan(dietNum);
		}catch (ClassNotFoundException|SQLException e) {
			LOGGER.error("Error generated while calling deleteDietDetails method of dao class.");
			e.printStackTrace();
		}
		return rowsUpdateCount;
	}
}
	