package com.itt.internship.java.batch25.dto;

public class SoftwareDTO {
    private final int serialNumber;
    private final String name;
    private final String version;
    private final String licenseKey;
    private final String dateOfLicense;

    public SoftwareDTO(int serialNumber, String name, String version, String licenseKey, String dateOfLicense) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.version = version;
        this.licenseKey = licenseKey;
        this.dateOfLicense = dateOfLicense;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public String getDateOfLicense() {
        return dateOfLicense;
    }
}
