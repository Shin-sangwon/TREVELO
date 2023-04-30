package com.ssafy.enjoytrip.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    LOGIN_ID_DUPLICATED(HttpStatus.CONFLICT, ""),
    LOGIN_ID_NOT_FOUND(HttpStatus.NOT_FOUND, ""),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "");

    private final HttpStatus httpStatus;
    private final String message;
}
