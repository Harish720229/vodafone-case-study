package com.vodafone.training.cartservice.repo;

import com.vodafone.training.cartservice.entities.Cart;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

@EnableScan
public interface CartRepo extends DynamoDBCrudRepository<Cart, String> {
}
