package com.team09.airbnb.service;

import com.team09.airbnb.domain.hotel.HotelRepository;
import com.team09.airbnb.request.HotelRequest;
import com.team09.airbnb.response.HotelResponse;
import org.springframework.stereotype.Service;

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
}
