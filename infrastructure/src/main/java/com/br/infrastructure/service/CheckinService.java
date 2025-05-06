package com.br.infrastructure.service;

import com.br.infrastructure.dto.checkin.CheckinCreation;

import java.util.UUID;

public interface CheckinService {

    String executeCheckin(CheckinCreation checkinCreation);

}
