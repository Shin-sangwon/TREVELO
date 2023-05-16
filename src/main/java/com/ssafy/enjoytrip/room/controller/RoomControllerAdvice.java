package com.ssafy.enjoytrip.room.controller;

import com.ssafy.enjoytrip.global.exception.AmazonS3Exception;
import com.ssafy.enjoytrip.room.exception.RoomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.ssafy.enjoytrip.room.controller")
public class RoomControllerAdvice {

    @ExceptionHandler(RoomException.class)
    public ResponseEntity<?> roomExceptionHandler(RoomException e) {
        return ResponseEntity.status(e.getErrorCode()
                                      .getHttpStatus())
                             .body(e.getErrorCode().name() + " " + e.getMessage());
    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<?> amazonExceptionHandler(AmazonS3Exception e) {
        return ResponseEntity.status(e.getErrorCode()
                                      .getHttpStatus())
                             .body(e.getErrorCode().name() + " " + e.getMessage());
    }

}
