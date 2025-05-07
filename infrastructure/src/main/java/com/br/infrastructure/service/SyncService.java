package com.br.infrastructure.service;

import com.br.infrastructure.domain.CheckinEntity;
import com.br.infrastructure.domain.EventEntity;
import com.br.infrastructure.domain.GuestEntity;
import com.br.infrastructure.redis.CheckinRedis;
import com.br.infrastructure.redis.EventRedis;
import com.br.infrastructure.redis.GuestRedis;
import com.br.infrastructure.repositories.CheckinEntityRepository;
import com.br.infrastructure.repositories.EventEntityRepository;
import com.br.infrastructure.repositories.GuestEntityRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyncService {

    private final EventEntityRepository eventEntityRepository;
    private final GuestEntityRepository guestEntityRepository;
    private final CheckinEntityRepository checkinEntityRepository;
    private final RedisTemplate<String, EventRedis> eventRedisTemplate;
    private final RedisTemplate<String, GuestRedis> guestRedisTemplate;
    private final RedisTemplate<String, CheckinRedis> checkinRedisTemplate;
    private static final String keyPatternEvent = "event: ";
    private static final String keyPatternGuest = "guest: ";
    private static final String keyPatternCheckin = "checkin: ";


    public SyncService(EventEntityRepository eventEntityRepository,
                       GuestEntityRepository guestEntityRepository,
                       CheckinEntityRepository checkinEntityRepository,
                       RedisTemplate<String, EventRedis> eventRedisTemplate,
                       RedisTemplate<String, GuestRedis> guestRedisRedisTemplate,
                       RedisTemplate<String, CheckinRedis> checkinRedisTemplate) {
        this.eventEntityRepository = eventEntityRepository;
        this.guestEntityRepository = guestEntityRepository;
        this.checkinEntityRepository = checkinEntityRepository;
        this.eventRedisTemplate = eventRedisTemplate;
        this.guestRedisTemplate = guestRedisRedisTemplate;
        this.checkinRedisTemplate = checkinRedisTemplate;
    }

    @Scheduled(fixedRate = 50000)
    public void syncEvent(){
        List<EventEntity> listAll = eventEntityRepository.findAll();
        if(!listAll.isEmpty()){
            eventRedisTemplate.opsForZSet().removeRangeByScore(keyPatternEvent, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
            for(EventEntity eventEntity : listAll){
                EventRedis eventRedis = EventRedis.toEventRedis(eventEntity);
                eventRedisTemplate.opsForZSet().add(keyPatternEvent, eventRedis, eventRedis.id().hashCode());
            }
        }
    }

    @Scheduled(fixedRate = 50000)
    public void syncGuest(){
        List<GuestEntity> listAll = guestEntityRepository.findAll();
        if(!listAll.isEmpty()){
            guestRedisTemplate.opsForZSet().removeRangeByScore(keyPatternGuest, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
            for(GuestEntity guestEntity : listAll){
                GuestRedis guestRedis = GuestRedis.toGuestRedis(guestEntity);
                guestRedisTemplate.opsForZSet().add(keyPatternGuest, guestRedis, guestRedis.id().hashCode());
            }
        }
    }

    @Scheduled(fixedRate = 50000)
    public void syncCheckin(){
        List<CheckinEntity> listAll = checkinEntityRepository.findAll();
        if(!listAll.isEmpty()){
            checkinRedisTemplate.opsForZSet().removeRangeByScore(keyPatternCheckin, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
            for(CheckinEntity checkinEntity : listAll){
                CheckinRedis checkinRedis = CheckinRedis.toCheckinRedis(checkinEntity);
                checkinRedisTemplate.opsForZSet().add(keyPatternCheckin, checkinRedis, checkinRedis.id().hashCode());
            }
        }
    }
}
