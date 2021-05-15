package com.project.service;

import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.project.bean.SignUpBean;
import com.project.dao.SignUpDao;
import com.project.dao.SignUpDaoImpl;

/**
 * This class implements the SignUpService interface and defines all it's methods. The methods are for inserting, retrieving, validating
 * user details from the database by calling the respective DAO layer methods.
 * @author Harsh Sharma
 *
 */
public class SignUpServiceImpl implements SignUpService {

	/**
	 *  Object of FruitItemsDao class.
	 */
	private SignUpDao signUpDao = new SignUpDaoImpl();
	
	/**
	 * A final Logger variable that is initialized for this class.
	 */
	static final Logger LOGGER = Logger.getLogger(SignUpServiceImpl.class);

    @Override
    public int insertUser(SignUpBean signUpBean) throws ClassNotFoundException, SQLException{

        int rowsUpdateCount = 0;
        try{
        	LOGGER.info("Inserting user by calling signUpDao method");
            rowsUpdateCount = signUpDao.insertUser(signUpBean);
        }catch(ClassNotFoundException|SQLException e)
        {
        	LOGGER.error("Error while calling dao method. Message: "+e.getMessage());
            throw e;
        }
        return rowsUpdateCount;
    }
    
    @Override
    public boolean loginUser(String userName, String password) throws ClassNotFoundException, SQLException{
    	boolean access = false;
    	try {
    		LOGGER.info("Logging user in by calling signUpDao method");
    		access = signUpDao.loginUser(userName, password);
    	}catch(ClassNotFoundException|SQLException e)
    	{
    		LOGGER.error("Error generated while calling Dao method. Message: "+e.getMessage());
    		e.printStackTrace();
    	}
    	return access;
    }

	@Override
	public SignUpBean readUserDetails(String userName) throws ClassNotFoundException, SQLException {
		LOGGER.info("User details are read by calling dao method.");
		SignUpBean signUpBean = signUpDao.readUserDetails(userName);
		return signUpBean;
	}
}
