package com.sk.namevalue.domain.name.controller;

import com.sk.namevalue.domain.name.dto.NameValueDto;
import com.sk.namevalue.domain.name.service.NameValueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void save(@RequestBody NameValueDto.Save request){
        nameValueService.save(request);
    }

    /**
     * 네임벨류 조회
     * @param request - 요청 Dto
     * @return - 네임벨류 리스트
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NameValueDto.Response> selectList(@RequestBody NameValueDto.Select request){
        return nameValueService.selectList(request);
    }
}
