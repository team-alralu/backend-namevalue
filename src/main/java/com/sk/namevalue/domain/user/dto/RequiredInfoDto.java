package com.sk.namevalue.domain.user.dto;

import com.sk.namevalue.domain.model.enums.MBTI;
import com.sk.namevalue.domain.name.dto.NameValueDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
        private MBTI mbti;
        private NameValueDto.Save nameInfo;

        public Request(MBTI mbti, NameValueDto.Save nameInfo){
            this.mbti = mbti;
            this.nameInfo = nameInfo;
        }
    }
}
