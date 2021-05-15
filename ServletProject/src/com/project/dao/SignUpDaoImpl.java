package com.project.dao;

import java.sql.Statement;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jdbc.code.SignUpUtility;
import com.project.bean.SignUpBean;

/**
 * This class implements the SignUpDao interface and defines all it's methods. The methods are for inserting, retrieving, validating
 * user details from the database. This class takes the help of SignUpUtility class to establish and close
 * the connection with the database to perform the required operations.
 * @author Harsh Sharma
 *
 */
public class SignUpDaoImpl implements SignUpDao{

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
    static final Logger LOGGER = Logger.getLogger(SignUpDao.class);

    @Override
    public int insertUser(SignUpBean signUpBean) throws ClassNotFoundException, SQLException{
        try{
        	LOGGER.info("Inside insertUser method");
            int rowsUpdateCount = 0;
            String firstName = signUpBean.getFirstName();
            String lastName = signUpBean.getLastName();
            String userName = signUpBean.getUserName();
            String password = signUpBean.getPassword();
            String city = signUpBean.getCity();

            String insertData = "insert into signup values"
            + "('" +firstName+"','"+lastName+"','"+userName+"','"+password+"','"+city+"')";

            connection = SignUpUtility.getDbConnection();
            statement = connection.createStatement();
            rowsUpdateCount = statement.executeUpdate(insertData); 
            if(rowsUpdateCount > 0){
            	LOGGER.warn("User successfully created and the details are successfully inserted in the database.");
                System.out.println("Employee Registered successfully");
            }
            else{
                System.out.println("Something went wrong. Insertion unsuccessful");
            }
            
            return rowsUpdateCount;
        }catch(Exception e){
        	LOGGER.error("Error while inserting user details. Process failed. Message: "+e.getMessage());
            throw e;
        }
        finally{
            SignUpUtility.getDbConnectionClose();
        }
    }
    
    @Override
    public boolean loginUser(String userName, String password) throws ClassNotFoundException, SQLException{
    	boolean exist = false;
    	String currentUserName = "";
		String currentPassword = "";
		ResultSet resultSet = null;
    	try {
    		LOGGER.info("Inside loginUser method of Dao class");
    		connection = SignUpUtility.getDbConnection();
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select user_name, password from signup");
    		
    		while(resultSet.next()) {
    			currentUserName = resultSet.getString("user_name");
    			currentPassword = resultSet.getString("password");
    			
    			if(userName.equals(currentUserName) && password.equals(currentPassword)) {
    				LOGGER.warn("User found valid.");
    				exist = true;
    				return exist;
    			}
    		}
    	}catch(ClassNotFoundException|SQLException e)
    	{
    		LOGGER.error("Error while validating user login details. Login failed. Message: "+e.getMessage());
    		e.printStackTrace();
    	}
    	finally{
            SignUpUtility.getDbConnectionClose();
        }
    	return false;
    }
    
    @Override
    public SignUpBean readUserDetails(String userName) throws ClassNotFoundException, SQLException{
    	SignUpBean signUpBean = null;
    	try {
    		LOGGER.info("Inside readUserDetails method of dao class");
    		String retrieveData = "SELECT first_name, last_name, city FROM signup WHERE user_name = ?";
    		connection = SignUpUtility.getDbConnection();
    		preparedStatement = connection.prepareStatement(retrieveData);
    		preparedStatement.setString(1, userName);
    		ResultSet rs = preparedStatement.executeQuery();
    		while(rs.next()) {
    			String firstName = rs.getString("first_name");
    			String lastName = rs.getString("last_name");
    			String city = rs.getString("city");
    			System.out.println(firstName);
    			signUpBean = new SignUpBean(firstName, lastName, city);
    		}
    	}catch(ClassNotFoundException|SQLException e) {
    		LOGGER.error("Error while fetching user details. Message: "+e.getMessage());
    		throw e;
    	}
    	finally {
			SignUpUtility.getDbConnectionClose();
		}
    	LOGGER.warn("User details fetched successfully from database.");
		return signUpBean;
    }
}
