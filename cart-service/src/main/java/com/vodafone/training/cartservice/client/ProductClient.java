package com.vodafone.training.cartservice.client;

import com.vodafone.training.cartservice.domainObjects.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products-service")
public interface ProductClient {
    @GetMapping("/products/by-id/{id}")
    public Product getProduct(@PathVariable Integer id);
}


