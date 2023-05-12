package com.ssafy.enjoytrip.room.controller;

import com.ssafy.enjoytrip.room.exception.RoomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RoomControllerAdvice {

    @ExceptionHandler(RoomException.class)
    public ResponseEntity<?> memberExceptionHandler(RoomException e) {
        return ResponseEntity.status(e.getErrorCode()
                                      .getHttpStatus())
                             .body(e.getErrorCode().name() + " " + e.getMessage());
    }

}
