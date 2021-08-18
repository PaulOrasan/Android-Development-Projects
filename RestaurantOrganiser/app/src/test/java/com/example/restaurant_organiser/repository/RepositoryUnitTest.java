package com.example.restaurant_organiser.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
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
        BufferedReader reader = new BufferedReader(new FileReader(BACKUP));
        BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILENAME));
        while (reader.ready()){
            writer.write(reader.readLine() + "\n");
        }
    }

    @Test
    public void addMeal() {
        final int ID = 4;
        final String name = "Watermelon", category = "Dessert", description = "Fresh and watery";
        final double price = 10.25;
        testRepository.addMeal()
    }

    @Test
    public void deleteMeal() {
    }

    @Test
    public void findMeal() {
    }
}