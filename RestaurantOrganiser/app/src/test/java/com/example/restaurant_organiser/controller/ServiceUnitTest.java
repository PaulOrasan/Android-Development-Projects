package com.example.restaurant_organiser.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import java.io.*;
import java.net.URL;
import com.example.restaurant_organiser.domain.Meal;

public class ServiceUnitTest {

    private Service testService;
    @Before
    public void setUp() throws Exception {
        testService = new Service("test.txt");
    }

    @After
    public void tearDown() throws Exception {
        // smelly code to achieve same file usage to simulate a database for learning purposes
        InputStream is = getClass().getClassLoader().getResourceAsStream("backup.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        URL resource = getClass().getClassLoader().getResource("test.txt");
        String path = resource.getPath();
        OutputStream os = new FileOutputStream(path);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        while (reader.ready()){
            writer.write(reader.readLine() + "\n");
        }
        reader.close();
        writer.close();
    }

    @Test
    public void addMeal() {
        final String name = "Watermelon", category = "Dessert", description = "Fresh and watery";
        final double price = 10.25;
        try {
            testService.addMeal(name, category, description, price);
            assertEquals(testService.size(), 4);
        }catch (ServiceException e){
            fail();
        }
    }

    @Test
    public void deleteMeal() {
        try{
            testService.deleteMeal(1);
            assertEquals(testService.size(), 2);
        }catch (ServiceException e){
            fail();
        }
    }

    @Test
    public void findMeal() {
        try{
            assertEquals(testService.findMeal(1), new Meal(1));
        }catch (ServiceException e){
            fail();
        }
    }

    @Test
    public void getAll() {
        assertEquals(testService.getAll().size(), 3);
    }
}