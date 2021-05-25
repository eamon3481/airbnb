package com.team09.airbnb.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.team09.airbnb.domain.login.OAuthUser;
import com.team09.airbnb.exception.OAuthException;

import java.sql.Date;

public class JWTBuilder {

    private static final String JWT_ISSUER = "codesquad-team09";

    public static String create(OAuthUser OAuthUser) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withIssuer(JWT_ISSUER)
                    .withClaim("login", OAuthUser.getLogin())
                    .withClaim("name", OAuthUser.getName())
                    .withIssuedAt(new Date(System.currentTimeMillis()))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new OAuthException("Invalid Signing configuration, cannot issue JWT user credentials");
        }
    }
}
