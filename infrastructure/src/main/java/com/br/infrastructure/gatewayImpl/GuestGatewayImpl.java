package com.br.infrastructure.gatewayImpl;

import com.br.application.gateway.GuestGateway;
import com.br.core.entities.Guest;
import com.br.core.enums.EnumCode;
import com.br.core.exceptions.EventNotFound;
import com.br.core.exceptions.GuestAlreadyRegisteredEvent;
import com.br.core.exceptions.GuestNotFound;
import com.br.infrastructure.domain.GuestEntity;
import com.br.infrastructure.dto.guest.GuestEntityToGuest;
import com.br.infrastructure.dto.guest.GuestToEntityJpa;
import com.br.infrastructure.redis.GuestRedis;
import com.br.infrastructure.repositories.EventEntityRepository;
import com.br.infrastructure.repositories.GuestEntityRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class GuestGatewayImpl implements GuestGateway {

    private final GuestEntityRepository guestEntityRepository;
    private final EventEntityRepository eventEntityRepository;
    private final RedisTemplate<String, GuestRedis> guestRedisTemplate;
    public static final String keyPattern = "guest: ";


    public GuestGatewayImpl(GuestEntityRepository guestEntityRepository, RedisTemplate<String, GuestRedis> guestRedisTemplate, EventEntityRepository eventEntityRepository){
        this.guestEntityRepository = guestEntityRepository;
        this.guestRedisTemplate = guestRedisTemplate;
        this.eventEntityRepository = eventEntityRepository;
    }

    @Override
    public UUID registeredGuest(Guest guest) {
        if(!alreadyRegistered(guest) && eventEntityRepository.existsById(guest.getEventId())){
            GuestEntity conversion = new GuestToEntityJpa(guest).toEntityJpa();
            conversion.setRegisteredAt(LocalDateTime.now());
            GuestEntity guestSaved = guestEntityRepository.save(conversion);
            GuestRedis guestRedis = GuestRedis.toGuestRedis(guestSaved);
            guestRedisTemplate.opsForZSet().add(keyPattern, guestRedis, guestRedis.id().hashCode());
            return guestSaved.getEventId();
        } else if (alreadyRegistered(guest)) {
            throw new GuestAlreadyRegisteredEvent(EnumCode.GU001.getMessage());
        }else {
            throw new EventNotFound(EnumCode.EV000.getMessage());
        }
    }

    @Override
    public List<Guest> findAll() {
        Set<GuestRedis> setGuestRedis = guestRedisTemplate.opsForZSet().rangeByScore(keyPattern, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        if(setGuestRedis.size() != 0){
            setGuestRedis.stream().map(s -> new Guest.GuestBuilder()
                    .builder()
                    .id(s.id())
                    .name(s.name())
                    .email(s.email())
                    .document(s.document())
                    .guestStatus(s.guestStatus())
                    .eventId(s.eventId())
                    .registeredAt(s.registeredAt())
                    .build()).toList();
        }
        return guestEntityRepository.findAll().stream().map(g -> new GuestEntityToGuest(g).toGuest()).toList();
    }

    @Override
    public Guest findById(UUID id) {
        if(existsById(id)){
            Set<GuestRedis> setGuestRedis = guestRedisTemplate.opsForZSet().rangeByScore(keyPattern, id.hashCode(), id.hashCode());
            if(setGuestRedis.size() != 0){
                GuestRedis guestRedis = setGuestRedis.stream().findFirst().get();
                return new Guest.GuestBuilder()
                        .builder()
                        .id(guestRedis.id())
                        .name(guestRedis.name())
                        .email(guestRedis.email())
                        .document(guestRedis.document())
                        .guestStatus(guestRedis.guestStatus())
                        .eventId(guestRedis.eventId())
                        .registeredAt(guestRedis.registeredAt())
                        .build();
            }
            GuestEntity guestDatabase = guestEntityRepository.getReferenceById(id);
            return new GuestEntityToGuest(guestDatabase).toGuest();
        }
        throw new GuestNotFound(EnumCode.GU000.getMessage());
    }

    @Override
    public boolean alreadyRegistered(Guest guest) {
        if(guestEntityRepository.existsByEmail(guest.getEmail())){
            return true;
        }
        return false;
    }

    @Override
    public boolean existsById(UUID id) {
        return guestEntityRepository.existsById(id);
    }

    @Override
    public void deleteById(UUID id) {
        if(existsById(id)){
            guestEntityRepository.deleteById(id);
            guestRedisTemplate.opsForZSet().removeRangeByScore(keyPattern, id.hashCode(), id.hashCode());
        }else{
            throw new GuestNotFound(EnumCode.GU000.getMessage());
        }
    }
}
