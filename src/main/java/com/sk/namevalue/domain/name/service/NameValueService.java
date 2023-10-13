package com.sk.namevalue.domain.name.service;

import com.sk.namevalue.domain.animal.domain.AnimalEntity;
import com.sk.namevalue.domain.animal.repository.AnimalRepository;
import com.sk.namevalue.domain.favorite.domain.FavoriteEntity;
import com.sk.namevalue.domain.favorite.repository.FavoriteRepository;
import com.sk.namevalue.domain.name.entity.PersonNameEntity;
import com.sk.namevalue.domain.review.entity.ReviewEntity;
import com.sk.namevalue.domain.name.dto.NameValueDto;
import com.sk.namevalue.domain.name.repository.PersonNameRepository;
import com.sk.namevalue.domain.personality.domain.PersonalityEntity;
import com.sk.namevalue.domain.personality.repository.PersonalityRepository;
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
    private final FavoriteRepository favoriteRepository;
    private final PersonalityRepository personalityRepository;

    /**
     * 네임벨류 저장
     * @param request - 요청 Dto
     */
    public void save(NameValueDto.Save request){

        String personName = request.getPersonName();

        PersonNameEntity personNameEntity = PersonNameEntity.from(personName);

        List<AnimalEntity> animalEntityList = animalRepository.findAllById(request.getAnimalList());
        List<FavoriteEntity> favoriteEntityList = favoriteRepository.findAllById(request.getFavoriteList());
        List<PersonalityEntity> personalityEntityList = personalityRepository.findAllById(request.getPersonalityList());

        personNameEntity.addReview(request.getReview());
        animalEntityList.forEach(personNameEntity::addAnimal);
        favoriteEntityList.forEach(personNameEntity::addFavorite);
        personalityEntityList.forEach(personNameEntity::addPersonality);

        personNameRepository.save(personNameEntity);

        log.info("네임벨류 저장이 완료되었습니다.");
    }

    /**
     * 네임벨류 조회
     * @param request - 요청 Dto
     */
    public List<NameValueDto.Response> selectList(NameValueDto.Select request) {

        PersonNameEntity findPersonNameEntity = personNameRepository.findById(request.getPersonName())
                .orElseThrow(() -> new DataNotFoundException(ErrorMessage.PERSON_NAME_NOT_FOUND));

        List<ReviewEntity> reviewEntityList = findPersonNameEntity.getReviewList();
        // TODO : 로직 개발

        return null;
    }
}
