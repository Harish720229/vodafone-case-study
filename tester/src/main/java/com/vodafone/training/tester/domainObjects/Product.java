package com.vodafone.training.tester.domainObjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product implements Serializable {

    private Integer productId;
    private String productName;
    private String brand;
    private String category;
    private Double price;
    private Double rating;
    private String color;
    private String size;
    private Integer Quantity;

    @JsonIgnore
    public String getProductDetails() {

        return " Product Name: " + productName + "<br> Brand: " + brand + "<br> Category: " + category + "<br> Price: " + price + "<br> Rating: " + rating + "<br> Color: " + color + "<br> Size: " + size + "<br> Quantity: " + Quantity;
    }

}
