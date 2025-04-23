package com.itt.internship.java.batch25.dto;

public class HardwareDTO {
    private final int serialNumber;
    private final String name;
    private final String brand;
    private final String warrantyPeriod;
    private final String purchaseDate;

    public HardwareDTO(int serialNumber, String name, String brand, String warrantyPeriod, String purchaseDate) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
        this.purchaseDate = purchaseDate;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }
}
