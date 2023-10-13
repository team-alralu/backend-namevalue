package com.sk.namevalue.domain.like.service;

import com.sk.namevalue.domain.like.entity.LikeEntity;
import com.sk.namevalue.domain.like.repository.LikeRepository;
import com.sk.namevalue.domain.review.entity.ReviewEntity;
import com.sk.namevalue.domain.review.repository.ReviewRepository;
import com.sk.namevalue.domain.user.dao.UserRepository;
import com.sk.namevalue.domain.user.domain.UserEntity;
import com.sk.namevalue.global.exception.DataNotFoundException;
import com.sk.namevalue.global.exception.ErrorMessage;
import com.sk.namevalue.global.exception.InvalidUserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * title        : LikeService
 * author       : sim
 * date         : 2023-10-13
 * description  : LikeService
 */

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    /**
     * 좋아요 등록/삭제
     * 해당 리뷰에 대한 좋아요 데이터가 있는 경우 삭제를, 없는 경우 등록한다.
     * @param userId - 유저 ID
     * @param reviewId - 리뷰 ID
     */
    public void like(Long userId, Long reviewId){

        ReviewEntity reviewEntity = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessage.INVALID_REVIEW));

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new InvalidUserException(ErrorMessage.INVALID_USER));

        LikeEntity likeEntity = likeRepository.findByUserAndReview(userEntity, reviewEntity);

        if(likeEntity == null){
            likeRepository.save(LikeEntity.of(userEntity, reviewEntity));
            log.info("save like - userId : "+userId+", reviewId : "+reviewId);
        }else{
            likeRepository.delete(likeEntity);
            log.info("delete like - userId : "+userId+", reviewId : "+reviewId);
        }
    }
}
