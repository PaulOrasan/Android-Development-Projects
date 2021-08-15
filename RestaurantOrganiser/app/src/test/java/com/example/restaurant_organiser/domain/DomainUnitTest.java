package com.example.restaurant_organiser.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DomainUnitTest {

    //Test details
    private final int testID = 1;
    private final String testName = "Omelette";
    private final String testDescription = "Very tasty";
    private final String testCategory = "Breakfast";
    private final double testPrice = 12.25;
    private Meal testMeal;

    @Before
    public void setUp() throws Exception {
        testMeal = new Meal(testID, testName, testCategory, testDescription, testPrice);
    }

    @Test
    public void getID() {
        assertEquals(testMeal.getID(), testID);
    }

    @Test
    public void setID() {
        int newID = testID + 1;
        testMeal.setID(newID);
        assertEquals(testMeal.getID(), newID);
    }

    @Test
    public void getName() {
        assertEquals(testMeal.getName(), testName);
    }

    @Test
    public void setName() {
        String newName = "testName";
        testMeal.setName(newName);
        assertEquals(testMeal.getName(), newName);
    }

    @Test
    public void getCategory() {
        assertEquals(testMeal.getCategory(), testCategory);
    }

    @Test
    public void setCategory() {
        String newCategory = "testCategory";
        testMeal.setCategory(newCategory);
        assertEquals(testMeal.getCategory(), newCategory);
    }

    @Test
    public void getDescription() {
        assertEquals(testMeal.getDescription(), testDescription);
    }

    @Test
    public void setDescription() {
        String newDescription = "testDescription";
        testMeal.setDescription(newDescription);
        assertEquals(testMeal.getDescription(), newDescription);
    }

    @Test
    public void getPrice() {
        final double delta = 0.00000001;
        assertEquals(testMeal.getPrice(), testPrice, delta);
    }

    @Test
    public void setPrice() {
        double newPrice = testPrice + 1;
        testMeal.setPrice(newPrice);
        final double delta = 0.00000001;
        assertEquals(testMeal.getPrice(), newPrice, delta);
    }
}