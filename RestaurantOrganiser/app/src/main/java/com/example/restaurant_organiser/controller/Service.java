package com.example.restaurant_organiser.controller;

import com.example.restaurant_organiser.domain.Meal;
import com.example.restaurant_organiser.repository.*;

import java.util.List;

public class Service {
    private String filename;
    private Repository repository;

    /**
     * Constructor for class Service
     * @param file - String which describe the name of the file used by the repository
     * @throws ServiceException - if the file cannot be opened
     */
    public Service(String file) throws ServiceException {
        try {
            filename = file;
            repository = new Repository(filename);
        }catch(RepositoryException e){
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Method that adds a new Meal to the repository with specific attributes
     * @param name - String which describes the name of the new Meal
     * @param category - String which describes the category of the new Meal
     * @param description - String which describes the description of the new Meal
     * @param price - double value which describes the price of the new Meal
     * @throws ServiceException - if the file cannot be opened
     */
    public void addMeal(String name, String category, String description, double price) throws ServiceException {
        try{
            Meal newMeal = new Meal(repository.size() + 1, name, category, description, price);
            repository.addMeal(newMeal);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Method that deletes a Meal from the Repository based on its ID
     * @param ID - integer which represents the ID of the Meal we need to delete
     * @throws ServiceException - if the file cannot be opened or there is no Meal with that ID
     */
    public void deleteMeal(int ID) throws ServiceException {
        try{
            Meal meal = repository.findMeal(ID);
            repository.deleteMeal(meal);
        }catch(RepositoryException e){
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Method that finds a Meal based on ID
     * @param ID - integer which describes the ID of the Meal we are searching for
     * @return an object of type Meal which represents the clone of the object stored in repository
     *         whose ID coincides with the ID searched
     * @throws ServiceException - if the file cannot be opened or no Meal with that ID exists
     */
    public Meal findMeal(int ID) throws ServiceException{
        try{
            return repository.findMeal(ID);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Method that finds the number of elements stored
     * @return an integer which describes the number of Meals stored
     */
    public int size(){
        return repository.size();
    }

    /**
     * Method that finds all the Meals stored
     * @return - a list of copies of all Meal objects
     */
    public List<Meal> getAll(){
        return repository.getAll();
    }
}
