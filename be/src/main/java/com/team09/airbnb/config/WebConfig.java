package com.team09.airbnb.config;

import com.team09.airbnb.interceptor.JWTAuthorizationInterceptor;
import com.team09.airbnb.service.OAuthService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private OAuthService oauthService;

    public WebConfig(OAuthService oauthService) {
        this.oauthService = oauthService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTAuthorizationInterceptor(oauthService))
                .addPathPatterns("/**")
                .excludePathPatterns("/oauth/**");

    }
}
