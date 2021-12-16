package com.nttdata.creditcard.service.impl;

import com.nttdata.creditcard.model.entity.CreditCard;
import com.nttdata.creditcard.repository.ICreditCardRepository;
import com.nttdata.creditcard.service.ICreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements ICreditCardService {

    private final ICreditCardRepository repository;

    @Override
    public Mono<CreditCard> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<CreditCard> findByCustomerDocumentNumber(String documentNumber) {
        return repository.findByCustomerDocumentNumber(documentNumber);
    }

    @Override
    public Mono<CreditCard> findByCardNumber(String cardNumber) {
        return repository.findByCardNumber(cardNumber);
    }

    @Override
    public Mono<CreditCard> save(CreditCard creditCard) {
        return repository.save(creditCard);
    }
}
