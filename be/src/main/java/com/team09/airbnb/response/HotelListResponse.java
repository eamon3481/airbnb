package com.team09.airbnb.response;

import java.math.BigDecimal;
import java.util.List;

public class HotelListResponse extends HotelAbstract {

    private final String img;

    public HotelListResponse(Long hotelId, String title, BigDecimal price, boolean wishlist, double latitude, double longitude, int rate, List<String> details, String img) {
        super(hotelId, title, price, wishlist, latitude, longitude, rate, details);
        this.img = img;
    }

    public String getImg() {
        return img;
    }
}
