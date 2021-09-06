package com.team09.airbnb.response;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HotelDetailResponse extends HotelAbstract {

    private String host;
    private List<String> imgs;

    public HotelDetailResponse(Long hotelId, String title, BigDecimal price, boolean wishlist, double latitude, double longitude, int rate, String host, List<String> options, List<String> imgs) {
        super(hotelId, title, price, wishlist, latitude, longitude, rate, options);
        this.host = host;
        this.imgs = imgs;
    }

    public String getHost() {
        return host;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs){
        this.imgs = imgs;
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
        private String host = "Anonymous";
        private List<String> options = new ArrayList<>();
        private List<String> imgs = new ArrayList<>();

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

        public Builder host(String host){
            this.host = host;
            return this;
        }

        public Builder imgs(List<String> imgs) {
            this.imgs = imgs;
            return this;
        }

        public Builder option(List<String> options) {
            this.options = options;
            return this;
        }

        public HotelDetailResponse build() {
            return new HotelDetailResponse(hotelId, title, price, wishlist, latitude, longitude, rate, host, options, imgs);
        }

    }
}
