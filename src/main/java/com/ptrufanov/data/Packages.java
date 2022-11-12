package com.ptrufanov.data;

import com.ptrufanov.domain.Package;
import com.ptrufanov.domain.Usage;

import java.math.BigDecimal;
import java.util.Map;

import static com.ptrufanov.domain.TrafficType.MINUTE;
import static com.ptrufanov.domain.TrafficType.SMS;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.valueOf;

public class Packages {

    public static final Package S_PACKAGE = createPackage(10, 50, valueOf(5), "S package");
    public static final Package M_PACKAGE = createPackage(50, 100, TEN, "M package");
    public static final Package L_PACKAGE = createPackage(500, 500, valueOf(10), "L package");

    private static Package createPackage(int minutesAmount, int smsAmount, BigDecimal priceLimit, String name) {
        Package aPackage = new Package();
        Usage usage = new Usage();
        usage.setUsages(Map.of(SMS, smsAmount, MINUTE, minutesAmount));
        aPackage.setUsage(usage);
        aPackage.setPriceLimit(priceLimit);
        aPackage.setName(name);
        return aPackage;
    }
}
