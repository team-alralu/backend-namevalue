package com.sk.namevalue.global.auth;

import com.sk.namevalue.domain.model.annotation.RefreshToken;
import com.sk.namevalue.domain.model.enums.Token;
import com.sk.namevalue.global.exception.JwtTokenException;
import com.sk.namevalue.global.util.HttpUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * title        : JwtArgumentResolver
 * author       : sim
 * date         : 2023-09-20
 * description  : Cookie의 JWT RefreshToken을 Controller 메서드 파라미터에 설정하는 ArgumentResolver
 */

@Component
public class JwtArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RefreshToken.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        String token = HttpUtil.getValueOfCookies(request.getCookies(), Token.REFRESH_TOKEN.getKey());
        if(token.isEmpty()){
            throw new JwtTokenException("토큰이 만료되었습니다. 다시 로그인해주세요");
        }
        return token;
    }
}
