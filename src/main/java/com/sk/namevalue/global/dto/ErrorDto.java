package com.sk.namevalue.global.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * title        : ErrorDto
 * author       : sim
 * date         : 2023-09-20
 * description  : ErrorDto
 */

@RequiredArgsConstructor
@Getter
public class ErrorDto {
    private final String message;
}
