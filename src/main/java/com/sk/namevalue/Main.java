package com.sk.namevalue;

import com.sk.namevalue.domain.name.domain.PersonNameAnimalEntity;
import com.sk.namevalue.domain.name.domain.PersonNameEntity;
import com.sk.namevalue.domain.name.domain.PersonNamePersonalityEntity;
import com.sk.namevalue.domain.name.domain.ReviewEntity;
import com.sk.namevalue.domain.personality.domain.PersonalityEntity;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.metamodel.Metamodel;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * title        :
 * author       : ${USER}
 * date         : ${DATE}
 * description  :
 */
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("name_value");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        PersonalityEntity personalityEntity1 = PersonalityEntity.createPersonalityEntity("웃음이 많은", ":smile", 2);
        PersonalityEntity personalityEntity2 = PersonalityEntity.createPersonalityEntity("칙칙한", ":dark", -2);

        em.persist(personalityEntity1);
        em.persist(personalityEntity2);

        PersonNameEntity personName = PersonNameEntity.createPersonName("심승경");
        em.persist(personName);
        personName.addPersonality(personalityEntity1);
        personName.addPersonality(personalityEntity2);
        personName.addReview("승경이 너무 멋쪄요");

        em.persist(personName);
        em.flush();
        em.clear();

        System.out.println("------------------------------");

        PersonNameEntity personNameEntities = em.find(PersonNameEntity.class, 1);
        //personNameEntities.getPersonalityList();



        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        //System.out.println(">>>>>>>>"+list.get(0).getPersonality().getName());


        tx.commit();
        em.close();
        emf.close();

        System.out.println("Hello world!");
    }
}