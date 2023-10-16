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
import com.sk.namevalue.global.exception.DataNotFoundException;
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

        given(personNameRepository.findById(VALID_PERSON_NAME))
                .willReturn(Optional.of(VALID_PERSON_NAME_ENTITY));

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

    @DisplayName("유효 이름에 대한 이름 정보 조회")
    @Test
    void getNameInfoWithValidPersonName(){

        given(personNameRepository.findById(VALID_PERSON_NAME))
                .willReturn(Optional.of(VALID_PERSON_NAME_ENTITY));

        given(reviewRepository.findTop5ByPersonNameOrderByLikeCountAndCreateDateDesc(VALID_PERSON_NAME))
                .willReturn(TOP_REVIEW_DTO_LIST);

        given(reviewRepository.findByPersonNameOrderByCreateDateDesc(VALID_PERSON_NAME))
                .willReturn(REVIEW_DTO_LIST);

        given(animalRepository.findTopByPersonNameOrderByCount(VALID_PERSON_NAME))
                .willReturn(REPRESENT_ANIMAL_DTO);

        given(personalityRepository.findTopByPersonNameOrderByCount(VALID_PERSON_NAME))
                .willReturn(REPRESENT_PERSONALITY_DTO);

        NameValueDto.Response result = nameValueService.getNameInfo(VALID_NAME_VALUE_SELECT_DTO);

        assertThat(result.getTopReviewList()).isEqualTo(TOP_REVIEW_DTO_LIST);
        assertThat(result.getReviewList()).isEqualTo(REVIEW_DTO_LIST);
        assertThat(result.getRepresentAnimal()).isEqualTo(REPRESENT_ANIMAL_DTO);
        assertThat(result.getRepresentPersonality()).isEqualTo(REPRESENT_PERSONALITY_DTO);
    }

    @DisplayName("유효하지 않은 이름에 대한 이름 정보 조회")
    @Test
    void getNameInfoWithInvalidPersonName(){

        given(personNameRepository.findById(INVALID_PERSON_NAME))
                .willReturn(Optional.empty());

        assertThatThrownBy(() -> nameValueService.getNameInfo(VALID_NAME_VALUE_SELECT_DTO))
                .isInstanceOf(DataNotFoundException.class);
    }

    @DisplayName("가치 조회")
    @Test
    void getValue(){
        given(likeabilityRepository.findAvgPointByPersonName("홍길동"))
                .willReturn(4);
        given(personNameRepository.findValueByLikeabilityPoint(4))
                .willReturn(VALUE_RESPONSE_DTO);

        ValueDto.Response result = nameValueService.getValue(VALUE_REQUEST_DTO);
        assertThat(result).isEqualTo(VALUE_RESPONSE_DTO);
    }
}