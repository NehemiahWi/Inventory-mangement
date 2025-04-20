package com.example.cardealership;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CarDealership {
    private final String storeName;
    private final String location;
    private final ArrayList<Vehicle> inventory;
    private final ArrayList<Customer> customers;

    public CarDealership(String storeName, String location) {
        this.storeName = storeName;
        this.location = location;
        this.inventory = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public String calculateVehiclePrice(int vehicleIndex) {
        if (vehicleIndex >= 0 && vehicleIndex < inventory.size()) {
            Vehicle vehicle = inventory.get(vehicleIndex);
            return "The total price of the selected vehicle is: $" + vehicle.calculateTotalPrice();
        } else {
            return "Invalid vehicle index.";
        }
    }

    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void saveInventoryToFile(String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(inventory, writer);
            System.out.println("Inventory saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }

    public void saveCustomersToFile(String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(customers, writer);
            System.out.println("Customers saved successfully to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving customers: " + e.getMessage());
        }
    }

    public void loadInventoryFromFile(String filename) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            Vehicle[] vehicles = gson.fromJson(reader, Vehicle[].class);
            inventory.clear();
            inventory.addAll(Arrays.asList(vehicles));
            System.out.println("Inventory loaded successfully from " + filename);
        } catch (IOException e) {
            System.err.println("Error loading inventory: " + e.getMessage());
        }
    }

    public void loadCustomersFromFile(String filename) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            Customer[] customerArray = gson.fromJson(reader, Customer[].class);
            customers.clear();
            customers.addAll(Arrays.asList(customerArray));
            System.out.println("Customers loaded successfully from " + filename);
        } catch (IOException e) {
            System.err.println("Error loading customers: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format("Dealership: %s, Location: %s", storeName, location);
    }
}
