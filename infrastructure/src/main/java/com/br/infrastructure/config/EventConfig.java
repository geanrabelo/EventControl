package com.br.infrastructure.config;

import com.br.application.gateway.EventGateway;
import com.br.application.impl.EventUsecasesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {

    @Bean
    public EventUsecasesImpl eventUsecasesImpl(EventGateway eventGateway){
        return new EventUsecasesImpl(eventGateway);
    }
}
