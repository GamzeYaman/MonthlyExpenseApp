package com.app.consumer.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DebtUtils {

    private static BigDecimal min;
    private static BigDecimal max;

    static {
        min = BigDecimal.ONE;
    }

    public static ObjectMapper getObjMapInstance() {
        return new ObjectMapper();
    }

    public static BigDecimal getRandomDebtAmountForCredit() {
        max = BigDecimal.valueOf(20000);
        return getRandomAmount(max);
    }

    public static BigDecimal getRandomDebtAmountForUtilityBills() {
        max = BigDecimal.valueOf(4000);
        return getRandomAmount(max);
    }

    public static BigDecimal getRandomAmountForPhoneBills() {
        max = BigDecimal.valueOf(1000);
        return getRandomAmount(max);
    }

    private static BigDecimal getRandomAmount(BigDecimal maxValue) {
        return min.add(maxValue.subtract(min).multiply(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble()))).setScale(2, RoundingMode.HALF_UP);
    }
}