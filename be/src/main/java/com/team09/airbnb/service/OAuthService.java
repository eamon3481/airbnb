package com.team09.airbnb.service;

import com.team09.airbnb.domain.login.User;
import com.team09.airbnb.request.AccessTokenRequest;
import com.team09.airbnb.request.AccessTokenResponse;
import com.team09.airbnb.response.JWTResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OAuthService {

    private final Logger logger = LoggerFactory.getLogger(OAuthService.class);

    private final String GITHUB_ACCESS_TOKEN_URI = "https://github.com/login/oauth/access_token";
    private final String GITHUB_USER_URI = "https://api.github.com/user";

    private final String CLIENT_ID;
    private final String CLIENT_SECRET;

    public OAuthService(Environment environment) {
        this.CLIENT_ID = environment.getProperty("github.client.id");
        this.CLIENT_SECRET = environment.getProperty("github.client.secret");
    }

    public JWTResponse createJWT(String code) {
        AccessTokenResponse accessToken = getAccessToken(code);
        logger.info("Access Token : {} ", accessToken);
        User User = getGithubUser(accessToken);
        logger.info("User : {} ", User);
        return new JWTResponse(JWTBuilder.create(User), User);
    }

    public User getGithubUser(AccessTokenResponse accessToken) {
        RequestEntity<Void> githubUserRequest = RequestEntity
                .get(GITHUB_USER_URI)
                .header("Accept", "application/json")
                .header("Authorization", "token " + accessToken.getAccessToken())
                .build();

        return new RestTemplate()
                .exchange(githubUserRequest, User.class).getBody();
    }

    public AccessTokenResponse getAccessToken(String code) {
        logger.debug("client id : {}", this.CLIENT_ID);
        logger.debug("client secret : {}", this.CLIENT_SECRET);
        logger.debug("code : {}", code);

        RequestEntity<AccessTokenRequest> accessTokenRequest = RequestEntity
                .post(GITHUB_ACCESS_TOKEN_URI)
                .header("Accept", "application/json")
                .body(AccessTokenRequest.of(CLIENT_ID, CLIENT_SECRET, code));

       return new RestTemplate()
                .exchange(accessTokenRequest, AccessTokenResponse.class).getBody();

    }


}
