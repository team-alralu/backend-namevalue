package com.sk.namevalue.domain.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * title        : RefreshToken
 * author       : sim
 * date         : 2023-09-20
 * description  : RefreshToken 어노테이션
 */


@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface RefreshToken {
}
