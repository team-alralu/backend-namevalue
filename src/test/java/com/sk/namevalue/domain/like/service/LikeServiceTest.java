package com.sk.namevalue.domain.like.service;

import com.sk.namevalue.config.fixture.TestFixture;
import com.sk.namevalue.domain.like.dto.LikePayload;
import com.sk.namevalue.domain.like.entity.LikeEntity;
import com.sk.namevalue.domain.like.repository.LikeRepository;
import com.sk.namevalue.domain.review.entity.ReviewEntity;
import com.sk.namevalue.domain.review.repository.ReviewRepository;
import com.sk.namevalue.domain.user.dao.UserRepository;
import com.sk.namevalue.domain.user.domain.UserEntity;
import com.sk.namevalue.global.exception.DataNotFoundException;
import com.sk.namevalue.global.exception.InvalidUserException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * title        : LikeServiceTest
 * author       : sim
 * date         : 2023-11-29
 * description  : LikeServiceTest
 */
class LikeServiceTest extends TestFixture {

    private final LikeRepository likeRepository = mock(LikeRepository.class);
    private final ReviewRepository reviewRepository = mock(ReviewRepository.class);
    private final UserRepository userRepository = mock(UserRepository.class);
    private final SimpMessagingTemplate messagingTemplate = mock(SimpMessagingTemplate.class);
    private final LikeService likeService = new LikeService(
            likeRepository, reviewRepository, userRepository, messagingTemplate);
    @Test
    @DisplayName("좋아요 추가")
    void addLike() {

        ReviewEntity reviewEntity = ReviewEntity.of(VALID_PERSON_NAME_ENTITY, "홍길동님은 밥을 너무 쿰척쿰척 먹습니다.");
        given(reviewRepository.findById(any(Long.class))).willReturn(Optional.of(reviewEntity));

        List<LikeEntity> likeList = reviewEntity.getLikeList();
        LikeEntity likeEntity = LikeEntity.of(VALID_USER_ENTITY, reviewEntity);
        likeList.add(likeEntity);

        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(VALID_USER_ENTITY));

        likeService.like(1L, 1L);
        verify(likeRepository).save(any(LikeEntity.class));
        verify(messagingTemplate).convertAndSend(any(String.class), any(LikePayload.class));

    }

    @Test
    @DisplayName("좋아요 삭제")
    void removeLike(){
        ReviewEntity reviewEntity = ReviewEntity.of(VALID_PERSON_NAME_ENTITY, "홍길동님은 밥을 너무 쿰척쿰척 먹습니다.");
        given(reviewRepository.findById(any(Long.class))).willReturn(Optional.of(reviewEntity));

        List<LikeEntity> likeList = reviewEntity.getLikeList();
        LikeEntity likeEntity = LikeEntity.of(VALID_USER_ENTITY, reviewEntity);
        likeList.add(likeEntity);

        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(VALID_USER_ENTITY));

        given(likeRepository.findByUserAndReview(any(UserEntity.class), any(ReviewEntity.class)))
                .willReturn(likeEntity);

        likeService.like(1L, 1L);
        verify(likeRepository).delete(any(LikeEntity.class));
        verify(messagingTemplate).convertAndSend(any(String.class), any(LikePayload.class));
    }

    @Test
    @DisplayName("존재하지 않는 리뷰에 대한 좋아요 추가 및 삭제")
    void likeWithInvalidReview(){
        given(reviewRepository.findById(any(Long.class))).willReturn(Optional.empty());

        assertThatThrownBy(() -> likeService.like(1L, 1L))
                .isInstanceOf(DataNotFoundException.class);
    }

    @Test
    @DisplayName("존재하지 않는 유저의 좋아요 추가 및 삭제")
    void likeWithInvalidUser(){
        ReviewEntity reviewEntity = ReviewEntity.of(VALID_PERSON_NAME_ENTITY, "홍길동님은 밥을 너무 쿰척쿰척 먹습니다.");

        given(reviewRepository.findById(any(Long.class))).willReturn(Optional.of(reviewEntity));

        given(userRepository.findById(any(Long.class))).willReturn(
                Optional.empty());

        assertThatThrownBy(() -> likeService.like(1L, 1L))
                .isInstanceOf(InvalidUserException.class);
    }

}