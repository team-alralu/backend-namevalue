package com.sk.namevalue.config;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * title        :
 * author       : sim
 * date         : 2023-10-13
 * description  :
 */

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = TestSecurityContextFactory.class)
public @interface TestUser {
    TestUserType value();
}
