package com.team09.airbnb.service;

import com.team09.airbnb.request.AccessTokenRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class OAuthWebService extends OAuthService{

    private final Logger logger = LoggerFactory.getLogger(OAuthWebService.class);

    private final String CLIENT_ID;
    private final String CLIENT_SECRET;

    public OAuthWebService(Environment environment) {
        this.CLIENT_ID = environment.getProperty("github.client.id");
        this.CLIENT_SECRET = environment.getProperty("github.client.secret");
    }

    @Override
    public AccessTokenRequest accessTokenRequest(String code) {
        logger.debug("client id : {}", this.CLIENT_ID);
        logger.debug("client secret : {}", this.CLIENT_SECRET);
        logger.debug("code : {}", code);
        return AccessTokenRequest.of(CLIENT_ID, CLIENT_SECRET, code);
    }

}
