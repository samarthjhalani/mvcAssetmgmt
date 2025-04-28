package com.itt.internship.java.batch25.entity;

public class Hardware extends Asset {
    private String hardwareType;

    public Hardware(String serialNumber, String name, String hardwareType, String createdBy) {
        super(serialNumber, name, createdBy);
        this.hardwareType = hardwareType;
    }

    public String getHardwareType() {
        return hardwareType;
    }

    @Override
    public String getDetails() {
        return serialNumber + " " + name + " " + hardwareType;
    }
}
