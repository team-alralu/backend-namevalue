package com.sk.namevalue.config;

import com.sk.namevalue.global.auth.AuthenticationFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

/**
 * title        : 테스트에 대한 SecurityContext 팩터리 클래스
 * author       : sim
 * date         : 2023-10-13
 * description  : 테스트에 사용할 Authentication
 */
public class TestSecurityContextFactory implements WithSecurityContextFactory<TestUser> {
    @Override
    public SecurityContext createSecurityContext(TestUser annotation) {

        SecurityContext securityContext = SecurityContextHolder.getContext();

        if(annotation.value() == TestUserType.VALID){
            securityContext.setAuthentication(AuthenticationFactory.of(TestUserType.VALID.getLoginId(), TestUserType.VALID.getEmail()));
        }
        return securityContext;
    }
}
