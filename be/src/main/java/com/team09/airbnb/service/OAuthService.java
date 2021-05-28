package com.team09.airbnb.service;

import com.team09.airbnb.domain.login.User;
import com.team09.airbnb.request.AccessTokenRequest;
import com.team09.airbnb.request.AccessTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

public abstract class OAuthService {

    private final Logger logger = LoggerFactory.getLogger(OAuthService.class);

    private final String GITHUB_ACCESS_TOKEN_URI = "https://github.com/login/oauth/access_token";
    private final String GITHUB_USER_URI = "https://api.github.com/user";

    public User getUser(String code) {
        AccessTokenResponse accessToken = getAccessToken(code);
        logger.info("Access Token : {} ", accessToken);
        return getUserFromGitHub(accessToken);
    }

    public User getUserFromGitHub(AccessTokenResponse accessToken) {
        RequestEntity<Void> githubUserRequest = RequestEntity
                .get(GITHUB_USER_URI)
                .header("Accept", "application/json")
                .header("Authorization", "token " + accessToken.getAccessToken())
                .build();

        return new RestTemplate()
                .exchange(githubUserRequest, User.class).getBody();
    }

    public AccessTokenResponse getAccessToken(String code) {
        RequestEntity<AccessTokenRequest> accessTokenRequest = RequestEntity
                .post(GITHUB_ACCESS_TOKEN_URI)
                .header("Accept", "application/json")
                .body(accessTokenRequest(code));

        return new RestTemplate()
                .exchange(accessTokenRequest, AccessTokenResponse.class).getBody();

    }

    public abstract AccessTokenRequest accessTokenRequest(String code);

}
