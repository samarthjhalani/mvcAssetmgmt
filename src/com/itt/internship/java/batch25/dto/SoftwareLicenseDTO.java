package com.itt.internship.java.batch25.dto;

public class SoftwareLicenseDTO extends AssetDTO {
    private String licenseKey;
    private String expirationDate;

    // Getters and Setters
    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
