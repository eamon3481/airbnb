package com.team09.airbnb.request;

import java.util.Date;

public class PriceRequest {

    private Date checkin;
    private Date checkout;
    private double latitude;
    private double longitude;

    public Date getCheckin() {
        return checkin;
    }

    public PriceRequest setCheckin(Date checkin) {
        this.checkin = checkin;
        return this;
    }

    public Date getCheckout() {
        return checkout;
    }

    public PriceRequest setCheckout(Date checkout) {
        this.checkout = checkout;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public PriceRequest setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public PriceRequest setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }
}
