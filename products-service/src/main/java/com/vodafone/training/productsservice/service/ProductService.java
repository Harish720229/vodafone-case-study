package com.vodafone.training.productsservice.service;

import com.vodafone.training.productsservice.dao.ProductRepo;
import com.vodafone.training.productsservice.enttity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product saveProduct(Product product) {
        return productRepo.save(product);
    }

    public Iterable<Product> getProducts() {
        log.info("Getting all products");
        return productRepo.findAll();
    }

    public Product getProductById(Integer id) {
        log.info("Getting product by id: {}", id);
        return productRepo.findById(id).orElse(null);
    }

    public List<Product> getProductByName(String name) {
        log.info("Getting product by name: {}", name);
        return productRepo.findByProductName(name);
    }

    public List<Product> getProductByBrand(String brand) {
        log.info("Getting product by brand: {}", brand);
        return productRepo.findByBrand(brand);
    }

    public List<Product> getProductByCategory(String category) {
        log.info("Getting product by category: {}", category);
        return productRepo.findByCategory(category);
    }

    public Iterable<Product> saveAllProducts(List<Product> products) {
        log.info("Adding all products: {}", products);
        return productRepo.saveAll(products);
    }


    public void deleteAllProducts() {
        log.info("Deleting all products");
        productRepo.deleteAll();
    }

    public void deleteById(Integer id) {
        log.info("Deleting product by id: {}", id);
        productRepo.deleteById(id);
    }

    public void updateProduct(Product product) {
        Product existingProduct = productRepo.findById(product.getProductId()).orElse(null);
        log.info("Existing product: {}", existingProduct);
        if (existingProduct != null) {
            log.info("Updating product: {}", product);
            BeanUtils.copyProperties(product, existingProduct, "productId");
            productRepo.save(existingProduct);
            log.info("Product updated successfully");
        }
    }

}
