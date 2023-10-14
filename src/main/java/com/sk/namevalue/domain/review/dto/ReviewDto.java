package com.sk.namevalue.domain.review.dto;

import lombok.Getter;

/**
 * title        : ReviewDto
 * author       : sim
 * date         : 2023-10-13
 * description  : 리뷰 Dto
 */

@Getter
public class ReviewDto {

    private final Long reviewId;
    private final String review;
    private final int likeCnt;

    public ReviewDto(Long reviewId, String review, int likeCnt){
        this.reviewId = reviewId;
        this.review = review;
        this.likeCnt = likeCnt;
    }
}
