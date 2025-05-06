package com.br.infrastructure.service.impl;

import com.br.core.entities.Event;
import com.br.infrastructure.dto.event.EventCreation;
import com.br.infrastructure.dto.event.EventCreationToEvent;
import com.br.infrastructure.dto.event.EventDetails;
import com.br.infrastructure.dto.event.EventToDetails;
import com.br.infrastructure.service.EventService;
import com.br.usecases.EventUsecases;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventUsecases eventUsecases;

    public EventServiceImpl(EventUsecases eventUsecases){
        this.eventUsecases = eventUsecases;
    }

    @Override
    public String createEvent(EventCreation eventCreation) {
        Event event = new EventCreationToEvent(eventCreation).toEvent();
        UUID id = eventUsecases.createEvent(event);
        return "Event registered successfully - UUID: "+id;
    }

    @Override
    public List<EventDetails> findAll() {
        return eventUsecases.findAll().stream().map(e -> new EventToDetails(e).toEventDetails()).toList();
    }

    @Override
    public EventDetails findById(UUID id) {
        Event event = eventUsecases.findById(id);
        return new EventToDetails(event).toEventDetails();
    }

    @Override
    public void cancelEvent(UUID id) {
        eventUsecases.canceledEvent(id);
    }
}
