package com.team09.airbnb.response;

import com.team09.airbnb.domain.login.User;

public class JWTResponse {
    private final String jwt;
    private final User user;

    public JWTResponse(String jwt, User user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public User getUser() {
        return user;
    }

}
