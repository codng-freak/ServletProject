package com.jdbc.code;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

/**
 * This class is for setting up the database connection through JDBC driver. It connects to the mysql database
 * whose details are provided in the variables(url, userName, password). If the credentials are valid the database connection is created.
 * @author Harsh Sharma
 *
 */
public class SignUpUtility {

	/**
	 * A static final variable which holds the mysql connection url.
	 */
    public static final String url = "jdbc:mysql://localhost:3306/project";
    /**
     * A static final variable which holds the database user's name.
     */
    public static final String userName = "root";
    /**
     * A static final variable which holds the database user's password.
     */
    public static final String password = "13101998Harsh";
    /**
     * A static object of Connection set to null.
     */
    public static Connection connection = null;
    /**
     * A logger variable which is initialized for the class.
     */
    static final Logger LOGGER = Logger.getLogger(SignUpUtility.class); // Initializing a logger for this class.

    /**
     * This method return a Connection object and establishes a connection with the database through the provided credentials in the final parameters
     * url, userName, password.
     * @return Connection object
     * @throws ClassNotFoundException if the class does not exists.
     * @throws SQLException if there is a mistake in sql statement and commands or any database errors.
     */
    public static Connection getDbConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver Registered");
        connection = DriverManager.getConnection(url, userName, password);
        System.out.println("Connection Established");
        // To check JDBC version
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        System.out.println("JDBC version "+ databaseMetaData.getJDBCMajorVersion());
        LOGGER.info("Database connection created successfully.");

        return connection;
    }

    /**
     * This method closes the database connection for the user.
     * @throws SQLException if there is a mistake in sql statement and commands or any database errors.
     */
    public static void getDbConnectionClose() throws SQLException{
        if(connection != null){
        	LOGGER.warn("Database connection closed.");
            connection.close();
        }
    }
}
