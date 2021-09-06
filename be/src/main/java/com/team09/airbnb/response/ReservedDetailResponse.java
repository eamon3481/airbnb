package com.team09.airbnb.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReservedDetailResponse extends HotelDetailResponse {

    private final Long reservedId;
    private final LocalDate checkin;
    private final LocalDate checkout;
    private final int adults;
    private final int children;
    private final int infants;

    protected ReservedDetailResponse(Long hotelId, String title, BigDecimal price, boolean wishlist, double longitude, double latitude, int rate, List<String> options, String host, List<String> imgs, Long reservedId, LocalDate checkin, LocalDate checkout, int adults, int children, int infants) {
        super(hotelId, title, price, wishlist, longitude, latitude, rate, host, options, imgs);
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

    public static ReservedDetailResponse create(Long hotelId, String title, BigDecimal price, boolean wishlist, double longitude, double latitude, int rate, List<String> options, String host, List<String> imgs, Long reservedId, LocalDate checkin, LocalDate checkout, int adults, int children, int infants){
        return new ReservedDetailResponse(hotelId, title, price, wishlist, longitude, latitude, rate, options, host, imgs, reservedId, checkin, checkout, adults, children, infants);
    }
}
