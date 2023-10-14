package com.sk.namevalue.domain.review.repository;

import com.sk.namevalue.domain.review.entity.ReviewEntity;
import com.sk.namevalue.domain.review.repository.querydsl.ReviewRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * title        : ReviewRepository
 * author       : sim
 * date         : 2023-10-13
 * description  : ReviewRepository
 */
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>, ReviewRepositoryCustom {
}
