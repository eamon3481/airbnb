package com.team09.airbnb.controller;

import com.team09.airbnb.response.HotelDetailResponse;
import com.team09.airbnb.response.PriceResponse;
import com.team09.airbnb.response.HotelListResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

    @GetMapping("/hotels")
    public List<HotelListResponse> hotels(
            @RequestParam Date checkin,
            @RequestParam Date checkout,
            @RequestParam int pricemax,
            @RequestParam int pricemin,
            @RequestParam(defaultValue = "0") int adults,
            @RequestParam(defaultValue = "0") int children,
            @RequestParam(defaultValue = "0") int infants,
            @RequestParam(required = false) String location) {
        HotelListResponse response1 = new HotelListResponse(1L, "C1 New apartment right next to Gangnam Station", BigDecimal.valueOf(79000), false, 37.498063, 127.030187, 4, Arrays.asList("주방", "무선인터넷", "에어컨", "헤어드라이"), "https://team09-airbnb.s3.ap-northeast-2.amazonaws.com/airbnb1.png");
        HotelListResponse response2 = new HotelListResponse(2L, "Specious Central Itaewon – NEWTRO House", BigDecimal.valueOf(162000), true, 37.529388, 126.993279, 5, Arrays.asList("주방", "에어컨", "헤어드라이기"), "https://team09-airbnb.s3.ap-northeast-2.amazonaws.com/airbnb2.png");

        return Arrays.asList(response1, response2);
    }

    @GetMapping("/hotels/{hotelId}")
    public HotelDetailResponse hotelDetail(@PathVariable Long hotelId) {
        return new HotelDetailResponse(hotelId, "C1 New apartment right next to Gangnam Station", BigDecimal.valueOf(79000), false, 37.498063, 127.030187, 4, Arrays.asList("주방", "무선인터넷", "에어컨", "헤어드라이"), "Baba", Arrays.asList("https://team09-airbnb.s3.ap-northeast-2.amazonaws.com/airbnb1-1.png", "https://team09-airbnb.s3.ap-northeast-2.amazonaws.com/airbnb1-2.png", "https://team09-airbnb.s3.ap-northeast-2.amazonaws.com/airbnb1-3.png"));
    }

}
