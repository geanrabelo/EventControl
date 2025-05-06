package com.br.infrastructure.gatewayImpl;

import com.br.application.gateway.EventGateway;
import com.br.core.entities.Event;
import com.br.infrastructure.domain.EventEntity;
import com.br.infrastructure.dto.EventToEntityJpa;
import com.br.infrastructure.repositories.EventEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class EventGatewayImpl implements EventGateway {

    private final EventEntityRepository eventEntityRepository;

    public EventGatewayImpl(EventEntityRepository eventEntityRepository){
        this.eventEntityRepository = eventEntityRepository;
    }

    @Override
    public UUID createEvent(Event event) {
        EventEntity conversion = new EventToEntityJpa(event).toJpa();
        EventEntity eventSaved = eventEntityRepository.save(conversion);
        return eventSaved.getId();
    }

    @Override
    public List<Event> findAll() {
        return List.of();
    }

    @Override
    public Event findById(UUID id) {
        return null;
    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }

    @Override
    public void canceledEvent(UUID id) {

    }
}
