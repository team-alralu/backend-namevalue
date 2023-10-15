package com.sk.namevalue.domain.name.service;

import com.sk.namevalue.domain.animal.domain.AnimalEntity;
import com.sk.namevalue.domain.animal.repository.AnimalRepository;
import com.sk.namevalue.domain.name.entity.PersonNameEntity;
import com.sk.namevalue.domain.review.dto.ReviewDto;
import com.sk.namevalue.domain.review.entity.ReviewEntity;
import com.sk.namevalue.domain.name.dto.NameValueDto;
import com.sk.namevalue.domain.name.repository.PersonNameRepository;
import com.sk.namevalue.domain.personality.domain.PersonalityEntity;
import com.sk.namevalue.domain.personality.repository.PersonalityRepository;
import com.sk.namevalue.domain.review.repository.ReviewRepository;
import com.sk.namevalue.global.exception.DataNotFoundException;
import com.sk.namevalue.global.exception.ErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * title        : NameValueService
 * author       : sim
 * date         : 2023-10-11
 * description  : NameValueService
 */

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class NameValueService {

    private final PersonNameRepository personNameRepository;
    private final AnimalRepository animalRepository;
    private final PersonalityRepository personalityRepository;
    private final ReviewRepository reviewRepository;
    /**
     * 네임벨류 저장
     * @param request - 요청 Dto
     */
    public void save(NameValueDto.Save request){

        String personName = request.getPersonName();

        PersonNameEntity personNameEntity = PersonNameEntity.from(personName);

        List<AnimalEntity> animalEntityList = animalRepository.findAllById(request.getAnimalList());
        List<PersonalityEntity> personalityEntityList = personalityRepository.findAllById(request.getPersonalityList());

        personNameEntity.addReview(request.getReview());
        animalEntityList.forEach(personNameEntity::addAnimal);
        personalityEntityList.forEach(personNameEntity::addPersonality);

        personNameRepository.save(personNameEntity);

        log.info("네임벨류 저장이 완료되었습니다.");
    }

    /**
     * 네임벨류 조회
     * @param request - 요청 Dto
     */
    public List<NameValueDto.Response> selectList(NameValueDto.Select request) {

        String personName = request.getPersonName();
        PersonNameEntity findPersonNameEntity = personNameRepository.findById(request.getPersonName())
                .orElseThrow(() -> new DataNotFoundException(ErrorMessage.PERSON_NAME_NOT_FOUND));

        List<ReviewEntity> reviewEntityList = findPersonNameEntity.getReviewList();

        // TODO : 1. 좋아요순 상위 5개 리뷰 조회
        List<ReviewDto> topReviewList = reviewRepository.findTop5ByPersonNameOrderByLikeCountAndCreateDateDesc(personName);

        // TODO : 2. 최신 순 리뷰 조회
        List<ReviewDto> reviewList = reviewRepository.findByPersonNameOrderByCreateDateDesc(personName);

        // TODO : 3. 대표 성격 조회
        //PersonalityDto representPersonality =

        // TODO : 4. 대표 취미 조회

        // TODO : 5. 대표 동물 조회

        return null;
    }
}
