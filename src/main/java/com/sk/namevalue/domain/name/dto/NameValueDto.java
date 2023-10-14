package com.sk.namevalue.domain.name.dto;

import com.sk.namevalue.domain.animal.dto.AnimalDto;
import com.sk.namevalue.domain.favorite.dto.FavoriteDto;
import com.sk.namevalue.domain.personality.dto.PersonalityDto;
import com.sk.namevalue.domain.review.dto.ReviewDto;
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
        private final String personName;

        private Select(String personName){
            this.personName = personName;
        }

        public static Select of(String personName){
            return new Select(personName);
        }
    }

    @Getter
    public static class Response{

        private final List<ReviewDto> topReviewList;
        private final List<ReviewDto> reviewList;
        private final AnimalDto representAnimal;
        private final PersonalityDto representPersonality;
        private final FavoriteDto representFavorite;

        private Response(List<ReviewDto> topReviewList, List<ReviewDto> reviewList
                , AnimalDto representAnimal, PersonalityDto representPersonality, FavoriteDto representFavorite){
            this.reviewList = reviewList;
            this.topReviewList = topReviewList;
            this.representAnimal = representAnimal;
            this.representPersonality = representPersonality;
            this.representFavorite = representFavorite;
        }

        public static Response of(List<ReviewDto> topReviewList, List<ReviewDto> reviewList
                , AnimalDto representAnimal, PersonalityDto representPersonality, FavoriteDto representFavorite){
            return new Response(topReviewList, reviewList, representAnimal, representPersonality, representFavorite);
        }
    }
}

