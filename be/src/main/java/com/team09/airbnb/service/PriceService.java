package com.team09.airbnb.service;

import com.team09.airbnb.domain.hotel.PriceRepository;
import com.team09.airbnb.response.AllPriceResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PriceService {

    private PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public AllPriceResponse prices(double latitude, double longitude, Date checkin, Date checkout){
        List<BigDecimal> prices = priceRepository.prices(latitude, longitude, checkin, checkout);
        Collections.sort(prices);

        return new AllPriceResponse(prices);
    }
}
