package com.sk.namevalue.global.exception;

/**
 * title        : InvalidUser 예외 클래스
 * author       : sim
 * date         : 2023-10-13
 * description  : 유효하지 않는 유저에 대한 예외 클래스
 */
public class InvalidUserException extends RuntimeException{

    public InvalidUserException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
    }
}
