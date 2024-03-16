package com.vodafone.training.cartservice.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;


@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf->csrf.disable())
                .cors(cors->cors.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests.anyRequest().hasAuthority("CUSTOMER")
                )
                .oauth2ResourceServer(oauth -> oauth.jwt(jwt -> {
                    //jwt.decoder(jwtDecoder());
                    jwt.jwtAuthenticationConverter(new JwtCustomAuthenticationConverter());
                }));

        return http.build();
    }

//    @Bean
//    public JwtDecoder jwtDecoder() {
//        JwtDecoder jwtDecoder = NimbusJwtDecoder.withIssuerLocation(issuerUri).build();
//        return new JwtDecoder() {
//            @Override
//            public Jwt decode(String token) throws JwtException {
//                log.info("token received : " + token);
//                Jwt jwt = jwtDecoder.decode(token);
//                log.info("jwt received : " + jwt);
//                return jwt;
//            }
//        };
//    }
}
