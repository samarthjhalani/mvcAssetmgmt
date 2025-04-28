package com.itt.internship.java.batch25.entity;

import java.time.LocalDate;

public class SoftwareLicense extends Asset {
    private LocalDate expiryDate;

    public SoftwareLicense(String serialNumber, String name, LocalDate expiryDate, String createdBy) {
        super(serialNumber, name, createdBy);
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String getDetails() {
        return serialNumber + " " + name + " " + expiryDate;
    }
}
