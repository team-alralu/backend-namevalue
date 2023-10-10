package com.sk.namevalue.infra.oauth2;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * title        : UserAuthenticationToken
 * author       : sim
 * date         : 2023-09-18
 * description  : UserAuthenticationToken
 */
public class UserAuthenticationToken extends AbstractAuthenticationToken {

    private final Long principal;
    private final String credentials;


    public UserAuthenticationToken(Long userId, String email, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        super.setAuthenticated(true);
        this.principal = userId;
        this.credentials = email;
    }

    @Override
    public String getCredentials() {
        return credentials;
    }

    @Override
    public Long getPrincipal() {
        return principal;
    }
}
