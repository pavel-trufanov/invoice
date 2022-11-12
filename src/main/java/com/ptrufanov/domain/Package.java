package com.ptrufanov.domain;

import java.math.BigDecimal;

public class Package {
    private Usage usage;
    private BigDecimal priceLimit;

    private String name;

    public int getAmount(TrafficType trafficType) {
        return this.usage.getUsages().getOrDefault(trafficType, 0);
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public BigDecimal getPriceLimit() {
        return priceLimit;
    }

    public void setPriceLimit(BigDecimal priceLimit) {
        this.priceLimit = priceLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
