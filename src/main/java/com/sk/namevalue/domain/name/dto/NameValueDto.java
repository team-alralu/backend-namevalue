package com.sk.namevalue.domain.name.dto;

import lombok.Getter;

import java.util.List;

/**
 * title        : 네임벨류 Dto
 * author       : sim
 * date         : 2023-10-11
 * description  : 네임벨류 Dto
 */


public class NameValueDto {

    @Getter
    public static class Save{
        private final String personName;
        private final String review;
        private final List<Long> favoriteList;
        private final List<Long> animalList;
        private final List<Long> personalityList;

        private Save(String personName, String review, List<Long> favoriteList, List<Long> animalList, List<Long> personalityList){
            this.personName = personName;
            this.review = review;
            this.favoriteList = favoriteList;
            this.animalList = animalList;
            this.personalityList = personalityList;
        }

        public static Save of(String personName, String review, List<Long> favoriteList, List<Long> animalList, List<Long> personalityList) {
            return new Save(personName, review, favoriteList, animalList, personalityList);
        }
    }

    @Getter
    public static class Select{
        private String personName;

        private Select(String personName){
            this.personName = personName;
        }

        public static Select of(String personName){
            return new Select(personName);
        }
    }

    @Getter
    public static class Response{

        private Long reviewId;
        private String review;

        private Response(Long reviewId, String review){
            this.reviewId = reviewId;
            this.review = review;
        }

        public static Response of(Long reviewId, String review){
            return new Response(reviewId, review);
        }
    }
}

