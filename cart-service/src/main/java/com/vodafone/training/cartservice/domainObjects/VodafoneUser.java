package com.vodafone.training.cartservice.domainObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VodafoneUser {
    private String userId;
    private String userName;
    private String userPassword;
    public List<String> roles;
    public String userEmail;
    public String userMobile;
}