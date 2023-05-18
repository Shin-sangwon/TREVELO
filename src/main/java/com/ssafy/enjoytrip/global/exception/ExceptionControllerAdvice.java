package com.ssafy.enjoytrip.global.exception;

import com.ssafy.enjoytrip.member.exception.MemberException;
import com.ssafy.enjoytrip.reservation.exception.ReservationException;
import com.ssafy.enjoytrip.room.exception.RoomException;
import com.ssafy.enjoytrip.transaction.Exception.TransactionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<?> memberExceptionHandler(MemberException e) {

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

    @ExceptionHandler(ReservationException.class)
    public ResponseEntity<?> reservationExceptionHandler(ReservationException e) {
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

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<?> transactionExceptionHandler(TransactionException e) {
        return ResponseEntity.status(e.getErrorCode()
                                      .getHttpStatus())
                             .body(e.getErrorCode().name() + " " + e.getMessage());
    }
}
