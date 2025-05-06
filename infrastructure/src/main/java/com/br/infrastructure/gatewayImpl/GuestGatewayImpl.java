package com.br.infrastructure.gatewayImpl;

import com.br.application.gateway.GuestGateway;
import com.br.core.entities.Guest;
import com.br.core.enums.EnumCode;
import com.br.core.exceptions.GuestAlreadyRegisteredEvent;
import com.br.infrastructure.domain.GuestEntity;
import com.br.infrastructure.dto.GuestToEntityJpa;
import com.br.infrastructure.repositories.GuestEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GuestGatewayImpl implements GuestGateway {

    private final GuestEntityRepository guestEntityRepository;

    public GuestGatewayImpl(GuestEntityRepository guestEntityRepository){
        this.guestEntityRepository = guestEntityRepository;
    }

    @Override
    public UUID registeredGuest(Guest guest) {
        if(!alreadyRegistered(guest)){
            GuestEntity conversion = new GuestToEntityJpa(guest).toEntityJpa();
            GuestEntity guestSaved = guestEntityRepository.save(conversion);
            return guestSaved.getEventId();
        }
        throw new GuestAlreadyRegisteredEvent(EnumCode.GU001.getMessage());
    }

    @Override
    public List<Guest> findAll() {
        return List.of();
    }

    @Override
    public Guest findById(UUID id) {
        return null;
    }

    @Override
    public boolean alreadyRegistered(Guest guest) {
        if(guest.getEventId() != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }

    @Override
    public void deleteById(UUID id) {

    }
}
