package com.vodafone.training.cartservice.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

@Configuration
@Slf4j
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestTokenBearerInterceptor() {


        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                Jwt jwt = (Jwt) authentication.getPrincipal();
                String tokenString = jwt.getTokenValue();
                log.info("jwtToken to be added to the header : " + tokenString);
                requestTemplate.header("Authorization", "Bearer " + tokenString);
            }
        };
    }
}
