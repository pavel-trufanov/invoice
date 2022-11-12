package com.ptrufanov.utils;

import com.ptrufanov.domain.Usage;

import java.util.Map;

import static com.ptrufanov.domain.TrafficType.MINUTE;
import static com.ptrufanov.domain.TrafficType.SMS;

public class UsageUtils {

    public static Usage createUsage(int minutesAmount, int smsAmount) {
        Usage usage = new Usage();
        usage.setUsages(Map.of(MINUTE, minutesAmount, SMS, smsAmount));
        return usage;
    }
}
