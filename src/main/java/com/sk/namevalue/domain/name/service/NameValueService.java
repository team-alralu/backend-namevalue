package com.sk.namevalue.domain.name.service;

import com.sk.namevalue.domain.animal.domain.AnimalEntity;
import com.sk.namevalue.domain.animal.dto.AnimalDto;
import com.sk.namevalue.domain.animal.repository.AnimalRepository;
import com.sk.namevalue.domain.likeability.repository.LikeabilityRepository;
import com.sk.namevalue.domain.name.dto.ValueDto;
import com.sk.namevalue.domain.name.entity.PersonNameEntity;
import com.sk.namevalue.domain.personality.dto.PersonalityDto;
import com.sk.namevalue.domain.review.dto.ReviewDto;
import com.sk.namevalue.domain.name.dto.NameValueDto;
import com.sk.namevalue.domain.name.repository.PersonNameRepository;
import com.sk.namevalue.domain.personality.domain.PersonalityEntity;
import com.sk.namevalue.domain.personality.repository.PersonalityRepository;
import com.sk.namevalue.domain.review.repository.ReviewRepository;
import com.sk.namevalue.domain.user.dao.UserRepository;
import com.sk.namevalue.domain.user.domain.UserEntity;
import com.sk.namevalue.global.exception.DataNotFoundException;
import com.sk.namevalue.global.exception.ErrorMessage;
import com.sk.namevalue.global.exception.InvalidUserException;
import com.sk.namevalue.global.exception.NotProcessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    private final UserRepository userRepository;
    private final PersonNameRepository personNameRepository;
    private final AnimalRepository animalRepository;
    private final PersonalityRepository personalityRepository;
    private final ReviewRepository reviewRepository;
    private final LikeabilityRepository likeabilityRepository;

    /**
     * 이름 정보 저장
     * 이름에 대한 호감도, 리뷰, 동물, 성격 데이터를 저장한다.
     * 저장 후 마지막 이름 정보 저장 일자를 갱신한다.
     * @param request - 요청 Dto
     */
    public void save(Long userId, NameValueDto.Save request){

        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new InvalidUserException(ErrorMessage.INVALID_USER));

        if(!isRegPossible(userEntity.getLastRegDate())){
            throw new NotProcessException(ErrorMessage.IMPOSSIBLE_SAVE_DATE);
        }
        String personName = request.getPersonName();

        PersonNameEntity personNameEntity = PersonNameEntity.from(personName);

        List<AnimalEntity> animalEntityList = animalRepository.findAllById(request.getAnimalList());
        List<PersonalityEntity> personalityEntityList = personalityRepository.findAllById(request.getPersonalityList());

        personNameEntity.addReview(request.getReview());
        personNameEntity.addLikeability(request.getLikeability());
        animalEntityList.forEach(personNameEntity::addAnimal);
        personalityEntityList.forEach(personNameEntity::addPersonality);

        personNameRepository.save(personNameEntity);
        userEntity.renewLastNameRegDate();

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



    /**
     * @param lastRegDate - 마지막 이름 등록 날짜
     * @return 이름 등록 가능 여부
     */
    private boolean isRegPossible(LocalDateTime lastRegDate){
        return lastRegDate == null ||
                lastRegDate.plusHours(1).isBefore(LocalDateTime.now());
    }
}
