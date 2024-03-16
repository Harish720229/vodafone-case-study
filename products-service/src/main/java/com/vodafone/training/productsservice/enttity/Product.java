package com.vodafone.training.productsservice.enttity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;


/**
 * sample product
 * {
 *     "product_id": 1,
 *     "product_name": "Smartphone",
 *     "brand": "Samsung",
 *     "category": "Electronics",
 *     "price": 799,
 *     "rating": 4.5,
 *     "color": "Black",
 *     "size": "N/A"
 *   }
 */

@Document(indexName = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    @Id
    @Field(name = "product_id")
    private Integer productId;
    @Field(name = "product_name")
    private String productName;
    private String brand;
    private String category;
    private Double price;
    private Double rating;
    private String color;
    private String size;

}
