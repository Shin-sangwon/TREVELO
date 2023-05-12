package com.ssafy.enjoytrip.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* Member Exception */
    LOGIN_ID_DUPLICATED(HttpStatus.CONFLICT, ""),
    LOGIN_ID_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, ""),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, ""),

    /* Room Exception */
    ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 방입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
