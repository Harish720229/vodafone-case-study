package com.vodafone.training.cartservice.controller;

import com.vodafone.training.cartservice.domainObjects.CartInput;
import com.vodafone.training.cartservice.domainObjects.VodafoneUser;
import com.vodafone.training.cartservice.entities.Cart;
import com.vodafone.training.cartservice.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/all")
    public Iterable<Cart> getCarts() {
        log.info("Getting all carts");
        return cartService.getAllCarts();
    }

    @PostMapping("/add")
    public Cart saveCart(@RequestBody  CartInput cartInput) {
        log.info("Cart input: {}", cartInput);
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

        return cartService.addProductToCart(cartInput,vodafoneUser);
    }

    @PostMapping("/save-cart")
    public Cart saveCart(@RequestBody Cart cart) {
        log.info("Cart to be saved: {}", cart);
        return cartService.saveCart(cart);
    }

    @GetMapping("/by-id/{id}")
    public Cart getCartById(@PathVariable String id) {
        log.info("Getting cart by id: {}", id);
        return cartService.getCart(id);
    }

    @GetMapping("/total-price/{id}")
    public Double getTotalPrice(@PathVariable String id) {
        log.info("Getting total price of cart: {}", id);
        return cartService.getTotalPrice(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCart(@PathVariable String id) {
        log.info("Deleting cart by id: {}", id);
        cartService.deleteCart(id);
    }

    @DeleteMapping("/delete-all")
    public void deleteAllCarts() {
        log.info("Deleting all carts");
        cartService.deleteAllCarts();
    }



}
