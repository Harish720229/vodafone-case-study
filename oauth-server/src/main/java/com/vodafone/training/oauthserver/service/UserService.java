package com.vodafone.training.oauthserver.service;

import com.vodafone.training.oauthserver.entities.VodafoneUser;
import com.vodafone.training.oauthserver.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo vodafoneUserRepo;

    public VodafoneUser saveUser(VodafoneUser vodafoneUser){
        log.info("Adding user {}",vodafoneUser.toString());
        return vodafoneUserRepo.save(vodafoneUser);
    }

    public VodafoneUser getUser(String userId){
        return vodafoneUserRepo.findById(userId).orElse(null);
    }

    public Iterable<VodafoneUser> addAllUsers(Iterable<VodafoneUser> vodafoneUsers){
        log.info("Adding all users {}",vodafoneUsers.toString());
        return vodafoneUserRepo.saveAll(vodafoneUsers);
    }

}
