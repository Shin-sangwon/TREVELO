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
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "권한이 없습니다."),

    /* Room Exception */
    ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 방입니다."),
    ROOM_PERMISSION_DENIED(HttpStatus.UNAUTHORIZED, "방에 대한 권한이 없습니다."),
    /* File Upload Exception*/
    FILE_UPLOAD_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패하였습니다."),
    /* Reservation Exception */
    ROOM_ALREADY_RESERVED(HttpStatus.BAD_REQUEST, "이미 예약된 방입니다."),
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "예약이 존재하지 않습니다."),
    INSUFFICIENT_MILEAGE(HttpStatus.BAD_REQUEST, "마일리지가 부족합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
