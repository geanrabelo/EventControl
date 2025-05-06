package com.br.infrastructure.repositories;

import com.br.infrastructure.domain.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventEntityRepository extends JpaRepository<EventEntity, UUID> {

    boolean existsById(UUID id);
}
