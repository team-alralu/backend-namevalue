package com.sk.namevalue.global.exception;

import com.sk.namevalue.global.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * title        : ExceptionControllerAdvice
 * author       : sim
 * date         : 2023-09-20
 * description  : Controller 예외 핸들러 클래스
 */

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    private static final String UNKNOWN_ERROR_MESSAGE = "시스템에서 알 수 없는 에러가 발생하였습니다. 관리자에게 문의해주세요.";

    /**
     * HttpUtilException 예외 핸들러 메서드
     * @param e - 예외
     * @return - ErrorDto
     */
    @ExceptionHandler(HttpUtilException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto httpUtilExceptionHandler(HttpUtilException e){
        log.error(e.getMessage());
        return new ErrorDto(e.getMessage());
    }

    /**
     * JwtTokenException 예외 핸들러 메서드
     * @param e - 예외
     * @return - ErrorDto
     */
    @ExceptionHandler(JwtTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDto jwtTokenExceptionHandler(JwtTokenException e){
        log.error(e.getMessage());
        return new ErrorDto(e.getMessage());
    }

    /**
     * Exception 예외 핸들러 메서드
     * @param e - 예외
     * @return - ErrorDto
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto jwtTokenExceptionHandler(Exception e){
        log.error(e.getMessage());
        return new ErrorDto(UNKNOWN_ERROR_MESSAGE);
    }
}