package com.br.infrastructure.config;

import com.br.application.gateway.CheckinGateway;
import com.br.application.impl.CheckinUsecasesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckinConfig {

    @Bean
    public CheckinUsecasesImpl checkinUsecasesImpl(CheckinGateway checkinGateway){
        return new CheckinUsecasesImpl(checkinGateway);
    }
}

