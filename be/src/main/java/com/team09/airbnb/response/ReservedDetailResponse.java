package com.team09.airbnb.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ReservedDetailResponse extends HotelDetailResponse {

    private final Long reservedId;
    private final Date checkin;
    private final Date checkout;
    private final int adults;
    private final int children;
    private final int infants;

    public ReservedDetailResponse(Long hotelId, String title, BigDecimal price, boolean wishlist, double longitude, double latitude, int rate, List<String> options, String host, List<String> imgs, Long reservedId, Date checkin, Date checkout, int adults, int children, int infants) {
        super(hotelId, title, price, wishlist, longitude, latitude, rate, options, host, imgs);
        this.reservedId = reservedId;
        this.checkin = checkin;
        this.checkout = checkout;
        this.adults = adults;
        this.children = children;
        this.infants = infants;
    }

    public Long getReservedId() {
        return reservedId;
    }

    public Date getCheckin() {
        return checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public int getAdults() {
        return adults;
    }

    public int getChildren() {
        return children;
    }

    public int getInfants() {
        return infants;
    }
}
