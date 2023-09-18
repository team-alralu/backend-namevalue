package com.sk.namevalue.domain.name.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * title        : Test Controller
 * author       : sim
 * date         : 2023-09-17
 * description  : 테스트용 컨트롤러
 */

@RestController
public class TestController {

    @RequestMapping("/main")
    public String hi(){
        return "hi";
    }
}
