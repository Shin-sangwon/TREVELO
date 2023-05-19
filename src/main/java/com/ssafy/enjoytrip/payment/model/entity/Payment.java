package com.ssafy.enjoytrip.payment.model.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Alias("payment")
public class Payment {

    private Long id;
    private PayType payType;
    private Long amount;
    private String orderId;
    private String orderName;
    private String customerEmail;
    private String customerName;
    private String createdat;
    private String paymentKey;

    public void mapPaymentKey(String paymentKey) {
        this.paymentKey = paymentKey;
    }

}
