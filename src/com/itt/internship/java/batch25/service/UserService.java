package com.itt.internship.java.batch25.service;

import com.itt.internship.java.batch25.entity.User;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<User> userList = new ArrayList<>();

    private boolean isValidRole(String role) {
        return role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("viewer");
    }

    public void addUser(User user) {
        if (!isValidRole(user.getRole())) {
            System.out.println("Invalid role: " + user.getRole() + ". User not added.");
            return;
        }
        if (getUser(user.getUsername()) != null) {
            System.out.println("User already exists: " + user.getUsername() + ". Please use a different username.");
            return;
        }
        userList.add(user);
        System.out.println("User added successfully: " + user.getUsername());
    }

    public User getUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public void listUsers() {
        if (userList.isEmpty()) {
            System.out.println("No users available.");
        } else {
            userList.forEach(user -> {
                System.out.println("Username: " + user.getUsername());
                System.out.println("Role: " + user.getRole());
                System.out.println("---------------------------");
            });
        }
    }

    public List<User> getAllUsers() {
        return userList;
    }
}
