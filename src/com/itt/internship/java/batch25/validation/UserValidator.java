package com.itt.internship.java.batch25.validation;

import com.itt.internship.java.batch25.entity.User;

public class UserValidator {
    public static boolean isValidUser(User user) {
        return user.getUsername() != null && !user.getUsername().isEmpty() && user.getRole() != null;
    }
}
