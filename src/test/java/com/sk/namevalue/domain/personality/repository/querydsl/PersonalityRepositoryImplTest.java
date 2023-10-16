package com.sk.namevalue.domain.personality.repository.querydsl;

import com.sk.namevalue.config.TestConfig;
import com.sk.namevalue.config.fixture.TestFixture;
import com.sk.namevalue.domain.personality.dto.PersonalityDto;
import com.sk.namevalue.domain.personality.repository.PersonalityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * title        : PersonalityRepositoryImplTest
 * author       : sim
 * date         : 2023-10-15
 * description  : PersonalityRepositoryImplTest
 */

@DataJpaTest
@Import(TestConfig.class)
class PersonalityRepositoryImplTest extends TestFixture {

    @Autowired
    private PersonalityRepository personalityRepository;

    @DisplayName("사용자의 대표 성격 조회")
    @Test
    void findTopByPersonNameOrderByCount() {

        PersonalityDto result = personalityRepository.findTopByPersonNameOrderByCount(VALID_PERSON_NAME);
        assertThat(result.getPersonalityId()).isEqualTo(1L);
    }
}