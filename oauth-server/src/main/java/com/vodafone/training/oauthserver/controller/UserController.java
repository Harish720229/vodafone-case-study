package com.vodafone.training.oauthserver.controller;

import com.vodafone.training.oauthserver.entities.VodafoneUser;
import com.vodafone.training.oauthserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;
    @RequestMapping("/add")
    public VodafoneUser addUser(@RequestBody VodafoneUser user) {
        log.info("User to be added: {}", user);
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        return  userService.saveUser(user);
    }

    @RequestMapping("/get/{userId}")
    public VodafoneUser getUser(@PathVariable String userId) {
        return userService.getUser(userId);
    }

    @PostMapping("/add-all")
    public Iterable<VodafoneUser> addAllUsers(@RequestBody Iterable<VodafoneUser> users) {
        log.info("Users to be added: {}", users);
        for(VodafoneUser user: users){
            user.setUserPassword(encoder.encode(user.getUserPassword()));
        }
        return userService.addAllUsers(users);
    }
}
