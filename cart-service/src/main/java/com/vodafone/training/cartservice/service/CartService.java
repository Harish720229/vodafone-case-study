package com.vodafone.training.cartservice.service;

import com.vodafone.training.cartservice.client.ProductClient;
import com.vodafone.training.cartservice.domainObjects.CartInput;
import com.vodafone.training.cartservice.domainObjects.VodafoneUser;
import com.vodafone.training.cartservice.entities.Cart;
import com.vodafone.training.cartservice.domainObjects.Product;
import com.vodafone.training.cartservice.repo.CartRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CartService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private CartRepo cartRepo;

    public Cart saveCart(Cart cart) {
        log.info("Cart to be saved: {}", cart);
        return cartRepo.save(cart);
    }

    public Cart getCart(String cartId) {
        log.info("Cart to be fetched: {}", cartId);
        return cartRepo.findById(cartId).orElse(null);
    }

    public void deleteCart(String cartId) {
        log.info("Cart to be deleted: {}", cartId);
        cartRepo.deleteById(cartId);
    }

    public List<Cart> getAllCarts() {
        log.info("All carts to be fetched");
        return (List<Cart>) cartRepo.findAll();
    }

    public void deleteAllCarts() {
        log.info("All carts to be deleted");
        cartRepo.deleteAll();
    }

    public Cart addProductToCart(CartInput cartInput,VodafoneUser vodafoneUser){
        log.info("Product to be added to cart: {}", cartInput);
            String cartId = "CART"+System.currentTimeMillis();
            Cart cart = new Cart();
            cart = new Cart();
            cart.setCartId(cartId);
            cart.setUserId(vodafoneUser.getUserId());
            Product product= productClient.getProduct(cartInput.getProductId());
            product.setQuantity(cartInput.getQuantity());
            cart.setProduct(product);
            cart.setTotalPrice(product.getPrice()*cartInput.getQuantity());
            log.info("Cart to be saved: {}", cart);
            return cartRepo.save(cart);
    }

    public Double getTotalPrice(String cartId) {
        log.info("Total price to be fetched for cart: {}", cartId);
        Cart cart = cartRepo.findById(cartId).orElse(null);
        if (cart != null) {
            return cart.getTotalPrice();
        }
        log.info("Cart not found: {}", cartId);
        return 0.0;
    }



}
