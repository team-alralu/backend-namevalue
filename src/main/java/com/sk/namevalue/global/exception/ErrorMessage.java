package com.sk.namevalue.global.exception;

import lombok.Getter;

/**
 * title        : 에러메시지
 * author       : sim
 * date         : 2023-10-13
 * description  : 에러메시지
 */

@Getter
public enum ErrorMessage {

    PERSON_NAME_NOT_FOUND("이름에 대한 데이터가 존재하지 않습니다. 다른 이름으로 시도해주세요.")
    ,INVALID_REVIEW("유효하지 않는 리뷰입니다. 새로고침 후 다시 시도해주세요.")
    ,INVALID_USER("유효하지 않는 사용자입니다. 다시 로그인 해주세요.")
    ,INVALID_VALUE_OF_POINT("점수에 대한 가치 데이터가 존재하지 않습니다. 관리자에게 문의해주세요.")
    ,IMPOSSIBLE_SAVE_DATE("이름 저장은 한 시간에 한번만 가능합니다. 일정 시간 후 다시 시도해주세요.")
    ,EXPIRED_JWT_TOKEN("토큰이 만료되었습니다. 다시 로그인 해주세요.")
    ,UNSUPPORTED_JWT_TOKEN("지원하지 않는 토큰입니다. 다시 로그인 해주세요.")
    ,MALFORMED_JWT_TOKEN("잘못된 형식의 토큰입니다. 다시 로그인 해주세요.")
    ,FORGERY_SIGNATURE_JWT_TOKEN("위조된 요청입니다. 다시 로그인하세요.")
    ,FAIL_PARSING_JWT_TOKEN("토큰 파싱 중 에러가 발생했습니다. 관리자에게 문의해주세요.")
    ,NON_AUTHORIZATION_HEADER("Authorization 헤더가 없습니다. 관리자에게 문의해주세요.")
    ,WRONG_AUTHORIZATION_HEADER("잘못된 인증 헤더입니다. 다시 로그인해주세요");

    private final String message;

    ErrorMessage(String message){
        this.message = message;
    }
}
