package com.sk.namevalue.domain.personality.service;

import com.sk.namevalue.domain.personality.dto.PersonalityDto;
import com.sk.namevalue.domain.personality.repository.PersonalityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * title        :
 * author       : sim
 * date         : 2023-12-01
 * description  :
 */

@Service
@RequiredArgsConstructor
public class PersonalityService {

    private final PersonalityRepository personalityRepository;

    public List<PersonalityDto> getPersonalityList(){

        return personalityRepository.findAll().stream()
                .map(e -> PersonalityDto.of(e.getPersonalityId(), e.getName(), e.getPath()))
                .collect(Collectors.toList());
    }
}
