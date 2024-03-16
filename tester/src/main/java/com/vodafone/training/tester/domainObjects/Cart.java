package com.vodafone.training.tester.domainObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart implements Serializable {

    private String cartId;
    private String userId;
    private Product product;
    private Double totalPrice;

}
