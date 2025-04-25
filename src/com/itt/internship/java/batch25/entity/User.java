package com.itt.internship.java.batch25.entity;

public class User {
    private String username;
    private String password;

    private String lastName;
    private String role;

    public User(String username, String password, String lastName, String role) {
        this.username = username;
        this.password = String.valueOf(password);

        this.lastName = lastName;
        this.role = role.toLowerCase();
    }

    public String getUsername() {
        return username;
    }


    public String getRole() {
        return role;
    }
}
