package com.br.infrastructure.service;

import com.br.infrastructure.dto.guest.GuestCreation;
import com.br.infrastructure.dto.guest.GuestDetails;

import java.util.List;
import java.util.UUID;

public interface GuestService {

    String registeredGuest(GuestCreation guestCreation);

    List<GuestDetails> findAll();

    GuestDetails findById(UUID id);

    void deleteById(UUID id);
}
