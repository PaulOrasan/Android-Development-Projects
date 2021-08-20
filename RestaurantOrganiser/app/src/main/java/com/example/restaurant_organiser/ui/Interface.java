package com.example.restaurant_organiser.ui;

import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import com.example.restaurant_organiser.controller.*;
import com.example.restaurant_organiser.domain.Meal;

public class Interface {
    private Scanner scanner = new Scanner(System.in);
    HashMap<String, Runnable> options = new HashMap<>(); // map that stores method references
    private boolean running = true;
    private Service service;
    private String filename;

    /**
     * Constructor for interface class
     * @param file
     */
    public Interface(String file){
        options.put("add", this::addMenu);
        options.put("del", this::deleteMenu);
        options.put("find", () -> findMenu());
        options.put("show", this::showMenu);
        options.put("help", this::helpMenu);
        options.put("exit", this::exit);
        filename = file;
        try{
            service = new Service(filename);
        }catch (ServiceException e){
            System.out.println(e.getMessage());
            running = false;
        }
    }

    /**
     * Method that runs the interface
     */
    public void run() {
        if (running) {
            System.out.println("Welcome!\n");
            String option;
            helpMenu();
            do {
                System.out.print(">>> ");
                option = scanner.nextLine();
                if (options.containsKey(option)) {
                    options.get(option).run();
                }else{
                    System.out.println("That is not a valid option!");
                }
            } while (running);
        }
        System.out.println("Running ended");
    }

    /**
     * Method that shows the help menu
     */
    private void helpMenu(){
        System.out.println("Type add to add meal");
        System.out.println("Type del to delete meal");
        System.out.println("Type find to find meal");
        System.out.println("Type show to show menu");
        System.out.println("Type help to show help menu");
        System.out.println("Type exit to exit\n");
    }

    /**
     * Method that controls the add menu (taking input and producing output)
     */
    private void addMenu() {
        String name, category, description;
        double price;
        System.out.print("\nInput meal name: ");
        name = scanner.nextLine();
        System.out.print("Input meal category: ");
        category = scanner.nextLine();
        System.out.print("Input meal description: ");
        description = scanner.nextLine();
        System.out.print("Input meal price: ");
        try {
            price = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        try {
            service.addMeal(name, category, description, price);
        }catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method that controls the delete menu (taking input and producing output)
     */
    private void deleteMenu() {
        int id;
        System.out.print("\nInput meal ID: ");
        try {
            id = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Incorrect ID value");
            return;
        }
        try {
            service.deleteMeal(id);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method that controls the find menu (taking input and producing output)
     */
    private void findMenu(){
        int id;
        System.out.print("\nInput meal ID: ");
        try {
            id = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Incorrect ID value");
            return;
        }
        try {
            Meal m = service.findMeal(id);
            System.out.println(m);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method that controls the show menu (taking input and producing output)
     */
    private void showMenu(){
        List<Meal> list = service.getAll();
        System.out.println("\nMENU\n");
        for (Meal m : list){
            System.out.println(m);
        }
    }

    /**
     * Method that modifies data so program can stop
     */
    private void exit(){
        running = false;
    }
}
