package com.sk.namevalue.domain.review.repository.querydsl;

import com.sk.namevalue.config.TestConfig;
import com.sk.namevalue.domain.review.dto.ReviewDto;
import com.sk.namevalue.domain.review.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * title        : ReviewRepositoryImplTest
 * author       : sim
 * date         : 2023-10-14
 * description  : ReviewRepositoryImplTest
 */

@DataJpaTest
@Import(TestConfig.class)
class ReviewRepositoryImplTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @DisplayName("좋아요, 등록날짜순으로 정렬된 상위 리뷰 5건 조회하기")
    @Test
    void findTop5ByPersonNameOrderByLikeCountAndCreateDateDesc() {
        List<ReviewDto> result = reviewRepository.findTop5ByPersonNameOrderByLikeCountAndCreateDateDesc("홍길동");

        assertThat(result.get(0).getReviewId()).isEqualTo(2L);
        assertThat(result.get(1).getReviewId()).isEqualTo(6L);
        assertThat(result.get(2).getReviewId()).isEqualTo(5L);
        assertThat(result.get(3).getReviewId()).isEqualTo(1L);
        assertThat(result.get(4).getReviewId()).isEqualTo(7L);
        assertThat(result.size()).isEqualTo(5);
    }

    @DisplayName("등록날짜순으로 정렬된 모든 리뷰 조회하기")
    @Test
    void findByPersonNameOrderByCreateDateDesc(){
        List<ReviewDto> result = reviewRepository.findByPersonNameOrderByCreateDateDesc("홍길동");

        assertThat(result.get(0).getReviewId()).isEqualTo(7);
        assertThat(result.get(1).getReviewId()).isEqualTo(6);
        assertThat(result.get(2).getReviewId()).isEqualTo(5);
        assertThat(result.get(3).getReviewId()).isEqualTo(4);
        assertThat(result.get(4).getReviewId()).isEqualTo(3);
        assertThat(result.get(5).getReviewId()).isEqualTo(2);
        assertThat(result.get(6).getReviewId()).isEqualTo(1);
        assertThat(result.size()).isEqualTo(7);
    }
}