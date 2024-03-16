package com.vodafone.training.productsservice.dao;

import com.vodafone.training.productsservice.enttity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepo extends ElasticsearchRepository<Product,Integer> {

    List<Product> findByProductName(String productName);

    List<Product> findByBrand(String brand);

    List<Product> findByCategory(String category);

}
