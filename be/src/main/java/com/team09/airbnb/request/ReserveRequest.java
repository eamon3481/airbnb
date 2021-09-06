package com.team09.airbnb.request;

import com.team09.airbnb.response.ReserveAbstract;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReserveRequest extends ReserveAbstract {

    private String username;

    public ReserveRequest(Long hotelId, LocalDate checkin, LocalDate checkout, int adults, int children, int infants, String username) {
        super(hotelId, checkin, checkout, adults, children, infants);
        this.username = username;
    }

    public long getDays() {
        return ChronoUnit.DAYS.between(getCheckin(), getCheckout());
    }

    public String getUsername(){
        return username;
    }

}
