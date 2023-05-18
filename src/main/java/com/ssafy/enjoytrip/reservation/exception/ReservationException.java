package com.ssafy.enjoytrip.reservation.exception;

import com.ssafy.enjoytrip.global.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;
}
