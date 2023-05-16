package com.ssafy.enjoytrip.reservation.controller;

import com.ssafy.enjoytrip.reservation.exception.ReservationException;
import com.ssafy.enjoytrip.room.exception.RoomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReservationControllerAdvice {

    @ExceptionHandler(ReservationException.class)
    public ResponseEntity<?> reservationExceptionHandler(ReservationException e) {
        return ResponseEntity.status(e.getErrorCode()
                                      .getHttpStatus())
                             .body(e.getErrorCode().name() + " " + e.getMessage());
    }

    @ExceptionHandler(RoomException.class)
    public ResponseEntity<?> roomExceptionHandler(RoomException e) {
        return ResponseEntity.status(e.getErrorCode()
                                      .getHttpStatus())
                             .body(e.getErrorCode().name() + " " + e.getMessage());
    }
}
