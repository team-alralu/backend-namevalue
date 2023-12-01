package com.sk.namevalue.domain.user.dto;

import com.sk.namevalue.domain.model.enums.MBTI;
import com.sk.namevalue.domain.name.dto.NameValueDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * title        : 필수정보 Dto
 * author       : sim
 * date         : 2023-10-18
 * description  : 필수정보 Dto
 */
public class RequiredInfoDto {

    @NoArgsConstructor
    @Getter
    public static class Request{

        @NotNull(message = "유효하지 않은 MBTI 입니다. 다시 입력해주세요.")
        private MBTI mbti;
        private NameValueDto.Save nameInfo;

        public Request(MBTI mbti, NameValueDto.Save nameInfo){
            this.mbti = mbti;
            this.nameInfo = nameInfo;
        }
    }
}
