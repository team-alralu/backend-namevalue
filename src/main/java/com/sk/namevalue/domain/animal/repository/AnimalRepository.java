package com.sk.namevalue.domain.animal.repository;

import com.sk.namevalue.domain.animal.domain.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * title        : AnimalRepository
 * author       : sim
 * date         : 2023-10-12
 * description  : AnimalRepository
 */
public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {
}
