package com.vodafone.training.cartservice.repo;

import com.vodafone.training.cartservice.entities.Cart;
import com.vodafone.training.cartservice.entities.Order;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

@EnableScan
public interface OrderRepo extends DynamoDBCrudRepository<Order, String> {
}
