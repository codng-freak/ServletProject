package com.project.bean;

import java.util.ArrayList;

/**
 * SignUpBean contains information about the user that creates a diet plan. It includes it's basic information and 
 * the credentials for each user that are required to create a diet plan. This class has a one-to-many relationship with
 * DietPlan class. Thus every user can have as many DietPlan as he wants.
 * @author Harsh Sharma
 *
 */
public class SignUpBean {
	
	/**
	 * A String for the first name of the user.
	 */
    private String firstName;
    
    /**
     * A String for the last name of the user.
     */
    private String lastName;
    
    /**
     * A String for the unique user name.
     */
    private String userName;
    
    /**
     * A String for the user's password.
     */
    private String password;
    
    /**
     * A String for user's city of residence.
     */
    private String city;
    
    /**
     * An ArrayList for DietPlans.
     */
    private ArrayList<DietPlan> diet = new ArrayList<DietPlan>();
    
    /**
     * A default constructor that displays a message.
     */
    public SignUpBean() {
    	System.out.println("User created");
    }
    
    /**
     * A parameterized constructor that initializes 3 parameters.
     * @param firstName A String that holds user's first name.
     * @param lastName A String that holds user's last name.
     * @param city A String that holds user's city of residence.
     */
    public SignUpBean(String firstName, String lastName, String city) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
	}

    /**
     * Returns user's first name.
     * @return String First Name of user.
     */
	public String getFirstName() {
        return this.firstName;
    }

	/**
	 * Method to set user's first name.
	 * @param firstName Holds user's first name.
	 */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns user's last name.
     * @return String Last Name of user.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Method to set user's last name.
     * @param lastName Holds user's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns user's unique userName.
     * @return String User Name of the user.
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Method to set user's unique userName.
     * @param userName Holds user name of the user.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns user's password.
     * @return String Password of the user.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Method to set user's password.
     * @param password Holds the user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns user's city of residence.
     * @return String user's city name.
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Method to set the city of residence of the user.
     * @param city Holds user's city of residence.
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * Returns the list of user's diet plans.
     * @return ArrayList List of user's diet plans.
     */
    public ArrayList<DietPlan> getDiet() {
    	return this.diet;
    }
    
    /**
     * Method to add a diet plan to the list of diet plans of the user.
     * @param dietPlan Holds the list of all diet plans a particular user has.
     */
    public void addDietList(DietPlan dietPlan) {
    	diet.add(dietPlan);
    }
}
