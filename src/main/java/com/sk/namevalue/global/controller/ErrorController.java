package com.sk.namevalue.global.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * title        : Error Controller
 * author       : sim
 * date         : 2023-09-18
 * description  : 에러에 대한 핸들링 컨트롤러
 */

@Controller
@RequestMapping("/error")
public class ErrorController {

    @GetMapping("/404")
    public String page404(){
        return "error/404";
    }

    @GetMapping("/401")
    public String page401(){
        return "error/401";
    }

}
