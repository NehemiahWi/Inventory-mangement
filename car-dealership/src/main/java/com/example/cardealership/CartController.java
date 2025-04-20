package com.example.cardealership;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CartController {
    @FXML
    private TextArea cartTextArea;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private TextField removeIndexField; // Input field for index of item to remove

    private ArrayList<Vehicle> cart;

    public void setCart(ArrayList<Vehicle> cart) {
        this.cart = cart;
        displayCartDetails();
    }

    private void displayCartDetails() {
        if (cart == null || cart.isEmpty()) {
            cartTextArea.setText("Your cart is empty.");
            totalPriceLabel.setText("Total: $0.00");
            return;
        }

        StringBuilder cartDetails = new StringBuilder();
        double total = 0.0;

        for (int i = 0; i < cart.size(); i++) {
            Vehicle vehicle = cart.get(i);
            cartDetails.append(i + 1) // Display 1-based index
                    .append(". ")
                    .append(vehicle.getMake())
                    .append(" ")
                    .append(vehicle.getModel())
                    .append(" - $")
                    .append(vehicle.calculateTotalPrice())
                    .append("\n");
            total += vehicle.calculateTotalPrice();
        }

        cartTextArea.setText(cartDetails.toString());
        totalPriceLabel.setText(String.format("Total: $%.2f", total));
    }

    @FXML
    protected void onRemoveItem() {
        try {
            int index = Integer.parseInt(removeIndexField.getText()) - 1; // Convert to 0-based index

            if (index >= 0 && index < cart.size()) {
                Vehicle removedVehicle = cart.remove(index);
                displayCartDetails();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Item Removed");
                alert.setHeaderText(null);
                alert.setContentText("Removed: " + removedVehicle.getMake() + " " + removedVehicle.getModel());
                alert.showAndWait();
            } else {
                showErrorAlert("Invalid Index", "Please enter a valid index of an item in the cart.");
            }
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid Input", "Please enter a numeric index.");
        }
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    protected void onClose() {
        Stage stage = (Stage) cartTextArea.getScene().getWindow();
        stage.close();
    }
}
