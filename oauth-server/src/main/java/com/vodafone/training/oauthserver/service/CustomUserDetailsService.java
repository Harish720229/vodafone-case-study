package com.vodafone.training.oauthserver.service;

import com.vodafone.training.oauthserver.entities.VodafoneUser;
import com.vodafone.training.oauthserver.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Service
@Order(HIGHEST_PRECEDENCE)
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        VodafoneUser vodafoneUser = userRepo.findByUserName(username);
        log.info("User found: {}", vodafoneUser.getUserId());
        if (vodafoneUser == null) {
            throw new UsernameNotFoundException("User not found");
        } else {
            log.info("User found: {}", vodafoneUser.toString());
            UserDetails userBuilt = User.withUsername(vodafoneUser.getUserName())
                    .password(vodafoneUser.getUserPassword())
                    .roles(vodafoneUser.getRolesDetails())
                    .authorities(vodafoneUser.getRolesDetails())
                    .build();
            log.info("User built: {}", userBuilt.toString());
            return userBuilt;
        }
    }

}
