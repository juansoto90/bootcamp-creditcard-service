package com.nttdata.creditcard.util;

import java.time.LocalDateTime;
import java.util.Random;

public class Generator {
    private static Random random = new Random(System.currentTimeMillis());

    public static String generateCreditCardNumber() {
        String bin = "4556";

        StringBuilder b = new StringBuilder(bin);
        for (int i = 0; i <= 11; i++) {
            int number = random.nextInt(10);
            if(i % 4 == 0) b.append("-");
            b.append(number);
        }
        return b.toString();
    }

    public static String generateCVV() {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i <= 2; i++) {
            int number = random.nextInt(10);
            b.append(number);
        }
        return b.toString();
    }

    public static int generateMonth() {
        return LocalDateTime.now().getMonthValue();
    }

    public static int generateYear() {
        return LocalDateTime.now().getYear() + 5;
    }
}
