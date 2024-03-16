package com.vodafone.training.cartservice.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.vodafone.training.cartservice.domainObjects.Product;
import com.vodafone.training.cartservice.util.ProductConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@DynamoDBTable(tableName = "orders")
public class Order {

    @DynamoDBAttribute(attributeName = "user_id")
    private String userId;
    @DynamoDBTypeConverted(converter = ProductConverter.class)
    private Product product;
    @DynamoDBAttribute(attributeName = "total_price")
    private Double totalPrice;
    @DynamoDBHashKey(attributeName = "order_id")
    private String orderId;

}
