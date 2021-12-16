package com.nttdata.creditcard.service;

import com.nttdata.creditcard.model.entity.CreditCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ICreditCardService {
    public Mono<CreditCard> findById(String id);
    public Flux<CreditCard> findByCustomerDocumentNumber(String documentNumber);
    public Mono<CreditCard> findByCardNumber(String cardNumber);
    public Mono<CreditCard> save(CreditCard creditCard);
}
