package com.team09.airbnb.controller;

import com.team09.airbnb.response.PriceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@RestController
public class SearchController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/price")
    public Map<BigDecimal, Integer> price(
            @RequestParam Date checkin,
            @RequestParam Date checkout,
            @RequestParam int fraction,
            @RequestParam(required = false) String location) {
        logger.info("checkin : {}, checkout : {}, fraction : {}, location : {}", checkin, checkout, fraction, location);

        BigDecimal minPrice = BigDecimal.valueOf(50000);
        BigDecimal maxPrice = BigDecimal.valueOf(300000);

        return PriceResponse.of(minPrice, maxPrice, fraction).getPrices();
    }

}
