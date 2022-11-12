package com.ptrufanov.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PriceList {
    private Map<TrafficType, BigDecimal> prices = new HashMap<>();

    public Map<TrafficType, BigDecimal> getPrices() {
        return prices;
    }

    public void setPrices(Map<TrafficType, BigDecimal> prices) {
        this.prices = prices;
    }

    public BigDecimal getPrice(TrafficType trafficType) {
        return this.prices.get(trafficType);
    }
}
