package com.team09.airbnb.response;

public class ReserveResponse {
    private final Long reservedId;

    public ReserveResponse(Long reservedId){
        this.reservedId = reservedId;
    }

    public Long getReservedId() {
        return reservedId;
    }
}
