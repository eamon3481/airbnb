package com.team09.airbnb.request;

import com.team09.airbnb.response.ReserveAbstract;

import java.util.Date;

public class ReserveRequest extends ReserveAbstract {

    public ReserveRequest(Long hotelId, Date checkin, Date checkout, int adults, int children, int infants) {
        super(hotelId, checkin, checkout, adults, children, infants);
    }

}
