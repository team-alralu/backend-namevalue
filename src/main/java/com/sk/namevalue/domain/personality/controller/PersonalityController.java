package com.sk.namevalue.domain.personality.controller;

import com.sk.namevalue.domain.personality.dto.PersonalityDto;
import com.sk.namevalue.domain.personality.service.PersonalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * title        : 성격 컨트롤러
 * author       : sim
 * date         : 2023-12-01
 * description  : 성격 컨트롤러
 */

@RestController
@RequestMapping("/api/personality")
@RequiredArgsConstructor
public class PersonalityController {

    private final PersonalityService personalityService;

    @GetMapping("/list")
    public List<PersonalityDto> getPersonalityList(){

        return personalityService.getPersonalityList();
    }

}
