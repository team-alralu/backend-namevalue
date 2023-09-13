package com.sk.namevalue.global.auth;

import com.sk.namevalue.domain.model.enums.Role;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    public static Authentication from(Long id){
        return new UsernamePasswordAuthenticationToken(
                id
                , ""
                , Collections.singleton(new SimpleGrantedAuthority(Role.ROLE_USER.name()))
        );
    }
}
