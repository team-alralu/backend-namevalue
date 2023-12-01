package com.sk.namevalue.domain.name.dto;

import com.sk.namevalue.domain.personality.dto.PersonalityDto;
import com.sk.namevalue.domain.review.dto.ReviewDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
        private final List<Long> personalityList;
        private final int likeability;

        public Save(String personName, String review, List<Long> personalityList, int likeability){
            this.personName = personName;
            this.review = review;
            this.personalityList = personalityList;
            this.likeability = likeability;
        }
    }

    @NoArgsConstructor
    @Getter
    public static class Select{
        private String personName;

        public Select(String personName){
            this.personName = personName;
        }

    }

    @Getter
    public static class Response{

        private final List<ReviewDto> topReviewList;
        private final List<ReviewDto> reviewList;
        private final PersonalityDto representPersonality;

        public Response(List<ReviewDto> topReviewList, List<ReviewDto> reviewList, PersonalityDto representPersonality){
            this.reviewList = reviewList;
            this.topReviewList = topReviewList;
            this.representPersonality = representPersonality;
        }
    }
}

