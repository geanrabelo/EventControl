package com.br.infrastructure.gatewayImpl;

import com.br.application.gateway.GuestGateway;
import com.br.core.entities.Guest;
import com.br.core.enums.EnumCode;
import com.br.core.exceptions.GuestAlreadyRegisteredEvent;
import com.br.core.exceptions.GuestNotFound;
import com.br.infrastructure.domain.GuestEntity;
import com.br.infrastructure.dto.guest.GuestEntityToGuest;
import com.br.infrastructure.dto.guest.GuestToEntityJpa;
import com.br.infrastructure.redis.GuestRedis;
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
    private final RedisTemplate<String, GuestRedis> guestRedisTemplate;
    public static final String keyPattern = "guest: ";


    public GuestGatewayImpl(GuestEntityRepository guestEntityRepository, RedisTemplate<String, GuestRedis> guestRedisTemplate){
        this.guestEntityRepository = guestEntityRepository;
        this.guestRedisTemplate = guestRedisTemplate;
    }

    @Override
    public UUID registeredGuest(Guest guest) {
        if(!alreadyRegistered(guest)){
            GuestEntity conversion = new GuestToEntityJpa(guest).toEntityJpa();
            conversion.setRegisteredAt(LocalDateTime.now());
            GuestEntity guestSaved = guestEntityRepository.save(conversion);
            GuestRedis guestRedis = GuestRedis.toGuestRedis(guestSaved);
            guestRedisTemplate.opsForZSet().add(keyPattern, guestRedis, guestRedis.id().hashCode());
            return guestSaved.getEventId();
        }
        throw new GuestAlreadyRegisteredEvent(EnumCode.GU001.getMessage());
    }

    @Override
    public List<Guest> findAll() {
        Set<GuestRedis> setGuestRedis = guestRedisTemplate.opsForZSet().rangeByScore(keyPattern, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        if(setGuestRedis.size() != 0){
            setGuestRedis.stream().map(s -> new Guest.GuestBuilder()
                    //    private UUID id;
                    //    private String name;
                    //    private String email;
                    //    private String document;
                    //    private GuestStatus guestStatus;
                    //    private UUID eventId;
                    //    private LocalDateTime registeredAt;
                    .builder()
                    .id(s.id())
                    .build()).toList();
        }
        return guestEntityRepository.findAll().stream().map(g -> new GuestEntityToGuest(g).toGuest()).toList();
    }

    @Override
    public Guest findById(UUID id) {
        if(existsById(id)){
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
        }else{
            throw new GuestNotFound(EnumCode.GU000.getMessage());
        }
    }
}
