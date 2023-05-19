package com.ssafy.enjoytrip.payment.exception;

import com.ssafy.enjoytrip.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentException extends RuntimeException{

    private ErrorCode errorCode;
    private String msg;
}
