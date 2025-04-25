package com.itt.internship.java.batch25.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hardware extends Asset {
    private String model;
    private LocalDate purchaseDate;


    public Hardware(int serialNumber, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt, String model, String purchaseDate) {
        super(serialNumber, name, description, createdAt, updatedAt);
        this.model = model;
        this.purchaseDate = LocalDate.parse(purchaseDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // Getters
    public String getModel() {
        return model;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public String getPurchaseDateAsString() {
        return purchaseDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


    @Override
    public void display() {
        System.out.println("Asset Type: Hardware");
        System.out.println("Serial Number: " + getSerialNumber());
        System.out.println("Name: " + getName());
        System.out.println("Description: " + getDescription());
        System.out.println("Created Date: " + getCreatedDate());
        System.out.println("Updated Date: " + getUpdatedDate());
        System.out.println("Model: " + model);
        System.out.println("Purchase Date: " + getPurchaseDateAsString());
    }
}
