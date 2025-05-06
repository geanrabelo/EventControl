package com.br.application.gateway;

import com.br.core.entities.Event;

import java.util.List;
import java.util.UUID;

public interface EventGateway {

    UUID createEvent(Event event);

    List<Event> findAll();

    Event findById(UUID id);

    boolean existsById(UUID id);

    void canceledEvent(UUID id);

}
