package com.br.infrastructure.domain;

import com.br.core.enums.GuestStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_guest")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class GuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;

    private String email;

    private String document;

    @Enumerated(EnumType.STRING)
    private GuestStatus guestStatus;

    private UUID eventId;

    @JsonFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime registeredAt;
}
