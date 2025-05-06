package com.br.application.gateway;

import com.br.core.entities.Guest;

import java.util.UUID;

public interface CheckinGateway {

    UUID executeCheckin(Guest guest);

}
