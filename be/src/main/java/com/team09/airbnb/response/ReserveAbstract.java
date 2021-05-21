package com.team09.airbnb.response;

import java.util.Date;

public abstract class ReserveAbstract {

    private Long hotelId;
    private Date checkin;
    private Date checkout;
    private int adults;
    private int children;
    private int infants;

    public ReserveAbstract(Long hotelId, Date checkin, Date checkout, int adults, int children, int infants) {
        this.hotelId = hotelId;
        this.checkin = checkin;
        this.checkout = checkout;
        this.adults = adults;
        this.children = children;
        this.infants = infants;
    }

    public Long getHotelId() {
        return hotelId;
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
