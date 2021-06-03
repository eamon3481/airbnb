package com.team09.airbnb.request;

import java.math.BigDecimal;
import java.util.Date;

public class HotelRequest {

    private Date checkin;
    private Date checkout;
    private BigDecimal pricemax = BigDecimal.ZERO;
    private BigDecimal pricemin = BigDecimal.ZERO;
    private int adults;
    private int children;
    private int infants;
    private double latitude;
    private double longitude;

    public Date getCheckin() {
        return checkin;
    }

    public HotelRequest setCheckin(Date checkin) {
        this.checkin = checkin;
        return this;
    }

    public Date getCheckout() {
        return checkout;
    }

    public HotelRequest setCheckout(Date checkout) {
        this.checkout = checkout;
        return this;
    }

    public BigDecimal getPricemax() {
        return pricemax;
    }

    public HotelRequest setPricemax(BigDecimal pricemax) {
        this.pricemax = pricemax;
        return this;
    }

    public BigDecimal getPricemin() {
        return pricemin;
    }

    public HotelRequest setPricemin(BigDecimal pricemin) {
        this.pricemin = pricemin;
        return this;
    }

    public int getAdults() {
        return adults;
    }

    public HotelRequest setAdults(int adults) {
        this.adults = adults;
        return this;
    }

    public int getChildren() {
        return children;
    }

    public HotelRequest setChildren(int children) {
        this.children = children;
        return this;
    }

    public int getInfants() {
        return infants;
    }

    public HotelRequest setInfants(int infants) {
        this.infants = infants;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public HotelRequest setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public HotelRequest setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public int getOccupancy() {
        return this.adults + this.children + this.infants;
    }
}
