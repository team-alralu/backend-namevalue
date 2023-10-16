package com.sk.namevalue.global.exception;

/**
 * title        : 처리될 수 없는 상황에 대한 예외
 * author       : sim
 * date         : 2023-10-16
 * description  : 처리될 수 없는 상황에 대한 예외
 */
public class NotProcessException extends RuntimeException{
    public NotProcessException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
    }
}
