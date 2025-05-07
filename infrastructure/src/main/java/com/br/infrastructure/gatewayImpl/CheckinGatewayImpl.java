package com.br.infrastructure.gatewayImpl;

import com.br.application.gateway.CheckinGateway;
import com.br.core.entities.Checkin;
import com.br.core.entities.Guest;
import com.br.core.enums.EnumCode;
import com.br.core.enums.GuestStatus;
import com.br.core.exceptions.EventNotFound;
import com.br.core.exceptions.GuestNotFound;
import com.br.infrastructure.domain.CheckinEntity;
import com.br.infrastructure.domain.GuestEntity;
import com.br.infrastructure.dto.checkin.CheckinToEntityJpa;
import com.br.infrastructure.redis.CheckinRedis;
import com.br.infrastructure.redis.GuestRedis;
import com.br.infrastructure.repositories.CheckinEntityRepository;
import com.br.infrastructure.repositories.EventEntityRepository;
import com.br.infrastructure.repositories.GuestEntityRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CheckinGatewayImpl implements CheckinGateway {

    private final CheckinEntityRepository checkinEntityRepository;
    private final GuestEntityRepository guestEntityRepository;
    private final EventEntityRepository eventEntityRepository;
    private final RedisTemplate<String, CheckinRedis> checkinRedisTemplate;
    private final RedisTemplate<String, GuestRedis> guestRedisTemlate;
    private static final String keyPatternCheckin = "checkin: ";
    private static final String keyPatternGuest = "guest: ";

    public CheckinGatewayImpl(CheckinEntityRepository checkinEntityRepository,
                              GuestEntityRepository guestEntityRepository,
                              EventEntityRepository eventEntityRepository,
                              RedisTemplate<String, CheckinRedis> checkinRedisTemplate,
                              RedisTemplate<String, GuestRedis> guestRedisTemplate){
        this.checkinEntityRepository = checkinEntityRepository;
        this.guestEntityRepository = guestEntityRepository;
        this.eventEntityRepository = eventEntityRepository;
        this.checkinRedisTemplate = checkinRedisTemplate;
        this.guestRedisTemlate = guestRedisTemplate;
    }

    @Override
    public UUID executeCheckin(Guest guest) {
        if(guestEntityRepository.existsById(guest.getId()) &&  eventEntityRepository.existsById(guest.getEventId())){
            GuestEntity guestDatabase = guestEntityRepository.getReferenceById(guest.getId());
            guestDatabase.setGuestStatus(GuestStatus.CHECKED_ID);
            GuestEntity guestSaved = guestEntityRepository.save(guestDatabase);
            GuestRedis guestRedis = GuestRedis.toGuestRedis(guestSaved);
            guestRedisTemlate.opsForZSet().removeRangeByScore(keyPatternGuest, guestRedis.id().hashCode(), guestRedis.id().hashCode());
            guestRedisTemlate.opsForZSet().add(keyPatternGuest, guestRedis, guestRedis.id().hashCode());
            Checkin checkin = new Checkin.CheckinBuilder()
                    .builder()
                    .guestId(guest.getId())
                    .eventId(guest.getEventId())
                    .checkinTime(LocalDateTime.now())
                    .device("Cellphone")
                    .build();
            CheckinEntity conversion = new CheckinToEntityJpa(checkin).toEntityJpa();
            CheckinEntity checkinSaved = checkinEntityRepository.save(conversion);
            CheckinRedis checkinRedis = CheckinRedis.toCheckinRedis(checkinSaved);
            checkinRedisTemplate.opsForZSet().add(keyPatternCheckin, checkinRedis, checkinRedis.id().hashCode());
            return checkinSaved.getId();
        } else if (!guestEntityRepository.existsById(guest.getId())) {
            throw new GuestNotFound(EnumCode.GU000.getMessage());
        } else{
            throw new EventNotFound(EnumCode.EV000.getMessage());
        }
    }
}
