package com.sk.namevalue.domain.personality.repository;

import com.sk.namevalue.domain.personality.domain.PersonalityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * title        :
 * author       : sim
 * date         : 2023-10-12
 * description  :
 */
public interface PersonalityRepository extends JpaRepository<PersonalityEntity, Long> {
}
