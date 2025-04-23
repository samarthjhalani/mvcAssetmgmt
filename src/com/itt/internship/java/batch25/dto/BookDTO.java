package com.itt.internship.java.batch25.dto;

public class BookDTO {
    private final int serialNumber;
    private final String name;
    private final String author;
    private final String dateOfPublish;

    public BookDTO(int serialNumber, String name, String author, String dateOfPublish) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.author = author;
        this.dateOfPublish = dateOfPublish;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDateOfPublish() {
        return dateOfPublish;
    }
}

