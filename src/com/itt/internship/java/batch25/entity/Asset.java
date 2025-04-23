package com.itt.internship.java.batch25.entity;

public abstract class Asset {
    private int serialNumber;
    private String name;
    private String description;  // Added description for more detailed information

    public Asset(int serialNumber, String name, String description) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.description = description;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public abstract void display();

    @Override
    public String toString() {
        return "Asset{" +
                "serialNumber=" + serialNumber +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
