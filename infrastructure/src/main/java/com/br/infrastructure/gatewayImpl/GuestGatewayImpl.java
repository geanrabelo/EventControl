package com.br.infrastructure.gatewayImpl;

import com.br.application.gateway.GuestGateway;
import com.br.core.entities.Guest;
import com.br.core.enums.EnumCode;
import com.br.core.exceptions.GuestAlreadyRegisteredEvent;
import com.br.core.exceptions.GuestNotFound;
import com.br.infrastructure.domain.GuestEntity;
import com.br.infrastructure.dto.GuestEntityToGuest;
import com.br.infrastructure.dto.GuestToEntityJpa;
import com.br.infrastructure.repositories.EventEntityRepository;
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
        return guestEntityRepository.findAll().stream().map(g -> new GuestEntityToGuest(g).toGuest()).toList();
    }

    @Override
    public Guest findById(UUID id) {
        if(existsById(id)){
            GuestEntity guestDatabase = guestEntityRepository.getReferenceById(id);
            return new GuestEntityToGuest(guestDatabase).toGuest();
        }
        throw new GuestNotFound(EnumCode.GU000.getMessage());
    }

    @Override
    public boolean alreadyRegistered(Guest guest) {
        if(guestEntityRepository.existsByEmail(guest.getEmail())){
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(UUID id) {
        return guestEntityRepository.existsById(id);
    }

    @Override
    public void deleteById(UUID id) {
        if(existsById(id)){
            guestEntityRepository.deleteById(id);
        }else{
            throw new GuestNotFound(EnumCode.GU000.getMessage());
        }
    }
}
