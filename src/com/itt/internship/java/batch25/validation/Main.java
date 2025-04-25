package com.itt.internship.java.batch25.validation;

import com.itt.internship.java.batch25.entity.*;
import com.itt.internship.java.batch25.service.AssetService;
import com.itt.internship.java.batch25.service.UserService;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your username: ");
        String uname = sc.nextLine();

        System.out.print("Enter Last Name: ");
        String LastName = sc.nextLine();

        System.out.println("Enter the password");
        String password = sc.nextLine();

        System.out.print("Enter your role (admin / user ): ");
        String role = sc.next().toLowerCase();
        sc.nextLine();

        if (!InputValidator.isValidRole(role)) {
            System.out.println("Invalid role. Exiting...");
            return;
        }

        User currentUser = new User(uname, password, role, LastName);

        AssetService assetService = new AssetService(currentUser);
        UserService userService = new UserService();

        System.out.println("Welcome, " + uname + " " + LastName + " (" + role + ")");
        assetService.checkForExpiringAssets();

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Asset");
            System.out.println("2. Search Asset");
            System.out.println("3. Update Asset");
            System.out.println("4. Delete Asset");
            System.out.println("5. List All Assets");
            System.out.println("6. Filter Books by Author Name");
            System.out.println("7. List All Users");
            System.out.println("8. Logout/Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    if (role.equals("admin") || role.equals("user")) {
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

                        LocalDateTime now = LocalDateTime.now();
                        Asset asset = null;

                        switch (type) {
                            case 1:
                                System.out.print("Enter Author Name: ");
                                String author = sc.nextLine();
                                System.out.print("Enter Date of Publish (Year): ");
                                int dateOfPublish = sc.nextInt();
                                sc.nextLine();
                                asset = new Book(serialNumber, name, description, now, now, author, dateOfPublish);
                                break;
                            case 2:
                                System.out.print("Enter Hardware Type: ");
                                String hardwareType = sc.nextLine();
                                System.out.print("Enter Purchase Date: ");
                                String purchaseDate = sc.nextLine();
                                asset = new Hardware(serialNumber, name, description, now, now, hardwareType, purchaseDate);
                                break;
                            case 3:
                                System.out.print("Enter License Key: ");
                                String licenseKey = sc.nextLine();
                                System.out.print("Enter Expiry Date (yyyy-MM-dd): ");
                                String expiryDate = sc.nextLine();
                                asset = new Software(serialNumber, name, description, now, now, licenseKey, expiryDate);
                                break;
                            default:
                                System.out.println("Invalid asset type.");
                        }

                        if (asset != null) {
                            assetService.addAsset(asset);
                        }
                    } else {
                        System.out.println("You don't have permission to add assets.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Asset Serial Number to Search: ");
                    int searchSerial = sc.nextInt();
                    sc.nextLine();
                    Asset foundAsset = assetService.searchAsset(searchSerial);
                    if (foundAsset != null) {
                        foundAsset.display();
                    } else {
                        System.out.println("Asset not found.");
                    }
                    break;

                case 3:
                    if (role.equals("admin") || role.equals("user")) {
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

                        Asset updatedAsset = null;
                        LocalDateTime now = LocalDateTime.now();

                        switch (type) {
                            case 1:
                                System.out.print("Enter Author Name: ");
                                String author = sc.nextLine();
                                System.out.print("Enter Date of Publish (Year): ");
                                int dateOfPublish = sc.nextInt();
                                sc.nextLine();
                                updatedAsset = new Book(serialNumber, name, description, now, now, author, dateOfPublish);
                                break;
                            case 2:
                                System.out.print("Enter Hardware Type: ");
                                String hardwareType = sc.nextLine();
                                System.out.print("Enter Purchase Date: ");
                                String purchaseDate = sc.nextLine();
                                updatedAsset = new Hardware(serialNumber, name, description, now, now, hardwareType, purchaseDate);
                                break;
                            case 3:
                                System.out.print("Enter License Key: ");
                                String licenseKey = sc.nextLine();
                                System.out.print("Enter Expiry Date (yyyy-MM-dd): ");
                                String expiryDate = sc.nextLine();
                                updatedAsset = new Software(serialNumber, name, description, now, now, licenseKey, expiryDate);
                                break;
                            default:
                                System.out.println("Invalid asset type.");
                        }

                        if (updatedAsset != null) {
                            assetService.updateAsset(serialNumber, updatedAsset);
                        }
                    } else {
                        System.out.println("You don't have permission to update assets.");
                    }
                    break;

                case 4:
                    if (role.equals("admin")) {
                        System.out.print("Enter Asset Serial Number to Delete: ");
                        int deleteSerial = sc.nextInt();
                        sc.nextLine();
                        assetService.deleteAsset(deleteSerial);
                    } else {
                        System.out.println("You don't have permission to delete assets.");
                    }
                    break;

                case 5:
                    assetService.listAssets();
                    break;

                case 6:
                    System.out.print("Enter Author Name to Filter Books: ");
                    String authorName = sc.nextLine();
                    assetService.filterBooksByAuthor(authorName);
                    break;

                case 7:
                    userService.listUsers();
                    break;

                case 8:
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
