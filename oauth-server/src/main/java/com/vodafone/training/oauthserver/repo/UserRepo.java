package com.vodafone.training.oauthserver.repo;

import com.vodafone.training.oauthserver.entities.VodafoneUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<VodafoneUser, String> {
    VodafoneUser findByUserName(String username);
}
