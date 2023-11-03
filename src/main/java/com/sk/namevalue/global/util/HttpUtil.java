package com.sk.namevalue.global.util;

import com.sk.namevalue.global.exception.HttpUtilException;

import javax.servlet.http.Cookie;
import java.util.Arrays;

/**
 * title        : HttpUtil
 * author       : sim
 * date         : 2023-09-18
 * description  : HTTP 서블릿 관련 Util 클래스
 */
public class HttpUtil {

    /**
     * 보안(httpOnly, secure)적용된 쿠키 생성하기
     * cookie.setSecure 옵션은 HTTPS 가 적용되면 주석해제
     * @param key - 키
     * @param value - 값
     * @param maxAge - 유효기간 (minute)
     * @return Cookie
     */
    public static Cookie generateSecureCookie(String key, String value, int maxAge){
        Cookie cookie = new Cookie(key, value);
        cookie.setHttpOnly(true);
        //cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        return cookie;
    }

    /**
     * 쿠키 value 추출하기
     * @param cookies - Cookie 배열
     * @param key - 찾으려는 key
     * @return value
     */
    public static String getValueOfCookies(Cookie[] cookies, String key){
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(key))
                .findFirst()
                .orElseThrow(() -> new HttpUtilException(key+" 에 대한 쿠키를 찾을 수 없습니다."))
                .getValue();
    }

}
