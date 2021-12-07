package com.nttdata.creditcard.repository;

import com.nttdata.creditcard.model.entity.CreditCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ICreditCardRepository extends ReactiveMongoRepository<CreditCard, String> {
    public Flux<CreditCard> findByCustomerDocumentNumber(String documentNumber);
}
