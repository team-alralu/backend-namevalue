package com.sk.namevalue.domain.name.domain;

import com.sk.namevalue.domain.animal.domain.AnimalEntity;
import com.sk.namevalue.domain.favorite.domain.FavoriteEntity;
import com.sk.namevalue.domain.model.BaseEntity;
import com.sk.namevalue.domain.personality.domain.PersonalityEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * title        : 이름 엔티티
 * author       : sim
 * date         : 2023-08-28
 * description  : 사람 이름 엔티티
 */

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_person_name")
public class PersonNameEntity extends BaseEntity {

    @Id
    @Column(name = "person_name")
    private String personName;

    @OneToMany(mappedBy = "personName", cascade = CascadeType.ALL)
    private List<PersonNamePersonalityEntity> personalityList = new ArrayList<>();

    @OneToMany(mappedBy = "personName", cascade = CascadeType.ALL)
    private List<PersonNameAnimalEntity> animalList = new ArrayList<>();

    @OneToMany(mappedBy = "personName", cascade = CascadeType.ALL)
    private List<ReviewEntity> reviewList = new ArrayList<>();

    private PersonNameEntity(String personName){
        this.personName = personName;
    }

    /**
     * PersonNameEntity 정적 팩터리 메서드
     * @param personName - 사람 이름
     * @return PersonNameEntity
     */
    public static PersonNameEntity from(String personName){
        return new PersonNameEntity(personName);
    }
    /**
     * 이름에 대한 한줄평 추가
     * @param content - 한줄평
     * reviewList에 추가만 해주면 영속성 전이에 의해 ReviewEntity에 해당하는 테이블에도 데이터가 INSERT됨.
     */
    public void addReview(String content){
        ReviewEntity reviewEntity = ReviewEntity.createReviewEntity(this, content);
        this.reviewList.add(reviewEntity);
    }

    /**
     * 이름에 대한 동물 추가
     * @param animal - 동물 엔티티
     * animalList에 추가만 해주면 영속성 전이에 의해 PersonNameAnimalEntity에 해당하는 테이블에도 데이터가 INSERT됨.
     */
    public void addAnimal(AnimalEntity animal){
        PersonNameAnimalEntity personNameAnimalEntity = PersonNameAnimalEntity.createPersonNameAnimalEntity(this, animal);
        this.animalList.add(personNameAnimalEntity);
    }

    /**
     * 이름에 대한 성격 추가
     * @param personality - 성격 엔티티
     * personalityList에 추가만 해주면 영속성 전이에 의해 PersonNamePersonalityEntity 해당하는 테이블에도 데이터가 INSERT됨.
     */
    public void addPersonality(PersonalityEntity personality){
        PersonNamePersonalityEntity personNamePersonality = PersonNamePersonalityEntity.createPersonNamePersonalityEntity(this, personality);
        this.personalityList.add(personNamePersonality);
    }

    public static PersonNameEntity createPersonName(String personName){
        return new PersonNameEntity(personName);
    }
}
