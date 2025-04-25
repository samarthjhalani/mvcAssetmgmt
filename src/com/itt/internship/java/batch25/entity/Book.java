package com.itt.internship.java.batch25.entity;

import java.time.LocalDateTime;

public class Book extends Asset {
    private String author;
    private int dateOfPublish;

    public Book(int serialNumber, String name, String description, LocalDateTime createdate, LocalDateTime updatedate, String author, int dateOfPublish) {
        super(serialNumber, name, description, createdate, updatedate);
        this.author = author;
        this.dateOfPublish = dateOfPublish;
    }

    public String getAuthor() {
        return author;
    }





    @Override
    public void display() {
        System.out.println("Serial Number: " + getSerialNumber());
        System.out.println("Name: " + getName());
        System.out.println("Author: " + author);
        System.out.println("Date Of Publish: " + dateOfPublish);
    }
}
