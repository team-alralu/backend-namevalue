package com.sk.namevalue.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * title        : LoginController
 * author       : sim
 * date         : 2023-09-18
 * description  : 로그인 페이지 컨트롤러
 */

@Controller
@RequestMapping("/view")
public class ViewController {

    @GetMapping("/login")
    public String loginPage(){
        return "/view/login";
    }

    @GetMapping("/test")
    public String testPage(){
        return "/view/test";
    }
}
