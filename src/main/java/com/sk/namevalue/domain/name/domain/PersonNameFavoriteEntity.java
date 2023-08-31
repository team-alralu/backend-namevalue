package com.sk.namevalue.domain.name.domain;

import com.sk.namevalue.domain.favorite.domain.FavoriteEntity;
import com.sk.namevalue.domain.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * title        : 이름_흥미 엔티티
 * author       : sim
 * date         : 2023-08-28
 * description  : 이름_흥미 엔티티
 */

@Entity
@Getter
@Table(name = "tbl_person_name_favorite")
public class PersonNameFavoriteEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "name_favorite_id")
    private Long nameFavoriteId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_name")
    private PersonNameEntity personName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "favorite_id")
    private FavoriteEntity favorite;
}
