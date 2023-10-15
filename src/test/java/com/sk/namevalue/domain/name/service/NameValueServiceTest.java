package com.sk.namevalue.domain.name.service;

import com.sk.namevalue.domain.animal.repository.AnimalRepository;
import com.sk.namevalue.domain.name.entity.PersonNameEntity;
import com.sk.namevalue.domain.name.dto.NameValueDto;
import com.sk.namevalue.domain.name.repository.PersonNameRepository;
import com.sk.namevalue.domain.personality.repository.PersonalityRepository;
import com.sk.namevalue.domain.review.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.sk.namevalue.config.fixture.TestFixture.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * title        :
 * author       : sim
 * date         : 2023-10-12
 * description  :
 */
class NameValueServiceTest {

    private final PersonNameRepository personNameRepository = mock(PersonNameRepository.class);
    private final AnimalRepository animalRepository = mock(AnimalRepository.class);
    private final PersonalityRepository personalityRepository = mock(PersonalityRepository.class);
    private final ReviewRepository reviewRepository = mock(ReviewRepository.class);
    private final NameValueService nameValueService = new NameValueService(personNameRepository, animalRepository,  personalityRepository,reviewRepository);

    @DisplayName("네임벨류 저장")
    @Test
    void saveWithValidDto() {

        given(personNameRepository.findById(NAME_VALUE_SAVE_DTO.getPersonName()))
                .willReturn(Optional.of(PERSON_NAME_ENTITY));

        nameValueService.save(NAME_VALUE_SAVE_DTO);

        verify(personNameRepository).save(any(PersonNameEntity.class));
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