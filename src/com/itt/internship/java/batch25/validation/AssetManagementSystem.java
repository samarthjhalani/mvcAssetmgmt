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
    private static UserSession userSession = new UserSession();

    public static void main(String[] args) {
        loadDummyUsers();
        login();
        assetService = new AssetService(scanner, userSession);

        while (true) {
            assetService.checkNotifications();
            showMenu();
            int choice = getChoice();
            handleChoice(choice);
        }
    }

    private static void loadDummyUsers() {
        users.put("admin", new User("admin", "admin", RoleConstants.ADMIN));
        users.put("john", new User("John", "Doe", RoleConstants.USER));
        users.put("alice", new User("Alice", "Smith", RoleConstants.USER));
    }

    private static void login() {
        System.out.println("==== Welcome to Asset Management System ====");

        while (true) {
            System.out.println("\n1. Login");
            System.out.println("2. Register");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter Username: ");
                String username = scanner.nextLine();
                if (users.containsKey(username)) {
                    userSession = new UserSession(users.get(username));
                    System.out.println("Login successful! Welcome, " + userSession.getUser().getFirstName() + " (" + userSession.getUser().getRole() + ")");
                    break;
                } else {
                    System.out.println("Invalid Username. Try again!");
                }
            } else if (choice.equals("2")) {
                registerNewUser();
            } else {
                System.out.println("Invalid choice. Try again!");
            }
        }
    }

    private static void registerNewUser() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }

        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter Role (ADMIN/USER): ");
        String role = scanner.nextLine().toUpperCase();

        if (!role.equals(RoleConstants.ADMIN) && !role.equals(RoleConstants.USER)) {
            System.out.println("Invalid role. Only ADMIN or USER allowed.");
            return;
        }

        users.put(username, new User(firstName, lastName, role));
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
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private static void handleChoice(int choice) {
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
                System.out.println("Thank you! Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Try again!");
        }
    }
}
