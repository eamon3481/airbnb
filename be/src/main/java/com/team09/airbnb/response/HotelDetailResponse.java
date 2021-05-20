package com.team09.airbnb.response;

import java.math.BigDecimal;
import java.util.List;

public class HotelDetailResponse extends HotelAbstract {

    private final String host;
    private final List<String> imgs;

    public HotelDetailResponse(Long hotelId, String title, BigDecimal price, boolean wishlist, double longitude, double latitude, int rate, List<String> details, String host, List<String> imgs) {
        super(hotelId, title, price, wishlist, longitude, latitude, rate, details);
        this.host = host;
        this.imgs = imgs;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public String getHost() {
        return host;
    }
}
