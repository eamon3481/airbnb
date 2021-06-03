package com.team09.airbnb.response;

import java.math.BigDecimal;
import java.util.List;

public class HotelResponse extends HotelAbstract {

    private final String img;

    public HotelResponse(Long hotelId, String title, BigDecimal price, boolean wishlist, double latitude, double longitude, int rate, List<String> options, String img) {
        super(hotelId, title, price, wishlist, latitude, longitude, rate, options);
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public static Builder of(Long hotelId) {
        return new Builder(hotelId);
    }

    public static class Builder {
        private final Long hotelId;
        private double latitude;
        private double longitude;
        private String title = "";
        private BigDecimal price = BigDecimal.ZERO;
        private boolean wishlist;
        private int rate = 5;
        private List<String> options;
        private String img;

        public Builder(Long hotelId) {
            this.hotelId = hotelId;
        }

        public Builder latitude(double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder longitude(double longitude) {
            this.longitude = longitude;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder wishlist(boolean wishlist) {
            this.wishlist = this.wishlist;
            return this;
        }

        public Builder rate(int rate) {
            this.rate = rate;
            return this;
        }

        public Builder img(String img) {
            this.img = img;
            return this;
        }

        public Builder options(List<String> options) {
            this.options = options;
            return this;
        }

        public HotelResponse build() {
            return new HotelResponse(hotelId, title, price, wishlist, latitude, longitude, rate, options, img);
        }
    }
}

