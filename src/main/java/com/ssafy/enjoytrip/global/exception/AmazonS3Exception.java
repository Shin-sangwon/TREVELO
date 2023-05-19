package com.ssafy.enjoytrip.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AmazonS3Exception extends RuntimeException{

    private ErrorCode errorCode;
    private String message;

}
