package com.br.infrastructure.service.impl;

import com.br.core.entities.Guest;
import com.br.infrastructure.dto.checkin.CheckinCreation;
import com.br.infrastructure.service.CheckinService;
import com.br.usecases.CheckinUsecases;
import com.br.usecases.GuestUsecases;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CheckinServiceImpl implements CheckinService {

    private final CheckinUsecases checkinUsecases;
    private final GuestUsecases guestUsecases;

    public CheckinServiceImpl(CheckinUsecases checkinUsecases, GuestUsecases guestUsecases){
        this.checkinUsecases = checkinUsecases;
        this.guestUsecases = guestUsecases;
    }

    @Override
    public String executeCheckin(CheckinCreation checkinCreation) {
        Guest guest = guestUsecases.findById(checkinCreation.guestId());
        UUID id = checkinUsecases.executeCheckin(guest);
        return "Checkin registered successfully - UUID: "+id;
    }
}
