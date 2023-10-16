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
    ,INVALID_USER("유효하지 않는 사용자입니다. 다시 로그인해주세요.")
    ,INVALID_VALUE_OF_POINT("점수에 대한 가치 데이터가 존재하지 않습니다. 관리자에게 문의해주세요.")
    ,IMPOSSIBLE_SAVE_DATE("이름 저장은 한 시간에 한번만 가능합니다. 일정 시간 후 다시 시도해주세요.");

    private final String message;

    ErrorMessage(String message){
        this.message = message;
    }
}
