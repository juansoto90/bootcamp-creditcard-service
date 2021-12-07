package com.nttdata.creditcard.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("creditcard")
public class CreditCard {
    @Id
    private String id;
    private String cardNumber;
    private double consumption;
    private double creditLine;
    private int expirationMonth;
    private int expirationYear;
    private String cvv;
    private Customer customer;
    private String status;

    private Acquisition acquisition;
}
