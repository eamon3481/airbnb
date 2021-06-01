package com.team09.airbnb.response;

import java.math.BigDecimal;
import java.util.List;

abstract class HotelAbstract {
    private final Long hotelId;
    private final String title;
    private final BigDecimal price;
    private final boolean wishlist;
    private final double latitude;
    private final double longitude;
    private final int rate;
    private final List<String> details;

    public HotelAbstract(Long hotelId, String title, BigDecimal price, boolean wishlist, double latitude, double longitude, int rate, List<String> details) {
        this.hotelId = hotelId;
        this.title = title;
        this.price = price;
        this.wishlist = wishlist;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rate = rate;
        this.details = details;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isWishlist() {
        return wishlist;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public int getRate() {
        return rate;
    }

    public List<String> getDetails() {
        return details;
    }
}
