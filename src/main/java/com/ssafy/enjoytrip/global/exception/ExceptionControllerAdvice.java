package com.ssafy.enjoytrip.global.exception;

import com.ssafy.enjoytrip.member.exception.MemberException;
import com.ssafy.enjoytrip.payment.exception.PaymentException;
import com.ssafy.enjoytrip.reservation.exception.ReservationException;
import com.ssafy.enjoytrip.room.exception.RoomException;
import com.ssafy.enjoytrip.transaction.Exception.TransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<?> memberExceptionHandler(MemberException e) {
        log.error("MemberException occurred: '{}'", e.getMessage(), e);
        return ResponseEntity.status(e.getErrorCode()
                                      .getHttpStatus())
                             .body(e.getErrorCode().name() + " " + e.getMessage());
    }

    @ExceptionHandler(RoomException.class)
    public ResponseEntity<?> roomExceptionHandler(RoomException e) {
        log.error("RoomException occurred: '{}'", e.getMessage(), e);
        return ResponseEntity.status(e.getErrorCode()
                                      .getHttpStatus())
                             .body(e.getErrorCode().name() + " " + e.getMessage());
    }

    @ExceptionHandler(ReservationException.class)
    public ResponseEntity<?> reservationExceptionHandler(ReservationException e) {
        log.error("ReservationException occurred: '{}'", e.getMessage(), e);
        return ResponseEntity.status(e.getErrorCode()
                                      .getHttpStatus())
                             .body(e.getErrorCode().name() + " " + e.getMessage());
    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<?> amazonExceptionHandler(AmazonS3Exception e) {
        log.error("AmazonS3Exception occurred: '{}'", e.getMessage(), e);
        return ResponseEntity.status(e.getErrorCode()
                                      .getHttpStatus())
                             .body(e.getErrorCode().name() + " " + e.getMessage());
    }

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<?> transactionExceptionHandler(TransactionException e) {
        log.error("TransactionException occurred: '{}'", e.getMessage(), e);
        return ResponseEntity.status(e.getErrorCode()
                                      .getHttpStatus())
                             .body(e.getErrorCode().name() + " " + e.getMessage());
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<?> paymentExceptionHandler(PaymentException e) {
        log.error("PaymentException occurred: '{}'", e.getMessage(), e);
        return ResponseEntity.status(e.getErrorCode()
                                      .getHttpStatus())
                             .body(e.getErrorCode().name() + " " + e.getMessage());
    }
}
