package com.example.restaurant_organiser.domain;

/**
Domain class Meal which describes a meal product
 */
public class Meal {

    private int ID;
    private String name, category, description;
    private double price;

    /**
     * Public constructor for class Meal
     * @param id - integer which represents the id of the Meal object
     * @param name - String which represents the name of the Meal object
     * @param category - String which represents the category of the Meal object
     * @param description - String which represents the description of the Meal object
     * @param price - double which represents the price of the Meal object
     */
    public Meal(int id, String name, String category, String description, double price){
        this.ID = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
    }

    /**
     * Getter method that finds the ID of the meal
     * @return an integer value which represents the ID of the meal
     */
    public int getID() {
        return ID;
    }

    /**
     * Setter method that modifies the ID of a meal
     * @param ID - an integer which represents the new ID of the meal
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Getter method that finds the name of the meal
     * @return a String value which represents the name of the meal
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method that modifies the name of a meal
     * @param name - a String which represents the new name of the meal
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter method that finds the category of the meal
     * @return a String value which represents the category of the meal
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter method that modifies the category of a meal
     * @param category - a String which represents the new category of the meal
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Getter method that finds the description of the meal
     * @return a String value which represents the description of the meal
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter method that modifies the description of a meal
     * @param description - a String which represents the new description of the meal
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter method that finds the price of the meal
     * @return a double value which represents the price of the meal
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter method that modifies the price of a meal
     * @param price - a double which represents the new price of the meal
     */
    public void setPrice(double price) {
        this.price = price;
    }

}
