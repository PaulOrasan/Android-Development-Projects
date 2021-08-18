package com.example.restaurant_organiser.repository;

public class RepositoryException extends Exception {
    public static final String openFileMessage = "Cannot open database file!";
    public RepositoryException(String message) {
        super(message);
    }
}
