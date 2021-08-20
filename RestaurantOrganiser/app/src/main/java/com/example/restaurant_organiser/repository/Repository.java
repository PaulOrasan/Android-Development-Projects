package com.example.restaurant_organiser.repository;

import java.io.*;
import com.example.restaurant_organiser.domain.Meal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that describes the Repository which deals with memory management
 * First iteration: storage done using text files, content loaded into memory
 */
public class Repository {
    private String filename = "";
    private Map<Meal, Meal> data = new HashMap<Meal, Meal>(); // could have used Map<Integer, Meal> given that
                                                              // given that the hashcode is an integer
                                                              // but this way prevents against future changes

    /**
     * Method that loads data from the specified file which must be in the assets folder of the app
     * @throws RepositoryException if the file cannot be opened
     */
    private void loadFromFile() throws RepositoryException {
        try{
            InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            while (reader.ready()){
                String id = reader.readLine();
                String name = reader.readLine();
                String category = reader.readLine();
                String description = reader.readLine();
                String price = reader.readLine();
                Meal meal = new Meal(Integer.parseInt(id), name, category, description, Double.parseDouble(price));
                data.put(meal, meal);
            }
            reader.close();
        }catch (IOException e){
            throw new RepositoryException(RepositoryException.openFileMessage);
        }
        catch (java.lang.NullPointerException e){
            throw new RepositoryException(RepositoryException.openFileMessage);
        }
    }

    /**
     * Method to store current data in the specified file
     * @throws RepositoryException if the file cannot be opened
     */
    private void storeToFile() throws RepositoryException {
        try {
            URL resource = getClass().getClassLoader().getResource(filename);   // smelly code to force the app to write
            String path = resource.getPath();                                   // to resource file to simulate database
            OutputStream os = new FileOutputStream(path);                       // learning purpose only
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
            for (Meal m : data.values()){
                writer.write(String.valueOf(m.getID()) + "\n");
                writer.write(m.getName() + "\n");
                writer.write(m.getCategory() + "\n");
                writer.write(m.getDescription() + "\n");
                writer.write(String.valueOf(m.getPrice()) + "\n");
            }
            writer.close();
        }catch(IOException e){
            throw new RepositoryException(RepositoryException.openFileMessage);
        }
    }

    /**
     * Constructor for the class Repository
     * @param file - String which describes the name of the file that will serve as a database
     * @throws RepositoryException if the file cannot be opened
     */
    public Repository(String file) throws RepositoryException {
        filename = file;
        if (filename != "")
            loadFromFile();
    }

    /**
     * Method that adds a new meal to the repository
     * @param m - Object of type Meal that will be added to the collection
     * @throws RepositoryException if the file cannot be opened
     */
    public void addMeal(Meal m) throws RepositoryException {
        data.put(m, m);
        if (filename != "")
            storeToFile();
    }

    /**
     * Method that deletes a Meal from the repository
     * @param m - Object of type Meal that will be removed from the collection
     * @throws RepositoryException if the file cannot be opened
     */
    public void deleteMeal(Meal m) throws RepositoryException {
        data.remove(m);
        if (filename != "")
            storeToFile();
    }

    /**
     * Method that finds a meal based on ID
     * @param ID - integer which describes the ID of the Meal searched
     * @return an object of type Meal whose ID must coincide with ID
     * @throws RepositoryException if the file cannot be opened
     */
    public Meal findMeal(int ID) throws RepositoryException{
        if (data.containsKey(new Meal(ID)))
            return data.get(new Meal(ID)).clone();
        else
            throw new RepositoryException(RepositoryException.findMealMessage);
    }

    /**
     * Method that finds the number of elements stored in the repository
     * @return an integer which represents the size of the repository
     */
    public int size(){
        return data.size();
    }

    /**
     * Method that finds all the Meals stored in the repository
     * @return an ArrayList object consisting of all Meals
     */
    public List<Meal> getAll(){
        return Collections.unmodifiableList(new ArrayList<>(data.values()));
    }

}

