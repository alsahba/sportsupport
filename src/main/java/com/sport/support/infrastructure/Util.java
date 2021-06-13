package com.sport.support.infrastructure;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {

    public static String convertToFormattedMoney(BigDecimal money) {
        BigDecimal moneyWithFractionalPart = money.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return moneyWithFractionalPart.toPlainString();
    }
}
