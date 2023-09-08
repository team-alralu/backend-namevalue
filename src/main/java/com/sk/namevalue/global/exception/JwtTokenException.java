package com.sk.namevalue.global.exception;

/**
 * title        : JwtTokenException
 * author       : sim
 * date         : 2023-09-08
 * description  : JWT 토큰 관련 예외 클래스
 */
public class JwtTokenException extends RuntimeException{
    public JwtTokenException(String message){
        super(message);
    }
}
