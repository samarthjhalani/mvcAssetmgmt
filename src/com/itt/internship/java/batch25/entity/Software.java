package com.itt.internship.java.batch25.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Software extends Asset {
    private String licenseKey;
    private LocalDate expiryDate;

    public Software(int serialNumber, String name, String description, LocalDateTime createdDate, LocalDateTime updatedDate, String licenseKey, String expiryDate) {
        super(serialNumber, name, description, createdDate, updatedDate);
        this.licenseKey = licenseKey;
        this.expiryDate = LocalDate.parse(expiryDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public String getExpiryDateAsString() {
        return expiryDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public void display() {
        System.out.println("Asset Type: Software License");
        System.out.println("Serial Number: " + getSerialNumber());
        System.out.println("Name: " + getName());
        System.out.println("Description: " + getDescription());
        System.out.println("Created Date: " + getCreatedDate());
        System.out.println("Updated Date: " + getUpdatedDate());
        System.out.println("License Key: " + licenseKey);
        System.out.println("Expiry Date: " + getExpiryDateAsString());
    }
}
