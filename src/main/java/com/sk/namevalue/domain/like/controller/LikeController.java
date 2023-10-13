package com.sk.namevalue.domain.like.controller;

import com.sk.namevalue.domain.like.service.LikeService;
import com.sk.namevalue.domain.model.annotation.LoginId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * title        : LikeController
 * author       : sim
 * date         : 2023-10-13
 * description  : LikeController
 */

@RequiredArgsConstructor
@RequestMapping("/api/like")
@RestController
public class LikeController {

    private final LikeService likeService;

    /**
     * 좋아요 등록 / 삭제
     * @param userId - 유저 ID
     * @param reviewId - 리뷰 ID
     */
    @PostMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void like(@LoginId Long userId, @PathVariable("reviewId") Long reviewId){
        likeService.like(userId, reviewId);
    }
}
