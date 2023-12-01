package com.sk.namevalue.domain.personality.service;

import com.sk.namevalue.config.fixture.TestFixture;
import com.sk.namevalue.domain.personality.domain.PersonalityEntity;
import com.sk.namevalue.domain.personality.repository.PersonalityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * title        :
 * author       : sim
 * date         : 2023-12-01
 * description  :
 */
class PersonalityServiceTest extends TestFixture {

    private final PersonalityRepository personalityRepository = mock(PersonalityRepository.class);
    private final PersonalityService personalityService = new PersonalityService(personalityRepository);
    @Test
    @DisplayName("성격 리스트 조회")
    void getPersonalityList() {

        List<PersonalityEntity> list = PERSONALITY_DTO_LIST.stream().map(
                dto -> PersonalityEntity.of(dto.getPersonalityId(), dto.getName(), dto.getPath()))
                .collect(Collectors.toList());

        given(personalityRepository.findAll()).willReturn(list);
        assertThat(personalityService.getPersonalityList())
                .usingRecursiveComparison().isEqualTo(PERSONALITY_DTO_LIST);
    }
}