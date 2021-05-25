package com.team09.airbnb.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

public class AccessTokenRequest {

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @Nullable
    @JsonProperty("redirect_uri")
    private String redirectURL;

    @Nullable
    private String state;

    private String code;

    public AccessTokenRequest(String clientId, String clientSecret, String code) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
    }

    @Nullable
    public String getRedirectURL() {
        return redirectURL;
    }

    @Nullable
    public String getState() {
        return state;
    }

    public String getCode() {
        return code;
    }

    public static AccessTokenRequest of(String clientId, String clientSecret, String code) {
        return new AccessTokenRequest(clientId, clientSecret, code);
    }

}
