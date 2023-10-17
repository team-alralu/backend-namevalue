package com.sk.namevalue.domain.user.service;

import com.sk.namevalue.config.fixture.TestFixture;
import com.sk.namevalue.domain.name.service.NameValueService;
import com.sk.namevalue.domain.user.dao.UserRepository;
import com.sk.namevalue.domain.user.domain.UserEntity;
import com.sk.namevalue.global.exception.NotProcessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * title        : UserServiceTest
 * author       : sim
 * date         : 2023-10-18
 * description  : UserServiceTest
 */
class UserServiceTest extends TestFixture {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final NameValueService nameValueService = mock(NameValueService.class);

    private final UserService userService = new UserService(userRepository, nameValueService);
    @Test
    @DisplayName("유효 ID에 대한 필수 정보 등록")
    void regRequiredInfoWithValidUser() {
        given(userRepository.findById(VALID_USER_ID))
                .willReturn(Optional.of(VALID_USER_ENTITY));
        userService.regRequiredInfo(VALID_USER_ID, REQUIRED_INFO_REQUEST_DTO);

        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    @DisplayName("이미 필수정보를 등록한 유저의 필수 정보 등록")
    void regRequiredInfoWithAlreadyRegUser() {

        VALID_USER_ENTITY.updateRequiredInfoRegFlag(true);

        given(userRepository.findById(VALID_USER_ID))
                .willReturn(Optional.of(VALID_USER_ENTITY));

        assertThatThrownBy(() -> userService.regRequiredInfo(VALID_USER_ID, REQUIRED_INFO_REQUEST_DTO))
                .isInstanceOf(NotProcessException.class);

        verify(userRepository,never()).save(any(UserEntity.class));
    }
}