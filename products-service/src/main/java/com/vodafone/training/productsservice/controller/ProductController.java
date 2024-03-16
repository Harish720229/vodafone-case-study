package com.vodafone.training.productsservice.controller;


import com.vodafone.training.productsservice.enttity.Product;
import com.vodafone.training.productsservice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public Iterable<Product> getProducts() {
        log.info("Getting all products");
        return productService.getProducts();
    }

    @GetMapping("/by-id/{id}")
    public Product getProductById(@PathVariable Integer id) {
        log.info("Getting product by id: {}", id);
        return productService.getProductById(id);
    }

    @GetMapping("/by-name/{name}")
    public List<Product> getProductByName(@PathVariable String name) {
        log.info("Getting product by name: {}", name);
        return productService.getProductByName(name);
    }

    @GetMapping("/by-brand/{brand}")
    public List<Product> getProductByBrand(@PathVariable String brand) {
        log.info("Getting product by brand: {}", brand);
        return productService.getProductByBrand(brand);
    }

    @GetMapping("/by-category/{category}")
    public List<Product> getProductByCategory(@PathVariable String category) {
        log.info("Getting product by category: {}", category);
        return productService.getProductByCategory(category);
    }

    @PostMapping("/add")
    public Product saveProduct(@RequestBody Product product) {
        log.info("Adding product: {}", product);
        return productService.saveProduct(product);
    }

    @PostMapping("/add-all")
    public Iterable<Product> saveAllProducts(@RequestBody List<Product> products) {
        log.info("Adding all products: {}", products);
        return productService.saveAllProducts(products);
    }

    @DeleteMapping("/delete-all")
    public void deleteAllProducts() {
        log.info("Deleting all products");
        productService.deleteAllProducts();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        productService.deleteById(id);
    }

    @PutMapping("/update")
    public void updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
    }

}
