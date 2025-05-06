package com.br.infrastructure.controller;

import com.br.infrastructure.dto.MessageDTO;
import com.br.infrastructure.dto.event.EventCreation;
import com.br.infrastructure.dto.event.EventDetails;
import com.br.infrastructure.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MessageDTO> registeredEvent(@RequestBody @Validated EventCreation eventCreation){
        String message = eventService.createEvent(eventCreation);
        return ResponseEntity.ok(new MessageDTO(message));
    }

    @GetMapping
    public ResponseEntity<List<EventDetails>> findAll(){
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<EventDetails> findById(@RequestParam(name = "id") UUID id){
        return ResponseEntity.ok(eventService.findById(id));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> deleteById(@RequestParam(name = "id") UUID id){
        eventService.cancelEvent(id);
        return ResponseEntity.noContent().build();
    }
}
