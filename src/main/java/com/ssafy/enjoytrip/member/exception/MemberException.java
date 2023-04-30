package com.ssafy.enjoytrip.member.exception;

import com.ssafy.enjoytrip.global.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;

}
