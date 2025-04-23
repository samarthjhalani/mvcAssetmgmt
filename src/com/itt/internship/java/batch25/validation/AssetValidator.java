package com.itt.internship.java.batch25.validation;

import com.itt.internship.java.batch25.entity.Asset;

public class AssetValidator {
    public static boolean isValidAsset(Asset asset) {
        return asset.getName() != null && !asset.getName().isEmpty();
    }
}
