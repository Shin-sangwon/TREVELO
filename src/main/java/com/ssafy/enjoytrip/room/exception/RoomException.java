package com.ssafy.enjoytrip.room.exception;

import com.ssafy.enjoytrip.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;

}
