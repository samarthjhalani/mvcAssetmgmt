package com.itt.internship.java.batch25.entity;

import java.time.LocalDate;

public class Book extends Asset {
    private String author;
    private LocalDate publishDate;

    public Book(String serialNumber, String name, String author, LocalDate publishDate, String createdBy) {
        super(serialNumber, name, createdBy);
        this.author = author;
        this.publishDate = publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    @Override
    public String getDetails() {
        return serialNumber + " " + name + " " + author + " " + publishDate;
    }
}
