package com.sk.namevalue.domain.likeability.repository;

import com.sk.namevalue.config.TestConfig;
import com.sk.namevalue.config.fixture.TestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * title        : LikeabilityRepositoryTest
 * author       : sim
 * date         : 2023-10-15
 * description  : LikeabilityRepositoryTest
 */

@DataJpaTest
@Import(TestConfig.class)
class LikeabilityRepositoryTest extends TestFixture {

    @Autowired
    private LikeabilityRepository likeabilityRepository;

    @DisplayName("사용자에 대한 평균 호감도 조회 (예상 평균 호감도 : 77)")
    @Test
    void findAvgPointByPersonNameWithValidPersonName() {
        int result = likeabilityRepository.findAvgPointByPersonName(VALID_PERSON_NAME);
        assertThat(result).isEqualTo(77);
    }
}