package com.nttdata.creditcard.config;

import com.nttdata.creditcard.handler.CreditCardHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> routes(CreditCardHandler handler){
        return route(GET("/creditcard/{id}"), handler::findById)
                .andRoute(GET("/creditcard/customer/{documentNumber}"), handler::findByCustomerDocumentNumber)
                .andRoute(POST("/creditcard"), handler::create);
    }
}
