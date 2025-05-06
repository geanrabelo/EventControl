package com.br.infrastructure.dto.event;

import com.br.core.entities.Event;

public record EventCreationToEvent(EventCreation eventCreation) {

    public Event toEvent(){
        return new Event.EventBuilder()
                .builder()
                .name(eventCreation.name())
                .description(eventCreation.description())
                .location(eventCreation.location())
                .dateTime(eventCreation.datetime())
                .capacity(eventCreation.capacity())
                .eventType(eventCreation.eventType())
                .build();
    }
}
