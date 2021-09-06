package com.team09.airbnb.controller;

import com.team09.airbnb.domain.login.User;
import com.team09.airbnb.response.ApiResponse;
import com.team09.airbnb.response.JWTResponse;
import com.team09.airbnb.service.OAuthIOSService;
import com.team09.airbnb.service.OAuthWebService;
import com.team09.airbnb.service.UserSerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    private final Logger logger = LoggerFactory.getLogger(OAuthController.class);

    private final OAuthIOSService oauthIOSService;
    private final OAuthWebService oauthWebService;
    private final UserSerivce userSerivce;

    public OAuthController(OAuthIOSService oauthIOSService, OAuthWebService oauthWebService, UserSerivce userSerivce) {
        this.oauthIOSService = oauthIOSService;
        this.oauthWebService = oauthWebService;
        this.userSerivce = userSerivce;
    }

    @GetMapping("/login")
    public ApiResponse jwtWebRequest(@RequestParam String code) {
        User user = oauthWebService.getUser(code);
        logger.info("User : {} ", user);
        return new ApiResponse(new JWTResponse(userSerivce.signIn(user), user));
    }

    @GetMapping("/iOS/login")
    public JWTResponse jwtIOSRequest(@RequestParam String code) {
        User user = oauthIOSService.getUser(code);
        logger.info("User : {} ", user);
        return new JWTResponse(userSerivce.signIn(user), user);
    }

}
