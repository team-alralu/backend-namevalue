package com.sk.namevalue.domain.name.repository;

import com.sk.namevalue.domain.name.entity.PersonNameEntity;
import com.sk.namevalue.domain.name.repository.querydsl.PersonNameRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * title        : PersonNameRepository
 * author       : sim
 * date         : 2023-10-12
 * description  : PersonNameRepository
 */
public interface PersonNameRepository extends JpaRepository<PersonNameEntity, String>, PersonNameRepositoryCustom {
}
