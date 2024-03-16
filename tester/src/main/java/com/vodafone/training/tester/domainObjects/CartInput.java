package com.vodafone.training.tester.domainObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartInput {

    private Integer productId;
    private Integer quantity;

}
