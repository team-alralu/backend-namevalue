package com.sk.namevalue.domain.name.repository.querydsl;

import com.sk.namevalue.config.TestConfig;
import com.sk.namevalue.domain.name.dto.ValueDto;
import com.sk.namevalue.domain.name.repository.PersonNameRepository;
import com.sk.namevalue.global.exception.DataNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * title        : PersonNameRepositoryTest
 * author       : sim
 * date         : 2023-10-15
 * description  : PersonNameRepositoryTest
 */

@DataJpaTest
@Import(TestConfig.class)
class PersonNameRepositoryImplTest {

    @Autowired
    private PersonNameRepository personNameRepository;

    @DisplayName("점수에 대한 가치 조회")
    @Test
    void findValueByLikeabilityPoint() {
        ValueDto.Response result = personNameRepository.findValueByLikeabilityPoint(78);
        assertThat(result.getName()).isEqualTo("반짝이는 다이아몬드");
    }


    @DisplayName("유효하지 않은 점수에 대한 가치 조회")
    @Test
    void findValueByLikeabilityPointWithInvalidPoint() {
        assertThatThrownBy(() -> personNameRepository.findValueByLikeabilityPoint(101))
                .isInstanceOf(DataNotFoundException.class);

    }
}