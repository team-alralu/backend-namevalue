package com.sk.namevalue.domain.user.controller;

import com.sk.namevalue.domain.model.annotation.LoginId;
import com.sk.namevalue.domain.user.dto.RequiredInfoDto;
import com.sk.namevalue.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * title        : User Controller
 * author       : sim
 * date         : 2023-10-18
 * description  : USer Controller
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    /**
     * 필수 정보 등록
     * @param loginId - 로그인 ID
     * @param request - 요청 Dto
     */
    @PostMapping("/required")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void regRequiredInfo(@LoginId Long loginId, @RequestBody RequiredInfoDto.Request request){
        userService.regRequiredInfo(loginId, request);
    }
}
