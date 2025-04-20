package com.example.cardealership;

public class Customer {
    private String name;
    private int age;
    private String address;
    private String phoneNumber;
    private String email;

    // Constructor
    public Customer(String name, int age, String address, String phoneNumber, String email) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "Name: %s, Age: %d, Address: %s, Phone: %s, Email: %s",
                name, age, address, phoneNumber, email
        );
    }

    // Getters and setters (if needed)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
