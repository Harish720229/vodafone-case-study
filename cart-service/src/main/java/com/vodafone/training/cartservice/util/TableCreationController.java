package com.vodafone.training.cartservice.util;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableCreationController {


    @Autowired
    private AmazonDynamoDB amazonDynamoDB;


    //orders/order_id/S
    //carts/cart_id/S
    @GetMapping("/create-table/{tableName}/{hashKeyName}/{hashKeyType}")
    public String createTable(@PathVariable String tableName, @PathVariable String hashKeyName, @PathVariable String hashKeyType) {
        return createDynamoDBTable(tableName, hashKeyName, hashKeyType);
    }


    public String createDynamoDBTable(String tableName, String hashKeyName, String hashKeyType) {

        try {
            DescribeTableResult describeTableResult = amazonDynamoDB.describeTable(tableName);
            System.out.println("Table '" + tableName + "' already exists.");
            return "Table '" + tableName + "' already exists.";
        } catch (ResourceNotFoundException e) {
            // Table does not exist, create it
            CreateTableRequest createTableRequest = new CreateTableRequest()
                    .withTableName(tableName)
                    .withKeySchema(new KeySchemaElement(hashKeyName, KeyType.HASH)
                    )// Composite primary key
                    .withAttributeDefinitions(
                            new AttributeDefinition(hashKeyName, hashKeyType)
                    )
                    .withProvisionedThroughput(new ProvisionedThroughput(5L, 5L)); // Adjust as needed

            CreateTableResult createTableResult = amazonDynamoDB.createTable(createTableRequest);
            System.out.println("Table '" + tableName + "' created successfully.");
            return "Table '" + tableName + "' created successfully.";
        }
    }


}
