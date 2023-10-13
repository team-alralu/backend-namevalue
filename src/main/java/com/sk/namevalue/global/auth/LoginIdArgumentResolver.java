package com.sk.namevalue.global.auth;

import com.sk.namevalue.domain.model.annotation.LoginId;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * title        : LoginId ArgumentResolver
 * author       : sim
 * date         : 2023-09-20
 * description  : SecurityContextHolder 내 Authentication을 취득 후 userId를 추출하여
 *                Controller 메서드의 파라미터 값으로 추가해주는 Method Resolver
 */

@Component
public class LoginIdArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
