package com.vodafone.training.cartservice.service;

import com.vodafone.training.cartservice.domainObjects.Payment;
import com.vodafone.training.cartservice.domainObjects.Product;
import com.vodafone.training.cartservice.entities.Cart;
import com.vodafone.training.cartservice.entities.Order;
import com.vodafone.training.cartservice.exception.VodafoneApplicationException;
import com.vodafone.training.cartservice.repo.CartRepo;
import com.vodafone.training.cartservice.repo.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CartRepo cartRepo;

    public Order createOrder(Payment payment){
        log.info("Payment received: " + payment.toString());
        String cartId=payment.getCartId();
        if(cartId==null){
            throw new VodafoneApplicationException("cart id is empty");
        }
        else {
            Cart cart = cartRepo.findById(cartId).orElseThrow(()->new VodafoneApplicationException("cartId doesn't exist"));
            Product product = cart.getProduct();
            Order order = new Order();
            order.setProduct(product);
            order.setUserId(cart.getUserId());
            order.setTotalPrice(payment.getTotalPrice());
            order.setOrderId("#"+ new Random().nextInt(1000));
            return  orderRepo.save(order);
        }
    }

}
