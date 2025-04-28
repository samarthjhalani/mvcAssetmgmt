package com.itt.internship.java.batch25.entity;

public class User {
    private String firstName;
    private String lastName;
    private String role; // Admin or User

    public User(String firstName, String lastName, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }
}
