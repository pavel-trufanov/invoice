package com.ptrufanov.domain;

import java.util.HashMap;
import java.util.Map;

public class Usage {

    private Map<TrafficType, Integer> usages = new HashMap<>();

    public Map<TrafficType, Integer> getUsages() {
        return usages;
    }

    public void setUsages(Map<TrafficType, Integer> usages) {
        this.usages = usages;
    }
}
