package com.br.infrastructure.config;

import com.br.application.gateway.GuestGateway;
import com.br.application.impl.GuestUsecasesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GuestConfig {

    @Bean
    public GuestUsecasesImpl guestUsecasesImpl(GuestGateway guestGateway){
        return new GuestUsecasesImpl(guestGateway);
    }
}
