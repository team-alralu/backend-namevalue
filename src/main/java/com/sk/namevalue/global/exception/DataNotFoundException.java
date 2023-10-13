package com.sk.namevalue.global.exception;

/**
 * title        : DataNotFound 예외 클래스
 * author       : sim
 * date         : 2023-10-13
 * description  : 데이터가 존재하지 않을 때 사용하는 예외 클래스
 */
public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
    }
}
