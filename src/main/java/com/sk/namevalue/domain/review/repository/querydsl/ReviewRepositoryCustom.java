package com.sk.namevalue.domain.review.repository.querydsl;

import com.sk.namevalue.domain.review.dto.ReviewDto;

import java.util.List;

/**
 * title        : ReviewRepositoryCustom
 * author       : sim
 * date         : 2023-10-14
 * description  : ReviewRepositoryCustom
 */
public interface ReviewRepositoryCustom {
    List<ReviewDto> findTop5ByPersonNameOrderByLikeCountAndCreateDateDesc(String personName);
    List<ReviewDto> findByPersonNameOrderByCreateDateDesc(String personName);
}
