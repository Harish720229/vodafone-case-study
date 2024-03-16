package com.vodafone.training.cartservice.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.vodafone.training.cartservice.domainObjects.Product;
import com.vodafone.training.cartservice.util.ProductConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamoDBTable(tableName = "carts")
public class Cart implements Serializable {

    @DynamoDBHashKey(attributeName = "cart_id")
    private String cartId;
    @DynamoDBAttribute(attributeName = "user_id")
    private String userId;
    @DynamoDBTypeConverted(converter = ProductConverter.class)
    private Product product;
    @DynamoDBAttribute(attributeName = "total_price")
    private Double totalPrice;

}
