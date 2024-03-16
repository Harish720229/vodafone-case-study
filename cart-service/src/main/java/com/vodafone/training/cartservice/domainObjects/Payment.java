package com.vodafone.training.cartservice.domainObjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Payment {

    private String creditCardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    private String cartId;
    private Double totalPrice;

}
