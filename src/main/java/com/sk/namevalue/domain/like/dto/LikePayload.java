package com.sk.namevalue.domain.like.dto;

/**
 * title        :
 * author       : sim
 * date         : 2023-11-29
 * description  :
 */

public class LikePayload {
    private final Long reviewId;

    private final int likeCnt;

    private LikePayload(Long reviewId, int likeCnt) {
        this.reviewId = reviewId;
        this.likeCnt = likeCnt;
    }

    public static LikePayload of(Long reviewId, int likeCnt){
        return new LikePayload(reviewId, likeCnt);
    }

}
