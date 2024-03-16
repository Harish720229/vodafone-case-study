package com.vodafone.training.cartservice.controller;

import com.vodafone.training.cartservice.domainObjects.Payment;
import com.vodafone.training.cartservice.domainObjects.VodafoneUser;
import com.vodafone.training.cartservice.entities.Order;
import com.vodafone.training.cartservice.service.EmailService;
import com.vodafone.training.cartservice.service.OrderService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/pay")
    public String pay(@RequestBody Payment payment) throws MessagingException, UnsupportedEncodingException {
        log.info("Payment received: " + payment.toString());
        Order order=null;
        if (payment != null) {
         order =  orderService.createOrder(payment);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Jwt jwt = (Jwt) authentication.getPrincipal();
        String jwtToken = jwt.getTokenValue();
        Map<String, Object> claimsMap = jwt.getClaims();
        System.out.println(claimsMap);
        VodafoneUser vodafoneUser = new VodafoneUser();
        vodafoneUser.setUserName((String) claimsMap.get("sub"));
        vodafoneUser.setRoles((List<String>) claimsMap.get("roles"));
        vodafoneUser.setUserEmail((String) claimsMap.get("user_email"));
        vodafoneUser.setUserMobile((String) claimsMap.get("user_mobile"));
        vodafoneUser.setUserId((String) claimsMap.get("user_id"));
        log.info("vodafoneUser extracted from jwt: " + vodafoneUser.toString());
        emailService.sendEmail(order,vodafoneUser);
        return "Payment successful! , Order id : "+order.getOrderId();
    }

}
