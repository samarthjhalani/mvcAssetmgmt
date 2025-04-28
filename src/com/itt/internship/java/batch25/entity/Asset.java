package com.itt.internship.java.batch25.entity;

import java.time.LocalDateTime;

public abstract class Asset {
    protected String serialNumber;
    public String name;
    protected String createdBy;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public Asset(String serialNumber, String name, String createdBy) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public abstract String getDetails();

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
