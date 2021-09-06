package com.team09.airbnb.response;

import java.time.LocalDate;

public abstract class ReserveAbstract {

    private Long hotelId;
    private LocalDate checkin;
    private LocalDate checkout;
    private int adults;
    private int children;
    private int infants;

    public ReserveAbstract(Long hotelId, LocalDate checkin, LocalDate checkout, int adults, int children, int infants) {
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

    public LocalDate getCheckin() {
        return checkin;
    }

    public LocalDate getCheckout() {
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

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public void setInfants(int infants) {
        this.infants = infants;
    }

    public long getDays(){
        return 5L;
    }
}
