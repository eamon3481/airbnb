package com.team09.airbnb.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.team09.airbnb.exception.OAuthException;
import com.team09.airbnb.service.OAuthService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationInterceptor implements HandlerInterceptor {

    private static final String JWT_ISSUER = "codesquad-team09";

    private OAuthService oauthService;

    public JWTAuthorizationInterceptor(OAuthService oauthService) {
        this.oauthService = oauthService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        final String token = request.getHeader("Authorization");


        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(JWT_ISSUER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
        } catch (JWTVerificationException exception){
            throw new OAuthException("Invalid signature / claims");
        }
        return true;
    }
}
