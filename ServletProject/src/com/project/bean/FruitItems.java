package com.project.bean;

/**
 * FruitItems represent the details of a fruit item for a corresponding diet plan. Since the diet plan has a one-to-many
 * relationship with a fruit item thus we can create as many number of fruit items as we want.
 * FruitItems has various details about the fruit item along with the total calories for a particular amount of respective fruit item amount.
 * @author Harsh Sharma
 *
 */
public class FruitItems {
	
	/**
	 * An integer for the fruit item number.
	 */
	private int itemNum;
	
	/**
	 * A String for the name of the fruit item.
	 */
	private String itemName;
	
	/**
	 * A double for the amount of fruit item.
	 */
	private double amount;
	
	/**
	 * A double for the calories per 100 gram of the fruit item.
	 */
	private double caloriesPer100Grams;
	
	/**
	 * A String for the diet name whose fruit item is being created.
	 */
	private String dietName;
	
	/**
	 * A double for the total calories for the amount of fruit item in the diet plan.
	 */
	private double totalCalories;
	
	/**
	 * A parameterized constructor that takes in 5 parameters and initializes them.
	 * @param itemNum An integer that holds the fruit item number.
	 * @param itemName A String that holds the fruit item name.
	 * @param amount A double that holds the amount of fruit item to consume.
	 * @param caloriesPer100Grams A double that holds the calories per 100 grams of the fruit item.
	 * @param totalCalories A double that holds the total calories of the fruit item for the provided amount.
	 */
	public FruitItems(int itemNum, String itemName, double amount, double caloriesPer100Grams, double totalCalories) {
		super();
		this.itemNum = itemNum;
		this.itemName = itemName;
		this.amount = amount;
		this.caloriesPer100Grams = caloriesPer100Grams;
		this.totalCalories = totalCalories;
	}
	
	/**
	 * A Default constructor which prints a message.
	 */
	public FruitItems() {
		System.out.println("Fruit Item is created");
	}
	
	/**
	 * Returns the fruit item number.
	 * @return int fruit item number.
	 */
	public int getItemNum() {
		return itemNum;
	}
	
	/**
	 * Method to set the fruit item number.
	 * @param itemNum Holds the fruit item number.
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
	 * Returns the name of the fruit item.
	 * @return String Fruit Item name.
	 */
	public String getItemName() {
		return itemName;
	}
	
	/**
	 * Method to set the fruit item name.
	 * @param itemName Fruit item name.
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	/**
	 * Return the amount of fruit item to be consumed in the diet plan.
	 * @return double amount of fruit item.
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Method to set the amount of fruit item to be consumed in the diet plan.
	 * @param amount Amount of fruit item.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	/**
	 * Returns the calories per 100 grams of the fruit item to be consumed.
	 * @return double calories per 100 grams.
	 */
	public double getCaloriesPer100Grams() {
		return caloriesPer100Grams;
	}
	
	/**
	 * Method to set the calories per 100 grams of the fruit item to be consumed.
	 * @param caloriesPer100Grams Calories per 100 grams.
	 */
	public void setCaloriesPer100Grams(double caloriesPer100Grams) {
		this.caloriesPer100Grams = caloriesPer100Grams;
	}
	
	/**
	 * Returns the total calories of the fruit item for the corresponding amount to be consumed.
	 * @return double total calories of fruit item.
	 */
	public double getTotalCalories() {
		totalCalories = this.amount * (this.caloriesPer100Grams / 100);
		return totalCalories;
	}
	
}
