package com.itt.internship.java.batch25.entity;

public class Hardware extends Asset {
    private String model;
    private String purchaseDate;

    public Hardware(int serialNumber, String name, String model, String purchaseDate) {
        super(serialNumber, name);
        this.model = model;
        this.purchaseDate = purchaseDate;
    }

    @Override
    public void display() {
        System.out.println("Serial Number: " + getSerialNumber());
        System.out.println("Name: " + getName());
        System.out.println("Model: " + model);
        System.out.println("Purchase Date: " + purchaseDate);
    }
}
