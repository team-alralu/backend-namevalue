package com.sk.namevalue.domain.like.repository;

import com.sk.namevalue.domain.like.entity.LikeEntity;
import com.sk.namevalue.domain.like.repository.querydsl.LikeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * title        : LikeRepository
 * author       : sim
 * date         : 2023-10-13
 * description  : LikeRepository
 */
public interface LikeRepository extends JpaRepository<LikeEntity, Long>, LikeRepositoryCustom {

}
