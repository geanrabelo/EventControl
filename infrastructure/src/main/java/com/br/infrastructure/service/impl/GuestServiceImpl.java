package com.br.infrastructure.service.impl;

import com.br.core.entities.Guest;
import com.br.infrastructure.dto.guest.GuestCreation;
import com.br.infrastructure.dto.guest.GuestCreationToGuest;
import com.br.infrastructure.dto.guest.GuestDetails;
import com.br.infrastructure.dto.guest.GuestToDetails;
import com.br.infrastructure.service.GuestService;
import com.br.usecases.GuestUsecases;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestUsecases guestUsecases;

    public GuestServiceImpl(GuestUsecases guestUsecases){
        this.guestUsecases = guestUsecases;
    }

    @Override
    public String registeredGuest(GuestCreation guestCreation) {
        Guest guest = new GuestCreationToGuest(guestCreation).toGuest();
        UUID id = guestUsecases.registeredGuest(guest);
        return "Guest registered successfully - UUID: "+id;
    }

    @Override
    public List<GuestDetails> findAll() {
        return guestUsecases.findAll().stream().map(g -> new GuestToDetails(g).toDetails()).toList();
    }

    @Override
    public GuestDetails findById(UUID id) {
        Guest guest = guestUsecases.findById(id);
        return new GuestToDetails(guest).toDetails();
    }

    @Override
    public void deleteById(UUID id) {
        guestUsecases.deleteById(id);
    }
}
