package com.ptrufanov.data;

import com.ptrufanov.domain.PriceList;
import com.ptrufanov.domain.TrafficType;

import java.math.BigDecimal;
import java.util.Map;

import static com.ptrufanov.domain.TrafficType.MINUTE;
import static java.math.BigDecimal.valueOf;

public class PriceLists {

    public static final PriceList PRICE_LIST = createPriceList(valueOf(0.3), valueOf(0.2));

    private static PriceList createPriceList(BigDecimal smsPrice, BigDecimal minutePrice) {
        PriceList priceList = new PriceList();
        priceList.setPrices(Map.of(TrafficType.SMS, smsPrice, MINUTE, minutePrice));
        return priceList;
    }
}
