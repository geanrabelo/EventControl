package com.br.usecases;

import com.br.core.entities.Guest;

import java.util.UUID;

public interface CheckinUsecases {

    UUID executeCheckin(Guest guest);
}
