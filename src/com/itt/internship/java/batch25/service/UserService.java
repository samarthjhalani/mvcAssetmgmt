package com.itt.internship.java.batch25.service;

import com.itt.internship.java.batch25.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private final List<User> userList = new ArrayList<>();

    public void addUser(User user) {
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
            for (User user : userList) {
                System.out.println("Username: " + user.getUsername());
                System.out.println("Role: " + user.getRole());
                System.out.println("---------------------------");
            }
        }
    }


    public List<User> getAllUsers() {
        return userList;
    }
}
