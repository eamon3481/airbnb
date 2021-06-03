package com.team09.airbnb.controller;

import com.team09.airbnb.response.ApiResponse;
import com.team09.airbnb.service.PriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SearchController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PriceService priceService;

    public SearchController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/price")
    public ApiResponse prices(
            @RequestParam Date checkin,
            @RequestParam Date checkout,
            @RequestParam double latitude,
            @RequestParam double longitude) {
        logger.info("checkin : {}, checkout : {}, latitude : {}, longitude : {} ", checkin, checkout, latitude, longitude);
        return new ApiResponse(priceService.prices(latitude, longitude, checkin, checkout));
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
