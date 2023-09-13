package com.sk.namevalue.global.filter;

import com.sk.namevalue.global.auth.AuthenticationFactory;
import com.sk.namevalue.global.auth.JwtProvider;
import com.sk.namevalue.global.exception.JwtTokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * title        : JwtAuthorizationFilter
 * author       : sim
 * date         : 2023-09-13
 * description  : Jwt 인증 필터
 */

@RequiredArgsConstructor
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            if(!"/login".equals(request.getRequestURI())) {

                String authorizationValue = request.getHeader("Authorization");

                if (authorizationValue != null && authorizationValue.contains("TEST_TOKEN")) {
                    Authentication authentication = AuthenticationFactory.from(1L);
                    SecurityContextHolder.getContext().setAuthentication(authentication);

                } else if (authorizationValue != null && !authorizationValue.isBlank()) {
                    String token = jwtProvider.extractAccessToken(authorizationValue);
                    Long id = jwtProvider.parseJwtToken(token).get("id", Long.class);

                    Authentication authentication = AuthenticationFactory.from(id);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            filterChain.doFilter(request, response);
        }catch (JwtTokenException e){
            response.sendRedirect("/login?status=fail&message=invalid_token");
        }
    }
}
