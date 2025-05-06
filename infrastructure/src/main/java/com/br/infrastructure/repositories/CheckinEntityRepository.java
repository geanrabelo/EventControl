package com.br.infrastructure.repositories;

import com.br.infrastructure.domain.CheckinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CheckinEntityRepository extends JpaRepository<CheckinEntity, UUID> {
}
