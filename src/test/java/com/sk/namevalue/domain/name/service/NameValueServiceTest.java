package com.sk.namevalue.domain.name.service;

import com.sk.namevalue.domain.animal.repository.AnimalRepository;
import com.sk.namevalue.domain.favorite.repository.FavoriteRepository;
import com.sk.namevalue.domain.name.domain.PersonNameEntity;
import com.sk.namevalue.domain.name.repository.PersonNameRepository;
import com.sk.namevalue.domain.personality.repository.PersonalityRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.sk.namevalue.config.TestFixture.*;

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
    private final FavoriteRepository favoriteRepository = mock(FavoriteRepository.class);
    private final PersonalityRepository personalityRepository = mock(PersonalityRepository.class);
    private final NameValueService nameValueService = new NameValueService(personNameRepository, animalRepository, favoriteRepository, personalityRepository);

    @DisplayName("네임벨류 저장")
    @Test
    void saveWithValidDto() {

        given(personNameRepository.findById(NAME_VALUE_DTO.getPersonName()))
                .willReturn(Optional.of(PERSON_NAME_ENTITY));

        nameValueService.save(NAME_VALUE_DTO);

        verify(personNameRepository).save(any(PersonNameEntity.class));
    }
}