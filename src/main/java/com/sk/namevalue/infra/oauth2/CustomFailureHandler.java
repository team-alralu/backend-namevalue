package com.sk.namevalue.infra.oauth2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * title        : CustomFailureHandler
 * author       : sim
 * date         : 2023-09-06
 * description  : 인증/인가 처리 실패에 대한 핸들러 클래스
 */

@Component
@Slf4j
public class CustomFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        log.error(e.getMessage());
        response.sendRedirect("/login?status=fail");
    }
}
