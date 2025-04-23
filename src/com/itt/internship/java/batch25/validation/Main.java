package com.itt.internship.java.batch25.validation;

import com.itt.internship.java.batch25.entity.*;
import com.itt.internship.java.batch25.service.AssetService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String uname = sc.nextLine();
        System.out.print("Enter your role (admin / manager / viewer): ");
        String role = sc.next().toLowerCase();

        if (!InputValidator.isValidRole(role)) {
            System.out.println("Invalid role. Exiting...");
            return;
        }

        User currentUser = new User(uname, role);
        AssetService service = new AssetService(currentUser);

        System.out.println("Welcome, " + uname + " (" + role + ")");

        // Check for expiring assets at the start of the session
        service.checkForExpiringAssets();

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Asset");
            System.out.println("2. Search Asset");
            System.out.println("3. Update Asset");
            System.out.println("4. Delete Asset");
            System.out.println("5. List All Assets");
            System.out.println("6. Filter Books by Author Name");
            System.out.println("7. Logout/Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    if (currentUser.getRole().equalsIgnoreCase("admin") || currentUser.getRole().equalsIgnoreCase("manager")) {
                        System.out.println("Select Asset Type (1. Book, 2. Hardware, 3. Software License): ");
                        int type = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Asset Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Serial Number: ");
                        int serialNumber = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Description: ");
                        String description = sc.nextLine();

                        Asset asset = null;
                        switch (type) {
                            case 1:
                                System.out.print("Enter Author Name: ");
                                String author = sc.nextLine();
                                asset = new Book(serialNumber, name, description, author);
                                break;
                            case 2:
                                System.out.print("Enter Hardware Type: ");
                                String hardwareType = sc.nextLine();
                                asset = new Hardware(serialNumber, name, description, hardwareType);
                                break;
                            case 3:
                                System.out.print("Enter License Key: ");
                                String licenseKey = sc.nextLine();
                                asset = new Software(serialNumber, name, description, licenseKey,ex);
                                break;
                            default:
                                System.out.println("Invalid asset type.");
                        }

                        if (asset != null) {
                            service.addAsset(asset);
                        }
                    } else {
                        System.out.println("You don't have permission to add assets.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Asset Serial Number to Search: ");
                    int searchSerial = sc.nextInt();
                    Asset foundAsset = service.searchAsset(searchSerial);
                    if (foundAsset != null) {
                        foundAsset.display();
                    } else {
                        System.out.println("Asset not found.");
                    }
                    break;

                case 3:
                    if (currentUser.getRole().equalsIgnoreCase("admin") || currentUser.getRole().equalsIgnoreCase("manager")) {
                        System.out.println("Select Asset Type (1. Book, 2. Hardware, 3. Software License): ");
                        int type = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Asset Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Serial Number: ");
                        int serialNumber = sc.nextInt();
                        sc.nextLine();  // Consume newline
                        System.out.print("Enter Description: ");
                        String description = sc.nextLine();

                        Asset updatedAsset = null;
                        switch (type) {
                            case 1:
                                System.out.print("Enter Author Name: ");
                                String author = sc.nextLine();
                                updatedAsset = new Book(serialNumber, name, description, author);
                                break;
                            case 2:
                                System.out.print("Enter Hardware Type: ");
                                String hardwareType = sc.nextLine();
                                updatedAsset = new Hardware(serialNumber, name, description, hardwareType);
                                break;
                            case 3:
                                System.out.print("Enter License Key: ");
                                String licenseKey = sc.nextLine();
                                System.out.print("Enter Expiry Date (yyyy-MM-dd): ");
                                String expiryDate = sc.nextLine();  // Capturing expiry date as a string
                                updatedAsset = new Software(serialNumber, name, description, licenseKey, expiryDate);
                                break;
                            default:
                                System.out.println("Invalid asset type.");
                        }

                        if (updatedAsset != null) {
                            service.updateAsset(serialNumber, updatedAsset);
                        }
                    } else {
                        System.out.println("You don't have permission to update assets.");
                    }
                    break;

                case 4:
                    if (currentUser.getRole().equalsIgnoreCase("admin")) {
                        System.out.print("Enter Asset Serial Number to Delete: ");
                        int deleteSerial = sc.nextInt();
                        service.deleteAsset(deleteSerial);
                    } else {
                        System.out.println("You don't have permission to delete assets.");
                    }
                    break;

                case 5:
                    service.listAssets();
                    break;

                case 6:
                    System.out.print("Enter Author Name to Filter Books: ");
                    String authorName = sc.nextLine();
                    service.filterBooksByAuthor(authorName);
                    break;

                case 7:
                    System.out.println("Logging out...");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        sc.close();
    }
}
