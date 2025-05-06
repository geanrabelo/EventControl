package com.br.application.gateway;

import com.br.core.entities.Guest;

import java.util.List;
import java.util.UUID;

public interface GuestGateway {

    UUID registeredGuest(Guest guest);

    List<Guest> findAll();

    Guest findById(UUID id);

    boolean alreadyRegistered(Guest guest);

    boolean existsById(UUID id);

    void deleteById(UUID id);

}
