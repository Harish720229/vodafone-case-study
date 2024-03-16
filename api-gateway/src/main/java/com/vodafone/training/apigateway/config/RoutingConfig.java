package com.vodafone.training.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class RoutingConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return  builder.routes().route(r->r.path("/products/**") .filters(GatewayFilterSpec::tokenRelay).uri("lb://products-service")).
                route(r->r.path("/cart/**","/orders/**","/create-table/**").filters(GatewayFilterSpec::tokenRelay).uri("lb://cart-service")).
                build();
    }


}
