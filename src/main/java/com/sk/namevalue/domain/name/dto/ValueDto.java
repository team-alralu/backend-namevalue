package com.sk.namevalue.domain.name.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * title        : 가치 Dto
 * author       : sim
 * date         : 2023-10-15
 * description  : 가치 Dto 클래스
 */

public class ValueDto {

    @NoArgsConstructor
    @Getter
    public static class Request{
        private String personName;

        public Request(String personName){
            this.personName = personName;
        }
    }

    @Getter
    public static class Response{
        private final String name;
        private final double point;

        public Response(String name, double point){
            this.name = name;
            this.point = point;
        }
    }
}
