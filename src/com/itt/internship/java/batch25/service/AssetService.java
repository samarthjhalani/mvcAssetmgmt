package com.itt.internship.java.batch25.service;

import com.itt.internship.java.batch25.constant.RoleConstants;
import com.itt.internship.java.batch25.dto.UserSession;
import com.itt.internship.java.batch25.entity.*;

import com.itt.internship.java.batch25.util.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class AssetService {

    private Map<String, List<Asset>> assetMap = new HashMap<>();
    private Scanner scanner;
    private UserSession userSession;

    public AssetService(Scanner scanner, UserSession userSession) {
        this.scanner = scanner;
        this.userSession = userSession;
        assetMap.put("Book", new ArrayList<>());
        assetMap.put("SoftwareLicense", new ArrayList<>());
        assetMap.put("Hardware", new ArrayList<>());
    }

    public void addAsset() {
        System.out.println("Enter asset type (Book, SoftwareLicense, Hardware): ");
        String type = scanner.nextLine();

        switch (type) {
            case "Book":
                addBook();
                break;
            case "SoftwareLicense":
                addSoftwareLicense();
                break;
            case "Hardware":
                addHardware();
                break;
            default:
                System.out.println("Invalid asset type!");
        }
    }

    private void addBook() {
        System.out.print("Enter Serial Number: ");
        String serial = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Date of Publish (dd-MM-yyyy): ");
        LocalDate publishDate = DateUtil.parseDate(scanner.nextLine());

        Book book = new Book(serial, name, author, publishDate, userSession.getUser().getFirstName());
        assetMap.get("Book").add(book);
        System.out.println("Book added successfully!");
    }

    private void addSoftwareLicense() {
        System.out.print("Enter Serial Number: ");
        String serial = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Expiry Date (dd-MM-yyyy): ");
        LocalDate expiryDate = DateUtil.parseDate(scanner.nextLine());

        SoftwareLicense sl = new SoftwareLicense(serial, name, expiryDate, userSession.getUser().getFirstName());
        assetMap.get("SoftwareLicense").add(sl);
        System.out.println("Software License added successfully!");
    }

    private void addHardware() {
        System.out.print("Enter Serial Number: ");
        String serial = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Hardware Type: ");
        String hardwareType = scanner.nextLine();

        Hardware hw = new Hardware(serial, name, hardwareType, userSession.getUser().getFirstName());
        assetMap.get("Hardware").add(hw);
        System.out.println("Hardware added successfully!");
    }

    public void listAssets() {
        for (Map.Entry<String, List<Asset>> entry : assetMap.entrySet()) {
            System.out.println("\nAsset Type: " + entry.getKey());
            List<Asset> assets = entry.getValue();

            List<Asset> visibleAssets = assets.stream()
                    .filter(asset -> userSession.getUser().getRole().equals(RoleConstants.ADMIN)
                            || asset.getCreatedBy().equals(userSession.getUser().getFirstName()))
                    .collect(Collectors.toList());

            if (visibleAssets.isEmpty()) {
                System.out.println("No assets available.");
            } else {
                visibleAssets.sort(Comparator.comparing(Asset::getCreatedAt)); // Default sorting by Created At
                for (Asset asset : visibleAssets) {
                    System.out.println(asset.getDetails() + " CreatedAt: " + asset.getCreatedAt() + " UpdatedAt: " + asset.getUpdatedAt());
                }
            }
        }
    }

    public void searchAsset() {
        System.out.println("Enter asset type (Book, SoftwareLicense, Hardware): ");
        String type = scanner.nextLine();

        if (!assetMap.containsKey(type)) {
            System.out.println("Invalid asset type!");
            return;
        }

        System.out.print("Enter Serial Number to search: ");
        String serialNumber = scanner.nextLine();

        List<Asset> assets = assetMap.get(type);
        for (Asset asset : assets) {
            if (asset.getSerialNumber().equals(serialNumber) &&
                    (userSession.getUser().getRole().equals(RoleConstants.ADMIN)
                            || asset.getCreatedBy().equals(userSession.getUser().getFirstName()))) {
                System.out.println(asset.getDetails() + " CreatedAt: " + asset.getCreatedAt() + " UpdatedAt: " + asset.getUpdatedAt());
                return;
            }
        }
        System.out.println("Asset not found or not accessible!");
    }

    public void updateAsset() {
        System.out.println("Enter asset type (Book, SoftwareLicense, Hardware): ");
        String type = scanner.nextLine();

        if (!assetMap.containsKey(type)) {
            System.out.println("Invalid asset type!");
            return;
        }

        System.out.print("Enter Serial Number to update: ");
        String serialNumber = scanner.nextLine();

        List<Asset> assets = assetMap.get(type);
        for (Asset asset : assets) {
            if (asset.getSerialNumber().equals(serialNumber) &&
                    (userSession.getUser().getRole().equals(RoleConstants.ADMIN)
                            || asset.getCreatedBy().equals(userSession.getUser().getFirstName()))) {

                System.out.print("Enter new Name: ");
                String newName = scanner.nextLine();
                asset.name = newName;
                asset.setUpdatedAt(LocalDateTime.now());
                System.out.println("Asset updated successfully!");
                return;
            }
        }
        System.out.println("Asset not found or not accessible!");
    }

    public void deleteAsset() {
        if (userSession.getUser().getRole().equals(RoleConstants.USER)) {
            System.out.println("You are not allowed to delete assets!");
            return;
        }

        System.out.println("Enter asset type (Book, SoftwareLicense, Hardware): ");
        String type = scanner.nextLine();

        if (!assetMap.containsKey(type)) {
            System.out.println("Invalid asset type!");
            return;
        }

        System.out.print("Enter Serial Number to delete: ");
        String serialNumber = scanner.nextLine();

        List<Asset> assets = assetMap.get(type);
        Iterator<Asset> iterator = assets.iterator();
        while (iterator.hasNext()) {
            Asset asset = iterator.next();
            if (asset.getSerialNumber().equals(serialNumber)) {
                iterator.remove();
                System.out.println("Asset deleted successfully!");
                return;
            }
        }
        System.out.println("Asset not found!");
    }

    public void checkNotifications() {
        for (List<Asset> assets : assetMap.values()) {
            for (Asset asset : assets) {
                if (asset instanceof SoftwareLicense) {
                    SoftwareLicense sl = (SoftwareLicense) asset;
                    LocalDate expiryDate = sl.getExpiryDate();
                    LocalDate today = LocalDate.now();
                    if (!today.isAfter(expiryDate)) {
                        if (expiryDate.minusDays(15).isBefore(today) || today.isEqual(expiryDate.minusDays(15))) {
                            System.out.println("[Notification] Software '" + sl.getName() + "' will expire on " + expiryDate);
                        }
                    }
                }
            }
        }
    }

    public void filterBooksByAuthor() {
        System.out.print("Enter Author Name to filter books: ");
        String author = scanner.nextLine();

        List<Asset> books = assetMap.get("Book");

        boolean found = false;
        for (Asset asset : books) {
            if (asset instanceof Book) {
                Book book = (Book) asset;
                if (book.getAuthor().equalsIgnoreCase(author) &&
                        (userSession.getUser().getRole().equals(RoleConstants.ADMIN)
                                || book.getCreatedBy().equals(userSession.getUser().getFirstName()))) {
                    System.out.println(book.getDetails() + " CreatedAt: " + book.getCreatedAt() + " UpdatedAt: " + book.getUpdatedAt());
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No books found by author: " + author);
        }
    }
}
