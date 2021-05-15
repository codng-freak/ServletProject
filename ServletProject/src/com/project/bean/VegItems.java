package com.project.bean;

/**
 * VegItems represent the details of a vegetarian item for a corresponding diet plan. Since the diet plan has a one-to-many
 * relationship with a vegetarian item thus we can create as many number of vegetarian items as we want.
 * VegItems has various details about the vegetarian item along with the total calories for a particular amount of respective vegetarian item amount.
 * @author Harsh Sharma
 *
 */
public class VegItems {
	
	/**
	 * A string for the type of vegetarian item.
	 */
	private String itemType;
	
	/**
	 * An integer for the vegetarian item number.
	 */
	private int itemNum;
	
	/**
	 * A String for the name of the vegetarian item.
	 */
	private String itemName;
	
	/**
	 * A double for the amount of vegetarian item.
	 */
	private double amount;
	
	/**
	 * A double for the calories per 100 gram of the vegetarian item.
	 */
	private double caloriesPer100Grams;
	
	/**
	 * A String for the diet name whose vegetarian item is being created.
	 */
	private String dietName;
	
	/**
	 * A double for the total calories for the amount of vegetarian item in the diet plan.
	 */
	private double totalCalories;
	

	/**
	 * A default constructor that displays a message.
	 */
	public VegItems() {
		System.out.println("Veg item is created");
	}

	/**
	 * A parameterized constructor that takes in 6 parameters and initializes them.
	 * @param itemNum An integer that holds the vegetarian item number.
	 * @param itemName A String that holds the vegetarian item name.
	 * @param itemType A String that holds the vegetarian item-type.
	 * @param amount A double that holds the amount of vegetarian item to consume.
	 * @param caloriesPer100Grams A double that holds the calories per 100 grams of the vegetarian item.
	 * @param totalCalories A double that holds the total calories of the vegetarian item for the provided amount.
	 */
	public VegItems(int itemNum, String itemName, String itemType, double amount, double caloriesPer100Grams, double totalCalories) {
		super();
		this.itemNum = itemNum;
		this.itemName = itemName;
		this.itemType = itemType;
		this.amount = amount;
		this.caloriesPer100Grams = caloriesPer100Grams;
		this.totalCalories = totalCalories;
	}

	
	/**
	 * Returns the vegetarian item number.
	 * @return int vegetarian item number.
	 */
	public int getItemNum() {
		return itemNum;
	}
	
	/**
	 * Method to set the vegetarian item number.
	 * @param itemNum Holds the vegetarian item number.
	 */
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}

	/**
	 * Returns the calories per 100 grams of the vegetarian item to be consumed.
	 * @return double calories per 100 grams.
	 */
	public double getCaloriesPer100Grams() {
		return caloriesPer100Grams;
	}
	
	/**
	 * Method to set the calories per 100 grams of the vegetarian item to be consumed.
	 * @param caloriesPer100Grams Calories per 100 grams.
	 */
	public void setCaloriesPer100Grams(double caloriesPer100Grams) {
		this.caloriesPer100Grams = caloriesPer100Grams;
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
	 * Returns the name of the vegetarian item.
	 * @return String vegetarian Item name.
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * Method to set the vegetarian item name.
	 * @param itemName vegetarian item name.
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	/**
	 * Returns the vegetarian item type.
	 * @return String vegetarian item type.
	 */
	public String getItemType() {
		return itemType;
	}
	
	/**
	 * Method to set the vegetarian item type.
	 * @param itemType Vegetarian item type.
	 */
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	
	/**
	 * Return the amount of vegetarian item to be consumed in the diet plan.
	 * @return double amount of vegetarian item.
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Method to set the amount of vegetarian item to be consumed in the diet plan.
	 * @param amount Amount of vegetarian item.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * Returns the total calories of the vegetarian item for the corresponding amount to be consumed.
	 * @return double total calories of vegetarian item.
	 */
	public double getTotalCalories() {
		totalCalories = this.amount * (this.caloriesPer100Grams / 100);
		return totalCalories;
	}
}
