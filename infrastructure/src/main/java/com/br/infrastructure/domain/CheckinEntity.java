package com.br.infrastructure.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_checkin")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class CheckinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private UUID guestId;

    private UUID eventId;

    @JsonFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime checkinTime;

    private String device;
}
