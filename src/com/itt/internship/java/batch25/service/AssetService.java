package com.itt.internship.java.batch25.service;

import com.itt.internship.java.batch25.entity.Asset;
import com.itt.internship.java.batch25.entity.Book;
import com.itt.internship.java.batch25.entity.Software;
import com.itt.internship.java.batch25.entity.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AssetService {
    private final List<Asset> assetList = new ArrayList<>();
    private final User currentUser;

    public AssetService(User currentUser) {
        this.currentUser = currentUser;
    }

    public void addAsset(Asset asset) {
        if (!isAuthorized("admin", "manager")) return;
        assetList.add(asset);
        System.out.println("Asset added successfully: " + asset.getName());
    }

    public Asset searchAsset(int serialNumber) {
        for (Asset asset : assetList) {
            if (asset.getSerialNumber() == serialNumber) {
                return asset;
            }
        }
        return null;
    }

    public boolean updateAsset(int serialNumber, Asset updatedAsset) {
        if (!isAuthorized("admin", "manager")) return false;

        for (int i = 0; i < assetList.size(); i++) {
            if (assetList.get(i).getSerialNumber() == serialNumber) {
                assetList.set(i, updatedAsset);
                System.out.println("Asset updated successfully.");
                return true;
            }
        }
        System.out.println("Asset not found for update.");
        return false;
    }

    public boolean deleteAsset(int serialNumber) {
        if (!isAuthorized("admin")) return false;

        Iterator<Asset> iterator = assetList.iterator();
        while (iterator.hasNext()) {
            Asset asset = iterator.next();
            if (asset.getSerialNumber() == serialNumber) {
                iterator.remove();
                System.out.println("Asset deleted successfully.");
                return true;
            }
        }
        System.out.println("Asset not found for deletion.");
        return false;
    }

    public void listAssets() {
        if (assetList.isEmpty()) {
            System.out.println("No assets available.");
        } else {
            for (Asset asset : assetList) {
                asset.display();
                System.out.println("---------------------------");
            }
        }
    }

    public List<Asset> getAllAssets() {
        return assetList;
    }

    private boolean isAuthorized(String... allowedRoles) {
        for (String role : allowedRoles) {
            if (currentUser.getRole().equalsIgnoreCase(role)) {
                return true;
            }
        }
        System.out.println("Access Denied: " + currentUser.getRole() + " is not allowed to perform this action.");
        return false;
    }

    public void filterBooksByAuthor(String authorName) {
        boolean found = false;
        for (Asset asset : assetList) {
            if (asset instanceof Book) {
                Book book = (Book) asset;
                if (book.getAuthor().equalsIgnoreCase(authorName)) {
                    book.display();
                    System.out.println("---------------------------");
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No books found by author: " + authorName);
        }
    }


    public void checkForExpiringAssets() {
        LocalDate today = LocalDate.now();

        for (Asset asset : assetList) {
            if (asset instanceof Software) {
                Software software = (Software) asset;
                LocalDate expiryDate = software.getExpiryDateAsLocalDate();
                long daysLeft = ChronoUnit.DAYS.between(today, expiryDate);

                if (daysLeft <= 15 && daysLeft >= 0) {
                    System.out.println("⚠️ ALERT: Software '" + software.getName() + "' (Serial No: "
                            + software.getSerialNumber() + ") will expire in " + daysLeft + " day(s) [Expiry: " + software.getExpiryDate() + "]");
                } else if (daysLeft < 0) {
                    System.out.println("❌ WARNING: Software '" + software.getName() + "' (Serial No: "
                            + software.getSerialNumber() + ") has already expired on " + software.getExpiryDate());
                }
            }
        }
    }
}
