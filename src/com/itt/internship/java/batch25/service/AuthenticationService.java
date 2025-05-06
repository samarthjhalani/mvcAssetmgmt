package com.itt.internship.java.batch25.service;

import com.itt.internship.java.batch25.dto.UserSession;
import com.itt.internship.java.batch25.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthenticationService {

    private List<User> users = new ArrayList<>();

    public AuthenticationService() {
        // Adding some predefined users with passwords
        users.add(new User("admin", "admin", "Admin", "admin123")); // username: admin admin, password: admin123
        users.add(new User("user", "user", "User", "user123"));      // username: user user, password: user123
    }

    public UserSession login(Scanner scanner) {
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        for (User user : users) {
            if (user.getFirstName().equalsIgnoreCase(firstName) &&
                    user.getLastName().equalsIgnoreCase(lastName) &&
                    user.getPassword().equals(password)) {  // Password check
                System.out.println("Login successful as " + user.getRole());
                return new UserSession(user);
            }
        }

        System.out.println("Invalid Credentials! Try again.");
        return null;
    }
}
