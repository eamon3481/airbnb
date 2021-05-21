package com.team09.airbnb.response;

import java.util.Date;

public class ReservedResponse extends ReserveAbstract {

    private final String hotelName;

    public ReservedResponse(Long hotelId, Date checkin, Date checkout, int adults, int children, int infants, String hotelName) {
        super(hotelId, checkin, checkout, adults, children, infants);
        this.hotelName = hotelName;
    }

    public String getHotelName() {
        return hotelName;
    }
}
