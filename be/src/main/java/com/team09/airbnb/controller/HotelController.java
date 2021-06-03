package com.team09.airbnb.controller;

import com.team09.airbnb.request.HotelRequest;
import com.team09.airbnb.request.PriceRequest;
import com.team09.airbnb.response.ApiResponse;
import com.team09.airbnb.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/price")
    public ApiResponse prices(PriceRequest priceRequest) {
        return new ApiResponse(hotelService.prices(priceRequest));
    }


    @GetMapping("/hotels")
    public ApiResponse hotels(HotelRequest hotelRequest) {
        return new ApiResponse(hotelService.findAll(hotelRequest));
    }

//    @GetMapping("/hotels/{hotelId}")
//    public HotelDetailResponse hotelDetail(@PathVariable Long hotelId) {
//        return new HotelDetailResponse(hotelId, "C1 New apartment right next to Gangnam Station", BigDecimal.valueOf(79000), false, 37.498063, 127.030187, 4, Arrays.asList("주방", "무선인터넷", "에어컨", "헤어드라이"), "Baba", Arrays.asList("https://team09-airbnb.s3.ap-northeast-2.amazonaws.com/airbnb1-1.png", "https://team09-airbnb.s3.ap-northeast-2.amazonaws.com/airbnb1-2.png", "https://team09-airbnb.s3.ap-northeast-2.amazonaws.com/airbnb1-3.png"));
//    }

}
