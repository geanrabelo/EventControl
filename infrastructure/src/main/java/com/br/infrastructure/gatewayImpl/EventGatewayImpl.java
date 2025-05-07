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
import java.util.Set;
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
        eventRedisTemplate.opsForZSet().add(keyPattern, eventRedis, eventRedis.id().toString().hashCode());
        return eventSaved.getId();
    }

    @Override
    public List<Event> findAll() {
        Set<EventRedis> setEventRedis = eventRedisTemplate.opsForZSet().rangeByScore(keyPattern, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        if(setEventRedis.size() != 0){
            return setEventRedis.stream().map(s -> new Event.EventBuilder()
                    .builder()
                    .id(s.id())
                    .name(s.name())
                    .description(s.description())
                    .location(s.location())
                    .dateTime(s.datetime())
                    .capacity(s.capacity())
                    .eventType(s.eventType())
                    .createdAt(s.createdAt())
                    .build()).toList();
        }
        return eventEntityRepository.findAll().stream().map(e -> new EventEntityToEvent(e).toEvent()).toList();
    }

    @Override
    public Event findById(UUID id) {
        if(existsById(id)){
            Set<EventRedis> setEventRedis = eventRedisTemplate.opsForZSet().rangeByScore(keyPattern, id.hashCode(), id.hashCode());
            if(setEventRedis.size() != 0){
                EventRedis eventRedis = setEventRedis.stream().findFirst().get();
                return new Event.EventBuilder()
                        .builder()
                        .id(eventRedis.id())
                        .name(eventRedis.name())
                        .description(eventRedis.description())
                        .location(eventRedis.location())
                        .dateTime(eventRedis.datetime())
                        .capacity(eventRedis.capacity())
                        .eventType(eventRedis.eventType())
                        .createdAt(eventRedis.createdAt())
                        .build();
            }
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
            eventRedisTemplate.opsForZSet().removeRangeByScore(keyPattern, id.hashCode(), id.hashCode());
        }else{
            throw new EventNotFound(EnumCode.EV000.getMessage());
        }
    }
}
