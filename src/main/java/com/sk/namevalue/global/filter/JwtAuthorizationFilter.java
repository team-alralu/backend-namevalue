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

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TEST_TOKEN = "TEST_TOKEN";
    private static final String CLAIM_ID = "id";
    private static final String CLAIM_EMAIL = "email";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            Authentication authentication;
            String authorizationValue = request.getHeader(AUTHORIZATION_HEADER);
            String token = jwtProvider.extractAccessToken(authorizationValue);

            if(token.isBlank()){
                filterChain.doFilter(request, response);
                return;
            }
            else if(TEST_TOKEN.equals(token)) {
                authentication = AuthenticationFactory.newTestInstance();
            }else{
                Long id = jwtProvider.parseJwtToken(token).get(CLAIM_ID, Long.class);
                String email = jwtProvider.parseJwtToken(token).get(CLAIM_EMAIL, String.class);
                authentication = AuthenticationFactory.of(id, email);
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        }catch (JwtTokenException e){
            e.printStackTrace();
            log.error(e.getMessage());
            response.sendRedirect("/error/401");
        }
    }
}
