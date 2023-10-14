package com.sk.namevalue.domain.favorite.dto;

import lombok.Getter;

/**
 * title        :
 * author       : sim
 * date         : 2023-10-14
 * description  :
 */

@Getter
public class FavoriteDto {

    private final Long favoriteId;
    private final String name;
    private final String emoji;

    private FavoriteDto(Long favoriteId, String name, String emoji) {
        this.favoriteId = favoriteId;
        this.name = name;
        this.emoji = emoji;
    }

    public FavoriteDto of(Long favoriteId, String name, String emoji) {
        return new FavoriteDto(favoriteId, name, emoji);
    }
}
