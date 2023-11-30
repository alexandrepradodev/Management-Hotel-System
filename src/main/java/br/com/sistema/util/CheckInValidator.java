package br.com.sistema.util;

import java.time.LocalDate;

public class CheckInValidator {

    public static boolean isCheckInWithinOneYear(LocalDate checkIn) {
        LocalDate currentDate = LocalDate.now();
        LocalDate maxCheckInDate = currentDate.plusYears(1);
        return checkIn.isAfter(maxCheckInDate);
    }
}
