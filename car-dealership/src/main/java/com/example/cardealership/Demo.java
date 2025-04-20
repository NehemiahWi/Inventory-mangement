package com.example.cardealership;

import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the dealership name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the dealership location: ");
        String location = scanner.nextLine();

        CarDealership dealership = new CarDealership(name, location);

        boolean running = true;

        while (running) {
            System.out.println("\n--- Car Dealership Management ---");
            System.out.println("1. Add a vehicle");
            System.out.println("2. Add a customer");
            System.out.println("3. View customers");
            System.out.println("4. View inventory");
            System.out.println("5. Save inventory to file");
            System.out.println("6. Load inventory from file");
            System.out.println("7. Save customers to file");
            System.out.println("8. Load customers from file");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addVehicle(scanner, dealership);
                    break;
                case "2":
                    addCustomer(scanner, dealership);
                    break;
                case "3":
                    viewCustomers(dealership);
                    break;
                case "4":
                    viewInventory(dealership);
                    break;
                case "5":
                    System.out.print("Enter filename to save inventory: ");
                    String inventoryFile = scanner.nextLine();
                    dealership.saveInventoryToFile(inventoryFile);
                    break;
                case "6":
                    System.out.print("Enter filename to load inventory: ");
                    inventoryFile = scanner.nextLine();
                    dealership.loadInventoryFromFile(inventoryFile);
                    break;
                case "7":
                    System.out.print("Enter filename to save customers: ");
                    String customerFile = scanner.nextLine();
                    dealership.saveCustomersToFile(customerFile);
                    break;
                case "8":
                    System.out.print("Enter filename to load customers: ");
                    customerFile = scanner.nextLine();
                    dealership.loadCustomersFromFile(customerFile);
                    break;
                case "9":
                    running = false;
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addVehicle(Scanner scanner, CarDealership dealership) {
        System.out.print("Enter color of vehicle: ");
        String color = scanner.nextLine().trim();
        System.out.print("Enter make of vehicle: ");
        String make = scanner.nextLine().trim();
        System.out.print("Enter model of vehicle: ");
        String model = scanner.nextLine().trim();
        System.out.print("Enter number of doors: ");
        int doors = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter discount amount: ");
        double discount = Double.parseDouble(scanner.nextLine().trim());
        System.out.print("Enter price of vehicle: ");
        double price = Double.parseDouble(scanner.nextLine().trim());

        Vehicle vehicle = new Vehicle(color, make, model, doors, discount, price);
        dealership.addVehicle(vehicle);

        System.out.println("Vehicle added successfully!");
        System.out.println(vehicle);
    }

    private static void addCustomer(Scanner scanner, CarDealership dealership) {
        System.out.print("Enter customer's name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter customer's age: ");
        int age = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter customer's address: ");
        String address = scanner.nextLine().trim();
        System.out.print("Enter customer's phone number: ");
        String phoneNumber = scanner.nextLine().trim();

        System.out.print("Enter customer's email: ");
        String email = scanner.nextLine().trim();
        Customer customer = new Customer(name, age, address, phoneNumber, email);
        dealership.addCustomer(customer);

        System.out.println("Customer added successfully!");
        System.out.println(customer);
    }

    private static void viewCustomers(CarDealership dealership) {
        System.out.println("\n--- Customer Information ---");
        if (dealership.getCustomers().isEmpty()) {
            System.out.println("No customers found.");
        } else {
            for (Customer customer : dealership.getCustomers()) {
                System.out.println(customer);
            }
        }
    }

    private static void viewInventory(CarDealership dealership) {
        System.out.println("\n--- Vehicle Inventory ---");
        if (dealership.getInventory().isEmpty()) {
            System.out.println("No vehicles in inventory.");
        } else {
            int count = 1;
            for (Vehicle vehicle : dealership.getInventory()) {
                System.out.printf("Vehicle %d:\n%s\n", count++, vehicle);
            }
        }
    }
}
