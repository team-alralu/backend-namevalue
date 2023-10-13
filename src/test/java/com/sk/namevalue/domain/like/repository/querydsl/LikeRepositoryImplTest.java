package com.sk.namevalue.domain.like.repository.querydsl;

import com.sk.namevalue.config.TestConfig;
import com.sk.namevalue.domain.like.entity.LikeEntity;
import com.sk.namevalue.domain.like.repository.LikeRepository;
import com.sk.namevalue.domain.review.entity.ReviewEntity;
import com.sk.namevalue.domain.review.repository.ReviewRepository;
import com.sk.namevalue.domain.user.dao.UserRepository;
import com.sk.namevalue.domain.user.domain.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * title        : LikeRepositoryImplTest
 * author       : sim
 * date         : 2023-10-13
 * description  : LikeRepositoryImplTest
 */

@DataJpaTest
@Import(TestConfig.class)
@TestPropertySource(properties = "spring.config.location=classpath:application.yml")
class LikeRepositoryImplTest {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Test
    void findByUserAndReview() {
        UserEntity userEntity = userRepository.findById(1L).get();
        ReviewEntity reviewEntity = reviewRepository.findById(1L).get();

        LikeEntity result = likeRepository.findByUserAndReview(userEntity, reviewEntity);

        assertThat(result.getLikeId()).isEqualTo(1L);
    }
}