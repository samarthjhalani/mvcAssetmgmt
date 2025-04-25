package com.itt.internship.java.batch25.entity;

import java.time.LocalDateTime;

public abstract class Asset {
    private int serialNumber;
    private String name;
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public Asset(int serialNumber, String name, String description, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.description = description;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
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

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public abstract void display();

    @Override
    public String toString() {
        return "Asset {" +
                "serialNumber=" + serialNumber +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
