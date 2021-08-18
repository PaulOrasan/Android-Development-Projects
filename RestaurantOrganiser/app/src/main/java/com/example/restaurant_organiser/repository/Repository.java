package com.example.restaurant_organiser.repository;
import java.io.*;
import com.example.restaurant_organiser.domain.Meal;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that describes the Repository which deals with memory management
 * First iteration: storage done using text files, content loaded into memory
 */
public class Repository {
    private String filename;
    private Map<Meal, Meal> data = new HashMap<Meal, Meal>(); // could have used Map<Integer, Meal> given that
                                                              // given that the hashcode is an integer
                                                              // but this way prevents against future changes

    private void loadFromFile() throws RepositoryException {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
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
    }

    private void storeToFile() throws RepositoryException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
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

    public Repository(String file) throws RepositoryException {
        filename = file;
        loadFromFile();
    }

    public void addMeal(Meal m) throws RepositoryException {
        data.put(m, m);
        storeToFile();
    }

    public void deleteMeal(Meal m) throws RepositoryException {
        data.remove(m);
        storeToFile();
    }

    public Meal findMeal(int ID){
        return data.get(new Meal(ID));
    }
}

