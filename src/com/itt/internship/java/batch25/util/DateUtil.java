package com.itt.internship.java.batch25.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr, formatter);
    }
}
