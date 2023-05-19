package com.ssafy.enjoytrip.payment.exception;

import com.ssafy.enjoytrip.global.exception.ErrorCode;

public class PaymentException extends RuntimeException{

    private ErrorCode errorCode;
    private String msg;
}
