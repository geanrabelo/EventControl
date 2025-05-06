package com.br.infrastructure.service.impl;

import com.br.infrastructure.dto.EventCreation;
import com.br.infrastructure.dto.EventDetails;
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

        return "";
    }

    @Override
    public List<EventDetails> findAll() {
        return List.of();
    }

    @Override
    public EventDetails findById(UUID id) {
        return null;
    }

    @Override
    public void cancelEvent(UUID id) {

    }
}
