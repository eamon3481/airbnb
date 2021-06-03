package com.team09.airbnb.service;

import com.team09.airbnb.domain.hotel.PriceRepository;
import com.team09.airbnb.request.PriceRequest;
import com.team09.airbnb.response.AllPriceResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class PriceService {

    private PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public AllPriceResponse prices(PriceRequest priceRequest){
        List<BigDecimal> prices = priceRepository.prices(priceRequest);
        Collections.sort(prices);

        return new AllPriceResponse(prices);
    }
}
