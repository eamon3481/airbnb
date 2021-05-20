package com.team09.airbnb.response;

import java.util.Date;

public class ReservedResponse {

    private final Long reservedId;
    private final String hotelName;
    private final Date checkin;
    private final Date checkout;
    private final int adults;
    private final int children;
    private final int infants;

    public ReservedResponse(Long reservedId, String hotelName, Date checkin, Date checkout, int adults, int children, int infants) {
        this.reservedId = reservedId;
        this.hotelName = hotelName;
        this.checkin = checkin;
        this.checkout = checkout;
        this.adults = adults;
        this.children = children;
        this.infants = infants;
    }

    public Long getReservedId() {
        return reservedId;
    }

    public String getHotelName() {
        return hotelName;
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
