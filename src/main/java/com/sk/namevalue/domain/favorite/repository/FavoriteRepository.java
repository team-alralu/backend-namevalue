package com.sk.namevalue.domain.favorite.repository;

import com.sk.namevalue.domain.favorite.domain.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * title        :
 * author       : sim
 * date         : 2023-10-12
 * description  :
 */
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {
}
