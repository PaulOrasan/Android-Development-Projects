package com.example.restaurant_organiser.repository;

public class RepositoryException extends Exception {
    public static final String openFileMessage = "Cannot open database file!";
    public static final String findMealMessage = "Cannot find a Meal with that ID!";
    public RepositoryException(String message) {
        super(message);
    }
}
