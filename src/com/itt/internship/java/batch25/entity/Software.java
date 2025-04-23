package com.itt.internship.java.batch25.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Software extends Asset {
    private String licenseKey;
    private LocalDate expiryDate;

    // Constructor updated to accept LocalDate
    public Software(int serialNumber, String name, String description, String licenseKey, String expiryDate) {
        super(serialNumber, name, description);
        this.licenseKey = licenseKey;
        this.expiryDate = LocalDate.parse(expiryDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    // Utility method to convert expiry date back to String if needed
    public String getExpiryDateAsString() {
        return expiryDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public void display() {

        System.out.println("License Key: " + licenseKey);
        System.out.println("Expiry Date: " + getExpiryDateAsString());
    }
}
