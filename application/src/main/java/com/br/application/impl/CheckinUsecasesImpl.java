package com.br.application.impl;

import com.br.application.gateway.CheckinGateway;
import com.br.core.entities.Guest;
import com.br.usecases.CheckinUsecases;

import java.util.UUID;

public class CheckinUsecasesImpl implements CheckinUsecases {

    private final CheckinGateway checkinGateway;

    public CheckinUsecasesImpl(CheckinGateway checkinGateway){
        this.checkinGateway = checkinGateway;
    }

    @Override
    public UUID executeCheckin(Guest guest) {
        return checkinGateway.executeCheckin(guest);
    }
}
