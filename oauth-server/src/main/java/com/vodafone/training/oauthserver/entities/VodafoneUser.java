package com.vodafone.training.oauthserver.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="users")
public class VodafoneUser {

    @Id
    @Column(name= "user_id")
    private String userId;
    @Column(name= "user_name")
    private String userName;
    @Column(name= "user_password")
    private String userPassword;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    public List<String> roles;
    @Column(name= "user_email")
    public String userEmail;
    @Column(name= "user_mobile")
    public String userMobile;


    public String[] getRolesDetails(){
        return roles.toArray(new String[0]);
    }

}
