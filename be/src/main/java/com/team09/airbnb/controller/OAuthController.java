package com.team09.airbnb.controller;

import com.team09.airbnb.service.OAuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    private final OAuthService oauthService;

    public OAuthController(OAuthService oauthService) {
        this.oauthService = oauthService;
    }

    @GetMapping("/login")
    public String jwtRequest(@RequestParam String code) {
        return oauthService.createJWT(code);
    }

}
