package com.project.bean;

import java.util.ArrayList;

/**
 * DietPlan represents a diet plan for a user and contains the various food items he needs to 
 * consume in the time span of the diet.
 * Each DietPlan contains vegetarian, non-Vegetarian and fruit items with whom this class has a one-to-many association.
 * Each of the vegetarian, non-vegetarian and fruit items will exist if the DietPlan for them exists. 
 * @author Harsh Sharma
 *
 */
public class DietPlan {
	/**
	 * An ArrayList of VegItems objects in a DietPlan.
	 */
	private ArrayList<VegItems> vegetarian = new ArrayList<VegItems>();
	/**
	 * An ArrayList of NonVegItems objects in a DietPlan.
	 */
	private ArrayList<NonVegItems> nonVegetarian = new ArrayList<NonVegItems>();
	/**
	 * An ArrayList of FruitItems objects in a DietPlan.
	 */
	private ArrayList<FruitItems> fruits = new ArrayList<FruitItems>();
	/**
	 * A double variable that stores the calculated total calories of diet.
	 */
	private double totalCaloriesOfDiet;
	/**
	 * A String variable that stores the time of diet i.e., Breakfast or Lunch or Dinner.
	 */
	private String timeOfDiet;
	/**
	 * An integer variable that stores the diet duration.
	 */
	private int dietDurationInDays;
	/**
	 * A String variable that stores the diet name.
	 */
	private String dietName;
	/**
	 * An integer variable to store diet number.
	 */
	private int dietNum;
	/**
	 * A String variable to store user name.
	 */
	private String userName;
	
	/**
	 * A default constructor for DietPlan class that prints a simple message.
	 */
	public DietPlan() {
		System.out.println("New Diet plan object created");
	}
	
	/**
	 * A parameterized constructor of DietPlan class that initializes the four parameters.
	 * @param timeOfDiet The time at which the diet should be taken.
	 * @param dietDurationInDays The duration for which the DietPlan is to be taken(in number of days).
	 * @param dietName The name of the DietPlan. 
	 * @param dietNum The number of created DietPlan.
	 */
	public DietPlan(String timeOfDiet, int dietDurationInDays, String dietName, int dietNum) {
		super();
		this.timeOfDiet = timeOfDiet;
		this.dietDurationInDays = dietDurationInDays;
		this.dietName = dietName;
		this.dietNum = dietNum;
	}
	
	/**
	 * Returns user name for the diet plan.
	 * @return String Name of the user.
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Method to set the user name.
	 * @param nameString Holds the user name.
	 */
	public void setUserName(String nameString) {
		this.userName = nameString;
	}
	
	/**
	 * Method to add a VegItem object to the ArrayList of VegItems.
	 * @param vegItems ArrayList of VegItems 
	 */
	public void addVegItems(VegItems vegItems) {
		vegetarian.add(vegItems);
	}
	
	/**
	 * Method to add a NonVegItem object to the ArrayList of NonVegItems.
	 * @param nonVegItems ArrayList of NonVegItems 
	 */
	public void addNonVegItems(NonVegItems nonVegItems) {
		nonVegetarian.add(nonVegItems);
	}
	
	/**
	 * Method to add a FruitItem object to the ArrayList of FruitItems
	 * @param fruitItems ArrayList of FruitItems
	 */
	public void addFruitItems(FruitItems fruitItems) {
		fruits.add(fruitItems);
	}
	
	/**
	 * Returns an integer that is the diet number.
	 * @return int Diet number.
	 */
	public int getDietNum() {
		return dietNum;
	}
	
	/**
	 * Method to set the diet number.
	 * @param dietNum An integer that holds the diet number.
	 */
	public void setDietNum(int dietNum) {
		this.dietNum = dietNum;
	}
	
	/**
	 * Returns the diet name.
	 * @return String Name of the diet.
	 */
	public String getDietName() {
		return dietName;
	}
	
	/**
	 * Method to set diet name.
	 * @param dietName A String that holds name of the diet plan.
	 */
	public void setDietName(String dietName) {
		this.dietName = dietName;
	}
	
	/**
	 * Returns the time of the diet(Breakfast/Lunch/Dinner).
	 * @return String Time of diet.
	 */
	public String getTimeOfDiet() {
		return timeOfDiet;
	}
	
	/**
	 * Method to set the time of diet.
	 * @param timeOfDiet A string that holds the time of diet i.e., Breakfast or Lunch or Dinner.
	 */
	public void setTimeOfDiet(String timeOfDiet) {
		this.timeOfDiet = timeOfDiet;
	}
	
	/**
	 * Returns the diet duration.
	 * @return int Duration of diet in number of days.
	 */
	public int getDietDurationInDays() {
		return dietDurationInDays;
	}
	
	/**
	 * Method to set the diet duration.
	 * @param dietDurationInDays An integer that corresponds to the number of days for which the diet plan is created.
	 */
	public void setDietDurationInDays(int dietDurationInDays) {
		this.dietDurationInDays = dietDurationInDays;
	}
	
	/**
	 * Returns the total calories of the diet that is calculated by adding the total calories of individual vegetarian,
	 * non-vegetarian and fruit items in the diet plan. The method loops through the list of the types of food items and 
	 * adds there total calories to get the final total calories of the diet plan.
	 * @param veg An ArrayList of VegItems objects.
	 * @param nonVeg An ArrayList of NonVegItems objects.
	 * @param fruit An ArrayList of FruitItems objects.
	 * @return double The total calculated calories of the diet plan.
	 */
	public double getTotalCaloriesOfDiet(ArrayList<VegItems> veg, ArrayList<NonVegItems> nonVeg, ArrayList<FruitItems> fruit) {
		double vegTotalCalories = 0;
		double nonVegTotalCalories= 0;
		double fruitsTotalCalories = 0;
		
		for(int i=0;i<veg.size();i++) {
			vegTotalCalories += veg.get(i).getTotalCalories();
		}
		
		for(int i=0;i<nonVeg.size();i++) {
			nonVegTotalCalories += nonVeg.get(i).getTotalCalories();
		}
		
		for(int i=0;i<fruit.size();i++) {
			fruitsTotalCalories += fruit.get(i).getTotalCalories();
		}
		
		totalCaloriesOfDiet = vegTotalCalories + nonVegTotalCalories + fruitsTotalCalories;
		return totalCaloriesOfDiet;
	}
}
