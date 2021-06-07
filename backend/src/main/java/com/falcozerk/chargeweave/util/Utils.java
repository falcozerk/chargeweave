package com.falcozerk.chargeweave.util;

public class Utils {
    public static void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new RuntimeException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new RuntimeException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }
}
