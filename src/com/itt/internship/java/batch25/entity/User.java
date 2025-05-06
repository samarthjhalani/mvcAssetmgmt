package com.itt.internship.java.batch25.entity;

public class User {
    private String firstName;
    private String lastName;
    private String role;
    private String password;  // <-- add password field

    public User(String firstName, String lastName, String role, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.password = password;
    }

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getRole() { return role; }
    public String getPassword() { return password; }
}
