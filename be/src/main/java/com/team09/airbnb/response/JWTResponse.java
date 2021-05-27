package com.team09.airbnb.response;

import com.team09.airbnb.domain.login.OAuthUser;

public class JWTResponse {
    private final String jwt;
    private final OAuthUser user;

    public JWTResponse(String jwt, OAuthUser user) {
        this.jwt = jwt;
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public OAuthUser getUser() {
        return user;
    }

}
