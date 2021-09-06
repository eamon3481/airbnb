package com.team09.airbnb.controller;

import com.team09.airbnb.request.HotelRequest;
import com.team09.airbnb.request.PriceRequest;
import com.team09.airbnb.response.ApiResponse;
import com.team09.airbnb.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/hotels/{hotelId}")
    public ApiResponse hotelDetail(@PathVariable Long hotelId) {
        return new ApiResponse(hotelService.hotelDetail(hotelId, "Woody"));
    }

}
