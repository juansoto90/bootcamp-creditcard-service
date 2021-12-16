package com.nttdata.creditcard.handler;

import com.nttdata.creditcard.model.dto.debitCardDto;
import com.nttdata.creditcard.model.entity.CreditCard;
import com.nttdata.creditcard.service.ICreditCardService;
import com.nttdata.creditcard.service.ICustomerService;
import com.nttdata.creditcard.util.Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class CreditCardHandler {

    private final ICreditCardService service;
    private final ICustomerService iCustomerService;

    public Mono<ServerResponse> findById(ServerRequest request){
        String id = request.pathVariable("id");
        return service.findById(id)
                .flatMap(cc -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(cc)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> findByCustomerDocumentNumber(ServerRequest request){
        String documentNumber = request.pathVariable("documentNumber");
        return service.findByCustomerDocumentNumber(documentNumber)
                .collectList()
                .flatMap(cc -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(cc)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> creditCardCreate(ServerRequest request){
        Mono<CreditCard> creditCardMono = request.bodyToMono(CreditCard.class);
        return creditCardMono
                .map(cc -> {
                    cc.setCardNumber(Generator.generateCreditCardNumber(cc.getCardType()));
                    cc.setExpirationMonth(Generator.generateMonth());
                    cc.setExpirationYear(Generator.generateYear());
                    cc.setCvv(Generator.generateCVV());
                    return cc;
                })
                .flatMap(service::save)
                .flatMap(cc -> ServerResponse.created(URI.create("/creditcard/".concat(cc.getId())))
                                             .contentType(MediaType.APPLICATION_JSON)
                                             .bodyValue(cc)
                );
    }

    public Mono<ServerResponse> debitCardCreate(ServerRequest request){
        Mono<debitCardDto> debitCardDtoMono = request.bodyToMono(debitCardDto.class);
        return debitCardDtoMono
                .flatMap(dc -> iCustomerService.findByDocumentNumber(dc.getDocumentNumber())
                                            .map(c -> {
                                                CreditCard cc = new CreditCard();
                                                cc.setCustomer(c);
                                                cc.setCardType(dc.getCardType());
                                                cc.setCardNumber(Generator.generateCreditCardNumber(dc.getCardType()));
                                                cc.setExpirationMonth(Generator.generateMonth());
                                                cc.setExpirationYear(Generator.generateYear());
                                                cc.setCvv(Generator.generateCVV());
                                                cc.setStatus("CREATED");
                                                return cc;
                                            })
                )
                .flatMap(service::save)
                .flatMap(cc -> ServerResponse.created(URI.create("/creditcard/".concat(cc.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(cc)
                );
    }

    public Mono<ServerResponse> findByCardNumber(ServerRequest request){
        String cardNumber = request.pathVariable("cardNumber");
        return service.findByCardNumber(cardNumber)
                .flatMap(cc -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(cc)
                )
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
