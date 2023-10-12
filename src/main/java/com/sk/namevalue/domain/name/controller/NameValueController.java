package com.sk.namevalue.domain.name.controller;

import com.sk.namevalue.domain.name.dto.NameValueDto;
import com.sk.namevalue.domain.name.service.NameValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * title        : Name 컨트롤러
 * author       : sim
 * date         : 2023-10-11
 * description  : Name 컨트롤러 클래스
 */

@RequestMapping("/api/name")
@RequiredArgsConstructor
@RestController
public class NameValueController {

    private final NameValueService nameValueService;

    /**
     * 네임벨류 저장
     * @param request - 요청 Dto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(NameValueDto request){
        nameValueService.save(request);
    }
}
