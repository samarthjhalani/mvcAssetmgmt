package com.itt.internship.java.batch25.validation;

public class InputValidator {
    public static boolean isValidUsername(String username) {
        return username != null && !username.isEmpty();
    }

    public static boolean isValidRole(String role) {
        return role.equals("admin") || role.equals("user");
    }
}
