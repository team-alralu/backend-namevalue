package com.sk.namevalue.domain.likeability.repository;

import com.sk.namevalue.domain.likeability.entity.LikeabilityEntity;
import com.sk.namevalue.domain.likeability.repository.querydsl.LikeabilityRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * title        : LikeabilityRepository
 * author       : sim
 * date         : 2023-10-15
 * description  : LikeabilityRepository
 */
public interface LikeabilityRepository extends JpaRepository<LikeabilityEntity, Long>, LikeabilityRepositoryCustom {
}
