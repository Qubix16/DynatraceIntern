package com.example.demo;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateValidator {
    public static boolean isValidDate(String dateString) {
        LocalDate currentDate = LocalDate.now();
        LocalDate date = LocalDate.parse(dateString);
        return date.isBefore(currentDate);
    }
}
