package com.sk.namevalue.global.auth;

import com.sk.namevalue.domain.model.enums.Role;
import com.sk.namevalue.infra.oauth2.UserAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

/**
 * title        : Authentication Factory
 * author       : sim
 * date         : 2023-09-13
 * description  : Authentication 객체 생성 클래스
 */
public class AuthenticationFactory {

    private static final Long TEST_USER_ID = 1L;
    private static final String TEST_USER_EMAIL = "tlatmsrud@naver.com";

    public static Authentication of(Long userId, String email){
        return new UserAuthenticationToken(
                userId
                , email
                , Collections.singleton(new SimpleGrantedAuthority(Role.ROLE_USER.name()))
        );
    }

    public static Authentication newTestInstance(){
        return new UserAuthenticationToken(
                TEST_USER_ID
                ,TEST_USER_EMAIL
                ,Collections.singleton(new SimpleGrantedAuthority(Role.ROLE_USER.name()))
        );
    }
}
