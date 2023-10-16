package com.sk.namevalue.domain.likeability.repository.querydsl;

/**
 * title        :
 * author       : sim
 * date         : 2023-10-16
 * description  :
 */
public interface LikeabilityRepositoryCustom {
    int findAvgPointByPersonName(String personName);
}
