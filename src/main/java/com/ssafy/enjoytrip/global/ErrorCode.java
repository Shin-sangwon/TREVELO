package com.ssafy.enjoytrip.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    LOGIN_ID_DUPLICATED(HttpStatus.CONFLICT, "");

    private final HttpStatus httpStatus;
    private final String message;
}
