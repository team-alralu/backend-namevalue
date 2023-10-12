package com.sk.namevalue.domain.user.dao.querydsl;

import com.sk.namevalue.config.TestConfig;
import com.sk.namevalue.domain.user.dao.UserRepository;
import com.sk.namevalue.domain.user.domain.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


import static com.sk.namevalue.config.TestFixture.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * title        : UserRepositoryImplTest
 * author       : sim
 * date         : 2023-09-06
 * description  : UserRepository QueryDsl 테스트
 */

@DataJpaTest
@Import(TestConfig.class)
class UserRepositoryImplTest{

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 조회 by email")
    void findByEmail() {
        UserEntity userEntity = userRepository.findByEmail(VALID_EMAIL);
        assertThat(userEntity.getEmail()).isEqualTo(VALID_EMAIL);
    }
}