package com.sk.namevalue.domain.favorite.domain;

import com.sk.namevalue.domain.model.BaseEntity;
import com.sk.namevalue.domain.name.entity.PersonNameFavoriteEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * title        : 흥미 엔티티
 * author       : sim
 * date         : 2023-08-28
 * description  : 좋아하는 것에 대한 엔티티
 */

@Entity
@Getter
@Table(name = "tbl_favorite")
public class FavoriteEntity extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorite_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "emoji", nullable = false)
    private String emoji;

    @OneToMany(mappedBy = "favorite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PersonNameFavoriteEntity> nameFavoriteList = new ArrayList<>();
}
