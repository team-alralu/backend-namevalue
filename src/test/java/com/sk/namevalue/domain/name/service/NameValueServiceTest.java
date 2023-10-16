package com.sk.namevalue.domain.name.service;

import com.sk.namevalue.domain.animal.repository.AnimalRepository;
import com.sk.namevalue.domain.likeability.repository.LikeabilityRepository;
import com.sk.namevalue.domain.model.enums.OAuthType;
import com.sk.namevalue.domain.name.dto.ValueDto;
import com.sk.namevalue.domain.name.entity.PersonNameEntity;
import com.sk.namevalue.domain.name.dto.NameValueDto;
import com.sk.namevalue.domain.name.repository.PersonNameRepository;
import com.sk.namevalue.domain.personality.repository.PersonalityRepository;
import com.sk.namevalue.domain.review.repository.ReviewRepository;
import com.sk.namevalue.domain.user.dao.UserRepository;
import com.sk.namevalue.domain.user.domain.UserEntity;
import com.sk.namevalue.global.exception.InvalidUserException;
import com.sk.namevalue.global.exception.NotProcessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.sk.namevalue.config.fixture.TestFixture.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * title        : NameValueServiceTest
 * author       : sim
 * date         : 2023-10-12
 * description  : NameValueServiceTest
 */
class NameValueServiceTest {

    private final UserRepository userRepository = mock(UserRepository.class);
    private final PersonNameRepository personNameRepository = mock(PersonNameRepository.class);
    private final AnimalRepository animalRepository = mock(AnimalRepository.class);
    private final PersonalityRepository personalityRepository = mock(PersonalityRepository.class);
    private final ReviewRepository reviewRepository = mock(ReviewRepository.class);
    private final LikeabilityRepository likeabilityRepository = mock(LikeabilityRepository.class);
    private final NameValueService nameValueService = new NameValueService(userRepository, personNameRepository, animalRepository,  personalityRepository,reviewRepository, likeabilityRepository);

    @DisplayName("유효 사용자에 대한 이름 정보 저장")
    @Test
    void saveWithValidUser() {

        given(userRepository.findById(VALID_USER_ID))
                .willReturn(Optional.of(VALID_USER_ENTITY));

        given(personNameRepository.findById(NAME_VALUE_SAVE_DTO.getPersonName()))
                .willReturn(Optional.of(PERSON_NAME_ENTITY));

        nameValueService.save(VALID_USER_ID, NAME_VALUE_SAVE_DTO);

        verify(personNameRepository).save(any(PersonNameEntity.class));
    }

    @DisplayName("유효하지 않은 사용자에 대한 이름 정보 저장")
    @Test
    void saveWithInvalidUser() {

        given(userRepository.findById(INVALID_USER_ID))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> nameValueService.save(INVALID_USER_ID, NAME_VALUE_SAVE_DTO))
                .isInstanceOf(InvalidUserException.class);

        verify(personNameRepository, never()).save(any(PersonNameEntity.class));
    }

    @DisplayName("이름 등록 후 한시간이 채 지나지 않은 사용자에 대한 이름 정보 저장")
    @Test
    void saveWithImpossibleSaveDueToLastRegTime(){
        UserEntity userEntity = UserEntity.of("valid@naver.com", "홍길동", OAuthType.NAVER);
        userEntity.renewLastNameRegDate(); // 마지막 이름 등록 일자 갱신

        given(userRepository.findById(VALID_USER_ID))
                .willReturn(Optional.of(userEntity));

        assertThatThrownBy(() -> nameValueService.save(VALID_USER_ID, NAME_VALUE_SAVE_DTO))
                .isInstanceOf(NotProcessException.class);

        verify(personNameRepository, never()).save(any(PersonNameEntity.class));
    }

    @DisplayName("네임벨류 조회")
    @Test
    void selectList(){

        List<NameValueDto.Response> result = nameValueService.selectList(NAME_VALUE_SELECT_DTO);
/*        assertThat(result.get(0).getReview()).isEqualTo(NAME_VALUE_RESPONSE_DTO.get(0).getReview());
        assertThat(result.get(0).getReview()).isEqualTo(NAME_VALUE_RESPONSE_DTO.get(0).getReview());
        assertThat(result.get(1).getReview()).isEqualTo(NAME_VALUE_RESPONSE_DTO.get(1).getReview());
        assertThat(result.get(1).getReview()).isEqualTo(NAME_VALUE_RESPONSE_DTO.get(1).getReview());*/
    }
}