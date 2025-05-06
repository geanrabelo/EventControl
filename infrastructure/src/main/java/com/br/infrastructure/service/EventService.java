package com.br.infrastructure.service;

import com.br.infrastructure.dto.event.EventCreation;
import com.br.infrastructure.dto.event.EventDetails;

import java.util.List;
import java.util.UUID;

public interface EventService {

    String createEvent(EventCreation eventCreation);

    List<EventDetails> findAll();

    EventDetails findById(UUID id);

    void cancelEvent(UUID id);
}
