package com.project.bean;

/**
 * NonVegItems represent the details of a non-vegetarian item for a corresponding diet plan. Since the diet plan has a one-to-many
 * relationship with a non vegetarian item thus we can create as many number of non-vegetarian items as we want.
 * NonVegItems has various details about the non-vegetarian item along with the total calories for a particular amount of respective non-vegetarian item amount.
 * @author Harsh Sharma
 *
 */
public class NonVegItems {
	
	/**
	 * An integer for the non-vegetarian item number.
	 */
	private int itemNum;
	
	/**
	 * A String for the name of the non-vegetarian item.
	 */
	private String itemName;
	
	/**
	 * A double for the amount of non-vegetarian item.
	 */
	private double amount;
	
	/**
	 * A double for the calories per 100 gram of the non-vegetarian item.
	 */
	private double caloriesPer100Grams;
	
	/**
	 * A String for the diet name whose non-vegetarian item is being created.
	 */
	private String dietName;
	
	/**
	 * A double for the total calories for the amount of non-vegetarian item in the diet plan.
	 */
	private double totalCalories;
	
	/**
	 * A default constructor that prints a message.
	 */
	public NonVegItems() {
		System.out.println("Non Veg item created");
	}
	
	/**
	 * A parameterized constructor that takes in 5 parameters and initializes them.
	 * @param itemNum An integer that holds the non-vegetarian item number.
	 * @param itemName A String that holds the non-vegetarian item name.
	 * @param amount A double that holds the amount of non-vegetarian item to consume.
	 * @param caloriesPer100Grams A double that holds the calories per 100 grams of the non-vegetarian item.
	 * @param totalCalories A double that holds the total calories of the non-vegetarian item for the provided amount.
	 */
	public NonVegItems(int itemNum, String itemName, double amount, double caloriesPer100Grams, double totalCalories) {
		super();
		this.itemNum = itemNum;
		this.itemName = itemName;
		this.amount = amount;
		this.caloriesPer100Grams = caloriesPer100Grams;
		this.totalCalories = totalCalories;
	}
	
	/**
	 * Returns the non-vegetarian item number.
	 * @return int non-vegetarian item number.
	 */
	public int getItemNum() {
		return itemNum;
	}
	
	/**
	 * Method to set the non-vegetarian item number.
	 * @param itemNum Holds the non-vegetarian item number.
	 */
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	/**
	 * Returns the name of the diet plan.
	 * @return String name of diet plan.
	 */
	public String getDietName() {
		return dietName;
	}
	
	/**
	 * Method to set the name of the diet plan.
	 * @param dietName Diet Plan name.
	 */
	public void setDietName(String dietName) {
		this.dietName = dietName;
	}
	
	/**
	 * Returns the name of the non-vegetarian item.
	 * @return String non-vegetarian Item name.
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * Method to set the non-vegetarian item name.
	 * @param itemName non-vegetarian item name.
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	/**
	 * Return the amount of non-vegetarian item to be consumed in the diet plan.
	 * @return double amount of non-vegetarian item.
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Method to set the amount of non-vegetarian item to be consumed in the diet plan.
	 * @param amount Amount of non-vegetarian item.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * Returns the calories per 100 grams of the non-vegetarian item to be consumed.
	 * @return double calories per 100 grams.
	 */
	public double getCaloriesPer100Gram() {
		return caloriesPer100Grams;
	}
	
	/**
	 * Method to set the calories per 100 grams of the non-vegetarian item to be consumed.
	 * @param caloriesPer100Gram Calories per 100 grams.
	 */
	public void setCaloriesPer100Gram(double caloriesPer100Gram) {
		this.caloriesPer100Grams = caloriesPer100Gram;
	}
	
	/**
	 * Returns the total calories of the non-vegetarian item for the corresponding amount to be consumed.
	 * @return double total calories of non-vegetarian item.
	 */
	public double getTotalCalories() {
		totalCalories = this.amount * (this.caloriesPer100Grams / 100);
		return totalCalories;
	}
}
