package com.ssafy.enjoytrip.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /* Member Exception */
    LOGIN_ID_DUPLICATED(HttpStatus.CONFLICT, "이미 존재하는 ID 입니다."),
    LOGIN_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "패스워드가 일치하지 않습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, ""),

    /* Room Exception */
    ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 방입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
