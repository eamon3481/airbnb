package com.team09.airbnb.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PriceResponse {

    private static final int DEFAULT_FRACTION = 10;

    private final Map<BigDecimal, Integer> prices;

    @JsonIgnore
    private final BigDecimal minPrice;

    @JsonIgnore
    private final BigDecimal maxPrice;

    @JsonIgnore
    private final int fraction;

    private PriceResponse(BigDecimal minPrice, BigDecimal maxPrice, int fraction) {
        this.prices = new HashMap();
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.fraction = fraction;
    }

    public Map<BigDecimal, Integer> getPrices() {
        BigDecimal price = minPrice;
        BigDecimal unit = maxPrice.subtract(minPrice).divide(BigDecimal.valueOf(fraction));
        for (int i = 0; i < fraction; price.add(unit), i++) {
            prices.put(price, i + 1);
        }
        return prices;
    }

    public static PriceResponse of(BigDecimal minPrice, BigDecimal maxPrice, int fraction){
        return new PriceResponse(minPrice, maxPrice, fraction);
    }

    public static PriceResponse of(BigDecimal minPrice, BigDecimal maxPrice){
        return new PriceResponse(minPrice, maxPrice, DEFAULT_FRACTION);
    }
}
