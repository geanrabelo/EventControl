package com.br.infrastructure.controller;

import com.br.infrastructure.dto.MessageDTO;
import com.br.infrastructure.dto.checkin.CheckinCreation;
import com.br.infrastructure.service.CheckinService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/checkin")
public class CheckinController {

    private final CheckinService checkinService;

    public CheckinController(CheckinService checkinService){
        this.checkinService = checkinService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MessageDTO> checkin(@RequestBody @Validated CheckinCreation checkinCreation){
        String message = checkinService.executeCheckin(checkinCreation);
        return ResponseEntity.ok(new MessageDTO(message));
    }
}
