package com.vodafone.training.cartservice.util;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vodafone.training.cartservice.domainObjects.Product;

public class ProductConverter implements DynamoDBTypeConverter<String, Product> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convert(Product product) {
        try {
            // Serialize the Product object to a JSON string
            return objectMapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Product to JSON: " + e.getMessage(), e);
        }
    }

    @Override
    public Product unconvert(String productStr) {
        try {
            // Deserialize the JSON string to a Product object
            return objectMapper.readValue(productStr, Product.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting JSON to Product: " + e.getMessage(), e);
        }
    }
}
