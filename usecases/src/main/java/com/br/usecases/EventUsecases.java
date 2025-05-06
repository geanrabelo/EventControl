package com.br.usecases;

import com.br.core.entities.Event;

import java.util.List;
import java.util.UUID;

public interface EventUsecases {

    UUID createEvent(Event event);

    List<Event> findAll();

    Event findById(UUID id);

    boolean existsById(UUID id);

    void canceledEvent(UUID id);
}
