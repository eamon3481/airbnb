package com.team09.airbnb.service;

import com.team09.airbnb.domain.hotel.HotelRepository;
import com.team09.airbnb.request.HotelRequest;
import com.team09.airbnb.request.PriceRequest;
import com.team09.airbnb.request.ReserveRequest;
import com.team09.airbnb.response.AllPriceResponse;
import com.team09.airbnb.response.HotelDetailResponse;
import com.team09.airbnb.response.HotelResponse;
import com.team09.airbnb.response.ReserveResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<HotelResponse> findAll(HotelRequest hotelRequest){
        return hotelRepository.findAll(hotelRequest);
    }

    public AllPriceResponse prices(PriceRequest priceRequest){
        List<BigDecimal> prices = hotelRepository.prices(priceRequest);
        Collections.sort(prices);

        return new AllPriceResponse(prices);
    }

    public HotelDetailResponse hotelDetail(Long hotelId, String username) {
        return hotelRepository.hotelDetail(hotelId, username);
    }

    public ReserveResponse reserveHotel(ReserveRequest reserveRequest){
        return hotelRepository.reserveHotel(reserveRequest);
    }
}
