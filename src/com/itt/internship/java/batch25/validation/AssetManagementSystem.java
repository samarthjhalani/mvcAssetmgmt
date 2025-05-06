package com.itt.internship.java.batch25.validation;

import com.itt.internship.java.batch25.constant.RoleConstants;
import com.itt.internship.java.batch25.dto.UserSession;
import com.itt.internship.java.batch25.entity.User;
import com.itt.internship.java.batch25.service.AssetService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AssetManagementSystem {

    private static Map<String, User> users = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);
    private static AssetService assetService;
    private static UserSession userSession;

    public static void main(String[] args) {
        loadDummyUsers();
        while (true) {
            login();
            assetService = new AssetService(scanner, userSession);

            while (true) {
                assetService.checkNotifications();
                showMenu();
                int choice = getChoice();
                if (!handleChoice(choice)) {
                    break;
                }
            }
        }
    }

    private static void loadDummyUsers() {
        users.put("admin", new User("admin", "admin", RoleConstants.ADMIN, "admin123"));
        users.put("john", new User("John", "Doe", RoleConstants.USER, "john123"));
        users.put("alice", new User("Alice", "Smith", RoleConstants.USER, "alice123"));

        System.out.println("Dummy users loaded: " + users.keySet());
    }

    private static void login() {
        System.out.println("\n==== Welcome to Asset Management System ====");

        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                System.out.print("Enter Username: ");
                String username = scanner.nextLine().trim().toLowerCase();

                if (users.containsKey(username)) {
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();

                    User user = users.get(username);
                    if (user.getPassword().equals(password)) {
                        userSession = new UserSession(user);
                        System.out.println("Login successful! Welcome, " +
                                userSession.getUser().getFirstName() + " (" + userSession.getUser().getRole() + ")");
                        break; // Successful login
                    } else {
                        System.out.println("Incorrect password. Try again!");
                    }
                } else {
                    System.out.println("Invalid Username. Try again!");
                }

            } else if (choice.equals("2")) {
                registerNewUser();
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }
    }

    private static void registerNewUser() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim().toLowerCase();
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Role (admin/user): ");
        String role = scanner.nextLine().trim().toLowerCase();

        if (!role.equals(RoleConstants.ADMIN) && !role.equals(RoleConstants.USER)) {
            System.out.println("Invalid role. Only 'admin' or 'user' roles are allowed.");
            return;
        }

        users.put(username, new User(firstName, lastName, role, password));
        System.out.println("Registration successful! You can now login.");
    }

    private static void showMenu() {
        System.out.println("\n======== Main Menu ========");
        System.out.println("1. Add Asset");
        System.out.println("2. Search Asset");
        System.out.println("3. Update Asset");
        if (userSession.getUser().getRole().equals(RoleConstants.ADMIN)) {
            System.out.println("4. Delete Asset");
        }
        System.out.println("5. List All Assets");
        System.out.println("6. Filter Book By Author");
        if (userSession.getUser().getRole().equals(RoleConstants.ADMIN)) {
            System.out.println("7. View All Users");
        }
        System.out.println("8. Logout");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private static boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                assetService.addAsset();
                break;
            case 2:
                assetService.searchAsset();
                break;
            case 3:
                assetService.updateAsset();
                break;
            case 4:
                if (userSession.getUser().getRole().equals(RoleConstants.ADMIN)) {
                    assetService.deleteAsset();
                } else {
                    System.out.println("Invalid choice!");
                }
                break;
            case 5:
                assetService.listAssets();
                break;
            case 6:
                assetService.filterBooksByAuthor();
                break;
            case 7:
                if (userSession.getUser().getRole().equals(RoleConstants.ADMIN)) {
                    viewAllUsers();
                } else {
                    System.out.println("Invalid choice!");
                }
                break;
            case 8:
                System.out.println("Logging out...");
                return false;
            case 9:
                System.out.println("Thank you! Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Try again!");
        }
        return true;
    }

    private static void viewAllUsers() {
        System.out.println("\n====== Registered Users ======");
        for (Map.Entry<String, User> entry : users.entrySet()) {
            User user = entry.getValue();
            System.out.println("Username: " + entry.getKey() + ", Name: " + user.getFirstName() + " " + user.getLastName() + ", Role: " + user.getRole());
        }
    }
}
