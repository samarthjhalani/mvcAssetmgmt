package com.itt.internship.java.batch25.dto;

import com.itt.internship.java.batch25.entity.User;

public class UserSession {
    private User user;

    // Constructor to initialize the user
    public UserSession(User user) {
        this.user = user;
    }

    // Default constructor (in case you need it)
    public UserSession() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
