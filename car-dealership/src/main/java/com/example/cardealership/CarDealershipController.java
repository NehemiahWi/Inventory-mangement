package com.example.cardealership;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CarDealershipController {

    @FXML
    private Label statusLabel;

    @FXML
    private TextField dealershipNameField;
    @FXML
    private TextField locationField;

    @FXML
    private TextField colorField;
    @FXML
    private TextField makeField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField doorsField;
    @FXML
    private TextField discountField;
    @FXML
    private TextField priceField;

    @FXML
    private TextField customerNameField;
    @FXML
    private TextField customerAgeField;  // New field
    @FXML
    private TextField customerAddressField;  // New field
    @FXML
    private TextField customerPhoneField;  // New field
    @FXML
    private TextField customerEmailField;  // New field

    @FXML
    private TextField vehicleIndexField;

    private CarDealership dealership;
    private ArrayList<Vehicle> cart;        // Cart to store selected vehicles
    private ArrayList<Vehicle> inventory;  // Inventory to store vehicles

    public void initialize() {
        dealership = new CarDealership("Default Dealership", "Default Location");
        cart = new ArrayList<>();          // Initialize the cart
        inventory = new ArrayList<>();     // Initialize the inventory
    }


    @FXML
    protected void onAddVehicle() {
        try {
            String color = colorField.getText();
            String make = makeField.getText();
            String model = modelField.getText();
            int doors = Integer.parseInt(doorsField.getText());
            double discount = Double.parseDouble(discountField.getText());
            double price = Double.parseDouble(priceField.getText());

            Vehicle vehicle = new Vehicle(color, make, model, doors, discount, price);
            dealership.addVehicle(vehicle);

            statusLabel.setText("Vehicle added successfully!");
        } catch (Exception e) {
            statusLabel.setText("Error adding vehicle: " + e.getMessage());
        }
    }

    @FXML
    protected void onViewVehicles() {
        if (dealership.getInventory().isEmpty()) {
            statusLabel.setText("No vehicles in inventory.");
        } else {
            StringBuilder vehicleList = new StringBuilder("Vehicles:\n");
            dealership.getInventory().forEach(vehicle -> vehicleList.append(vehicle).append("\n"));
            statusLabel.setText(vehicleList.toString());
        }
    }

    @FXML
    protected void onAddCustomer() {
        try {
            // Get input values
            String name = customerNameField.getText();
            int age = Integer.parseInt(customerAgeField.getText()); // Ensure age is numeric
            String address = customerAddressField.getText();
            String phoneNumber = customerPhoneField.getText();
            String email = customerEmailField.getText();

            // Validate inputs (optional, for better user experience)
            if (name.isEmpty() || address.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
                statusLabel.setText("All fields must be filled!");
                return;
            }

            if (age <= 0) {
                statusLabel.setText("Age must be a positive number!");
                return;
            }

            // Create and add the customer
            Customer customer = new Customer(name, age, address, phoneNumber, email);
            dealership.addCustomer(customer);

            statusLabel.setText("Customer added successfully!");
        } catch (NumberFormatException e) {
            statusLabel.setText("Error: Age must be a valid number.");
        } catch (Exception e) {
            statusLabel.setText("Error adding customer: " + e.getMessage());
        }
    }


    @FXML
    protected void onViewCustomers() {
        if (dealership.getCustomers().isEmpty()) {
            statusLabel.setText("No customers available.");
        } else {
            StringBuilder customerList = new StringBuilder("Customers:\n");
            dealership.getCustomers().forEach(customer -> customerList.append(customer).append("\n"));
            statusLabel.setText(customerList.toString());
        }
    }

    @FXML
    protected void onCalculatePrice() {
        try {
            int index = Integer.parseInt(vehicleIndexField.getText());
            String result = dealership.calculateVehiclePrice(index);
            statusLabel.setText(result);
        } catch (Exception e) {
            statusLabel.setText("Error calculating price: " + e.getMessage());
        }
    }

    @FXML
    protected void onSaveInventory() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Inventory");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON Files", "*.json")
        );
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            dealership.saveInventoryToFile(file.getAbsolutePath());
            statusLabel.setText("Inventory saved to " + file.getName());
        }
    }

    @FXML
    protected void onLoadInventory() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Inventory");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON Files", "*.json")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            dealership.loadInventoryFromFile(file.getAbsolutePath());
            statusLabel.setText("Inventory loaded from " + file.getName());
        }
    }

    @FXML
    protected void onSaveCustomers() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Customers");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON Files", "*.json")
        );
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            dealership.saveCustomersToFile(file.getAbsolutePath());
            statusLabel.setText("Customers saved to " + file.getName());
        }
    }

    @FXML
    protected void onLoadCustomers() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Customers");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("JSON Files", "*.json")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            dealership.loadCustomersFromFile(file.getAbsolutePath());
            statusLabel.setText("Customers loaded from " + file.getName());
        }
    }

    @FXML
    protected void onAddToCart() {
        try {
            int index = Integer.parseInt(vehicleIndexField.getText()) - 1;

            if (index >= 0 && index < dealership.getInventory().size()) {
                Vehicle selectedVehicle = dealership.getInventory().get(index);
                cart.add(selectedVehicle);

                statusLabel.setText(selectedVehicle.getModel() + " added to cart.");
            } else {
                statusLabel.setText("Invalid vehicle index.");
            }
        } catch (Exception e) {
            statusLabel.setText("Error adding to cart: " + e.getMessage());
        }
    }

    @FXML
    protected void onCheckout() {
        if (cart.isEmpty()) {
            statusLabel.setText("Cart is empty. Add vehicles to the cart before checking out.");
            return;
        }

        double total = 0.0;

        // Calculate the total price of items in the cart
        for (Vehicle vehicle : cart) {
            total += vehicle.calculateTotalPrice();
        }

        // Apply discounts
        double discount = calculateDiscount(total);
        total -= discount;

        // Add tax (7%)
        double totalWithTax = total * 1.07;

        // Display summary
        StringBuilder receipt = new StringBuilder("Receipt:\n");
        for (Vehicle vehicle : cart) {
            receipt.append(vehicle.getModel()).append(" - $").append(vehicle.calculateTotalPrice()).append("\n");
        }
        receipt.append(String.format("\nSubtotal: $%.2f\nDiscount: $%.2f\nTax (7%%): $%.2f\nTotal: $%.2f",
                total, discount, totalWithTax - total, totalWithTax));

        statusLabel.setText(receipt.toString());

        // Clear the cart after checkout
        cart.clear();
    }

    private double calculateDiscount(double total) {
        if (total >= 100) return total * 0.20;
        if (total >= 50) return total * 0.10;
        if (total >= 25) return total * 0.05;
        return 0.0;
    }

    @FXML
    protected void onUploadVideoTestimony() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Video Testimony");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi", "*.mov")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            System.out.println("Video testimony uploaded: " + file.getName());
            statusLabel.setText("Uploaded video testimony: " + file.getName());
        } else {
            statusLabel.setText("Video upload canceled.");
        }
    }

    @FXML
    protected void onPurchaseItems() {
        if (dealership.getInventory().isEmpty()) {
            statusLabel.setText("No vehicles available for purchase.");
            return;
        }

        StringBuilder inventoryDisplay = new StringBuilder("Available Vehicles:\n");
        for (int i = 0; i < dealership.getInventory().size(); i++) {
            inventoryDisplay.append(i + 1).append(". ").append(dealership.getInventory().get(i)).append("\n");
        }
        System.out.println(inventoryDisplay);

        double total = 0.0;
        for (Vehicle vehicle : cart) {
            total += vehicle.calculateTotalPrice();
        }

        double discount = calculateDiscount(total);
        total -= discount;
        double totalWithTax = total * 1.07;

        StringBuilder receipt = new StringBuilder("Receipt:\n");
        for (Vehicle vehicle : cart) {
            receipt.append(vehicle.getModel()).append(" - $").append(vehicle.calculateTotalPrice()).append("\n");
        }
        receipt.append(String.format("\nSubtotal: $%.2f\nDiscount: $%.2f\nTax (7%%): $%.2f\nTotal: $%.2f",
                total, discount, totalWithTax - total, totalWithTax));
        statusLabel.setText(receipt.toString());

        cart.clear();
    }

    @FXML
    protected void onExitProgram() {
        System.out.println("Exiting the program...");
        System.exit(0);
    }

    @FXML
    protected void onShowCart() {
        if (cart.isEmpty()) {
            statusLabel.setText("Cart is empty.");
            return;
        }

        StringBuilder cartDetails = new StringBuilder("Cart Items:\n");
        double total = 0.0;
        for (Vehicle vehicle : cart) {
            cartDetails.append(vehicle.getMake()).append(" ").append(vehicle.getModel()).append(" - $")
                    .append(vehicle.calculateTotalPrice()).append("\n");
            total += vehicle.calculateTotalPrice();
        }

        double discount = calculateDiscount(total);
        total -= discount;
        double totalWithTax = total * 1.07;

        cartDetails.append(String.format("\nSubtotal: $%.2f\nDiscount: $%.2f\nTax: $%.2f\nTotal: $%.2f",
                total, discount, totalWithTax - total, totalWithTax));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cart Details");
        alert.setHeaderText("Your Cart");
        alert.setContentText(cartDetails.toString());
        alert.showAndWait();
    }

    @FXML
    protected void showCart() {
        try {
            // Load the cart-view.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cart-view.fxml"));
            Parent root = loader.load();

            // Get the controller for the cart-view
            CartController cartController = loader.getController();
            cartController.setCart(cart); // Pass the cart data to the controller

            // Create a new stage for the cart view
            Stage stage = new Stage();
            stage.setTitle("Cart Details");
            stage.setScene(new Scene(root, 500, 400));
            stage.show();
        } catch (Exception e) {
            statusLabel.setText("Error loading cart view: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
