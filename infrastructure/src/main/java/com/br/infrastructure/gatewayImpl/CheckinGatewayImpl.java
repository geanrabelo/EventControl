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
import com.br.infrastructure.dto.CheckinToEntityJpa;
import com.br.infrastructure.repositories.CheckinEntityRepository;
import com.br.infrastructure.repositories.EventEntityRepository;
import com.br.infrastructure.repositories.GuestEntityRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class CheckinGatewayImpl implements CheckinGateway {

    private final CheckinEntityRepository checkinEntityRepository;
    private final GuestEntityRepository guestEntityRepository;
    private final EventEntityRepository eventEntityRepository;

    public CheckinGatewayImpl(CheckinEntityRepository checkinEntityRepository, GuestEntityRepository guestEntityRepository, EventEntityRepository eventEntityRepository){
        this.checkinEntityRepository = checkinEntityRepository;
        this.guestEntityRepository = guestEntityRepository;
        this.eventEntityRepository = eventEntityRepository;
    }

    @Override
    public UUID executeCheckin(Guest guest) {
        if(guestEntityRepository.existsById(guest.getId()) &&  eventEntityRepository.existsById(guest.getEventId())){
            GuestEntity guestDatabase = guestEntityRepository.getReferenceById(guest.getId());
            guestDatabase.setGuestStatus(GuestStatus.CHECKED_ID);
            Checkin checkin = new Checkin.CheckinBuilder()
                    .builder()
                    .guestId(guest.getId())
                    .eventId(guest.getEventId())
                    .checkinTime(LocalDateTime.now())
                    .device("Cellphone")
                    .build();
            CheckinEntity conversion = new CheckinToEntityJpa(checkin).toEntityJpa();
            CheckinEntity checkinSaved = checkinEntityRepository.save(conversion);
            return checkinSaved.getId();
        } else if (!guestEntityRepository.existsById(guest.getId())) {
            throw new GuestNotFound(EnumCode.GU000.getMessage());
        } else{
            throw new EventNotFound(EnumCode.EV000.getMessage());
        }
    }
}
