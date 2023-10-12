package com.sk.namevalue.domain.name.repository;

import com.sk.namevalue.domain.name.domain.PersonNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * title        :
 * author       : sim
 * date         : 2023-10-12
 * description  :
 */
public interface PersonNameRepository extends JpaRepository<PersonNameEntity, String> {
}
