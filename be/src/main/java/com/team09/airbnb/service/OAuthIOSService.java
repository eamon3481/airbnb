package com.team09.airbnb.service;

import com.team09.airbnb.request.AccessTokenRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class OAuthIOSService extends OAuthService {

    private final Logger logger = LoggerFactory.getLogger(OAuthIOSService.class);

    private final String CLIENT_ID;
    private final String CLIENT_SECRET;

    public OAuthIOSService(Environment environment) {
        this.CLIENT_ID = environment.getProperty("github.ios-client.id");
        this.CLIENT_SECRET = environment.getProperty("github.ios-client.secret");
    }

    @Override
    public AccessTokenRequest accessTokenRequest(String code) {
        logger.debug("client id : {}", this.CLIENT_ID);
        logger.debug("client secret : {}", this.CLIENT_SECRET);
        logger.debug("code : {}", code);
        return AccessTokenRequest.of(CLIENT_ID, CLIENT_SECRET, code);
    }

}
