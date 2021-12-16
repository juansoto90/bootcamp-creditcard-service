package com.nttdata.creditcard.service;

import com.nttdata.creditcard.model.entity.Customer;
import reactor.core.publisher.Mono;

public interface ICustomerService {
    public Mono<Customer> findByDocumentNumber(String documentNumber);
}
