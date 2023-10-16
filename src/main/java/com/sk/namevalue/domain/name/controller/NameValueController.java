package com.sk.namevalue.domain.name.controller;

import com.sk.namevalue.domain.model.annotation.LoginId;
import com.sk.namevalue.domain.name.dto.NameValueDto;
import com.sk.namevalue.domain.name.dto.ValueDto;
import com.sk.namevalue.domain.name.service.NameValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
     * 이름 정보 저장
     * @param request - 요청 Dto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@LoginId Long userId, @RequestBody NameValueDto.Save request){
        nameValueService.save(userId, request);
    }

    /**
     * 이름 정보 조회
     * @param request - 요청 Dto
     * @return - 이름 정보 리스트
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public NameValueDto.Response getNameInfo(@RequestBody NameValueDto.Select request){
        return nameValueService.getNameInfo(request);
    }

    /**
     * 네임벨류 조회
     * @param request - 요청 Dto
     * @return - 네임벨류
     */
    @GetMapping("/value")
    @ResponseStatus(HttpStatus.OK)
    public ValueDto.Response getValue(@RequestBody ValueDto.Request request){
        return nameValueService.getValue(request);
    }
}
