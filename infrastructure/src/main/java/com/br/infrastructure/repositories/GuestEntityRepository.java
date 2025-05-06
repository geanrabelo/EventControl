package com.br.infrastructure.repositories;

import com.br.infrastructure.domain.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GuestEntityRepository extends JpaRepository<GuestEntity, UUID> {

    boolean existsById(UUID id);
}
