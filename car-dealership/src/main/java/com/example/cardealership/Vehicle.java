package com.example.cardealership;

public class Vehicle {
    private String color;
    private String make;
    private String model;
    private int doors;
    private double discount;
    private double price;

    public Vehicle(String color, String make, String model, int doors, double discount, double price) {
        this.color = color;
        this.make = make;
        this.model = model;
        this.doors = doors;
        this.discount = discount;
        this.price = price;
    }

    public double calculateTotalPrice() {
        return price - discount;
    }

    @Override
    public String toString() {
        return String.format("Color: %s, Make: %s, Model: %s, Doors: %d, Price: $%,.2f, Total Price: $%,.2f",
                color, make, model, doors, price, calculateTotalPrice());
    }

    // Getters
    public double getPrice() {
        return price;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getMake() {
        return make;
    }

    public int getDoors() {
        return doors;
    }

    public double getDiscount() {
        return discount;
    }

    // Setters (if needed)
    public void setPrice(double price) {
        this.price = price;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
