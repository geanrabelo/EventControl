package com.br.application.impl;

import com.br.application.gateway.GuestGateway;
import com.br.core.entities.Guest;
import com.br.usecases.GuestUsecases;

import java.util.List;
import java.util.UUID;

public class GuestUsecasesImpl implements GuestUsecases {

    private final GuestGateway guestGateway;

    public GuestUsecasesImpl(GuestGateway guestGateway){
        this.guestGateway = guestGateway;
    }

    @Override
    public UUID registeredGuest(Guest guest) {
        return guestGateway.registeredGuest(guest);
    }

    @Override
    public List<Guest> findAll() {
        return guestGateway.findAll();
    }

    @Override
    public Guest findById(UUID id) {
        return guestGateway.findById(id);
    }

    @Override
    public boolean alreadyRegistered(Guest guest) {
        return guestGateway.alreadyRegistered(guest);
    }

    @Override
    public boolean existsById(UUID id) {
        return guestGateway.existsById(id);
    }

    @Override
    public void deleteById(UUID id) {
        guestGateway.deleteById(id);
    }
}
