package com.example.restaurant_organiser.repository;

import com.example.restaurant_organiser.domain.Meal;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.net.URL;
import java.nio.Buffer;

import static org.junit.Assert.*;

public class RepositoryUnitTest {
    Repository testRepository;
    private final String TEST_FILENAME = "test.txt";
    private final String BACKUP = "backup.txt";
    @Before
    public void setUp() throws Exception {
        testRepository = new Repository(TEST_FILENAME);
    }

    @After
    public void tearDown() throws Exception {
        // Restore test file
        InputStream is = getClass().getClassLoader().getResourceAsStream(BACKUP);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        URL resource = getClass().getClassLoader().getResource(TEST_FILENAME);
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
        final int ID = 4;
        final String name = "Watermelon", category = "Dessert", description = "Fresh and watery";
        final double price = 10.25;
        try {
            testRepository.addMeal(new Meal(ID, name, category, description, price));
            assertEquals(testRepository.size(), 4);
        }catch (RepositoryException e){
            fail();
        }
    }

    @Test
    public void deleteMeal() {
        try{
            testRepository.deleteMeal(new Meal(4));
            assertEquals(testRepository.size(), 3);
            testRepository.deleteMeal(new Meal(1));
            assertEquals(testRepository.size(), 2);
        }catch (RepositoryException e){
            fail();
        }
    }

    @Test
    public void findMeal() {
        try {
            assertEquals(new Meal(1), testRepository.findMeal(1));
        }catch(RepositoryException e){
            fail();
        }
    }
}