package com.itt.internship.java.batch25.entity;

public class Book extends Asset {
    private String author;
    private String dateOfPublish;


    public Book(int serialNumber, String name, String author, String dateOfPublish) {
        super(serialNumber, name);
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
