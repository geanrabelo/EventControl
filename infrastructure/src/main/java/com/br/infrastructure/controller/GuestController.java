package com.br.infrastructure.controller;

import com.br.infrastructure.dto.MessageDTO;
import com.br.infrastructure.dto.guest.GuestCreation;
import com.br.infrastructure.dto.guest.GuestDetails;
import com.br.infrastructure.service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/guest")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService){
        this.guestService = guestService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MessageDTO> register(@RequestBody @Validated GuestCreation guestCreation){
        String message = guestService.registeredGuest(guestCreation);
        return ResponseEntity.ok(new MessageDTO(message));
    }

    @GetMapping
    public ResponseEntity<List<GuestDetails>> findAll(){
        return ResponseEntity.ok(guestService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<GuestDetails> findById(@RequestParam(name = "id") UUID id){
        return ResponseEntity.ok(guestService.findById(id));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> deleteById(@RequestParam(name = "id") UUID id){
        guestService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
