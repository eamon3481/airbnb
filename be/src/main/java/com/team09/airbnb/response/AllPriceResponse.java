package com.team09.airbnb.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AllPriceResponse {

    private final List<BigDecimal> prices;

    @JsonProperty("min_price")
    private BigDecimal minPrice = BigDecimal.ZERO;

    @JsonProperty("max_price")
    private BigDecimal maxPrice = BigDecimal.ZERO;

    @JsonProperty("avg_price")
    private BigDecimal avgPrice = BigDecimal.ZERO;

    public AllPriceResponse(List<BigDecimal> prices) {
        this.prices = prices;
        setMinPrice();
        setMaxPrice();
        setAvgPrice();
    }

    private void setAvgPrice() {
        if (!prices.isEmpty()) {
            BigDecimal sum = prices.stream()
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            this.avgPrice = sum.divide(BigDecimal.valueOf(prices.size()), RoundingMode.HALF_UP);
        }
    }

    private void setMinPrice() {
        if (!prices.isEmpty()) {
            this.minPrice = prices.get(0);
        }
    }

    private void setMaxPrice() {
        if (!prices.isEmpty()) {
            this.maxPrice = prices.get(prices.size() - 1);
        }
    }

    public List<BigDecimal> getPrices() {
        return prices;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }
}
