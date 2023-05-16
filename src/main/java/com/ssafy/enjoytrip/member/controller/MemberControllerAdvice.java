package com.ssafy.enjoytrip.member.controller;

import com.ssafy.enjoytrip.member.exception.MemberException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.ssafy.enjoytrip.member.controller")
public class MemberControllerAdvice {
    
    @ExceptionHandler(MemberException.class)
    public ResponseEntity<?> memberExceptionHandler(MemberException e) {
        return ResponseEntity.status(e.getErrorCode()
                                      .getHttpStatus())
                             .body(e.getErrorCode().name() + " " + e.getMessage());
    }
}
