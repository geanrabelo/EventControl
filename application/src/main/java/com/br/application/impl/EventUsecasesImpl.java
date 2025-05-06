package com.br.application.impl;

import com.br.application.gateway.EventGateway;
import com.br.core.entities.Event;
import com.br.usecases.EventUsecases;

import java.util.List;
import java.util.UUID;

public class EventUsecasesImpl implements EventUsecases {

    private final EventGateway eventGateway;

    public EventUsecasesImpl(EventGateway eventGateway){
        this.eventGateway = eventGateway;
    }

    @Override
    public UUID createEvent(Event event) {
        return eventGateway.createEvent(event);
    }

    @Override
    public List<Event> findAll() {
        return eventGateway.findAll();
    }

    @Override
    public Event findById(UUID id) {
        return eventGateway.findById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return eventGateway.existsById(id);
    }

    @Override
    public void canceledEvent(UUID id) {
        eventGateway.canceledEvent(id);
    }
}
