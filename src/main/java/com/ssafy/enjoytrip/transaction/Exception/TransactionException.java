package com.ssafy.enjoytrip.transaction.Exception;

import com.ssafy.enjoytrip.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransactionException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;

}
