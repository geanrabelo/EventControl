package com.br.infrastructure.gatewayImpl;

import com.br.application.gateway.EventGateway;
import com.br.core.entities.Event;
import com.br.core.enums.EnumCode;
import com.br.core.exceptions.EventNotFound;
import com.br.infrastructure.domain.EventEntity;
import com.br.infrastructure.dto.event.EventEntityToEvent;
import com.br.infrastructure.dto.event.EventToEntityJpa;
import com.br.infrastructure.redis.EventRedis;
import com.br.infrastructure.repositories.EventEntityRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class EventGatewayImpl implements EventGateway {

    private final EventEntityRepository eventEntityRepository;
    private final RedisTemplate<String, EventRedis> eventRedisTemplate;
    private static final String keyPattern = "event: ";

    public EventGatewayImpl(EventEntityRepository eventEntityRepository, RedisTemplate<String, EventRedis> eventRedisTemplate){
        this.eventEntityRepository = eventEntityRepository;
        this.eventRedisTemplate = eventRedisTemplate;
    }

    @Override
    public UUID createEvent(Event event) {
        EventEntity conversion = new EventToEntityJpa(event).toJpa();
        conversion.setCreatedAt(LocalDateTime.now());
        EventEntity eventSaved = eventEntityRepository.save(conversion);

        EventRedis eventRedis = EventRedis.toEventRedis(eventSaved);
        eventRedisTemplate.opsForValue().set(eventRedis.id().toString(), eventRedis);
        return eventSaved.getId();
    }

    @Override
    public List<Event> findAll() {
        return eventEntityRepository.findAll().stream().map(e -> new EventEntityToEvent(e).toEvent()).toList();
    }

    @Override
    public Event findById(UUID id) {
        if(existsById(id)){
            EventEntity eventDatabase = eventEntityRepository.getReferenceById(id);
            return new EventEntityToEvent(eventDatabase).toEvent();
        }
        throw new EventNotFound(EnumCode.EV000.getMessage());
    }

    @Override
    public boolean existsById(UUID id) {
        return eventEntityRepository.existsById(id);
    }

    @Override
    public void canceledEvent(UUID id) {
        if(existsById(id)){
            eventEntityRepository.deleteById(id);
        }else{
            throw new EventNotFound(EnumCode.EV000.getMessage());
        }
    }
}
