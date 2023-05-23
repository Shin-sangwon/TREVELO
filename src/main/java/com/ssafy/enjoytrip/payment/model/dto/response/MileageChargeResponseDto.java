package com.ssafy.enjoytrip.payment.model.dto.response;

import com.ssafy.enjoytrip.payment.model.dto.request.MileageChargeRequestDto;
import com.ssafy.enjoytrip.payment.model.entity.PayType;
import com.ssafy.enjoytrip.payment.model.entity.Payment;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MileageChargeResponseDto {

    private PayType payType;
    private Long amount;
    private Long memberId;
    private String orderId;
    private String orderName;
    private String customerEmail;
    private String customerName;
    private String successUrl;
    private String failUrl;

    public static MileageChargeResponseDto fromRequestToCard(
        MileageChargeRequestDto mileageChargeRequestDto) {
        return MileageChargeResponseDto.builder()
                                       .amount(mileageChargeRequestDto.getAmount())
                                       .memberId(mileageChargeRequestDto.getMemberId())
                                       .customerEmail(mileageChargeRequestDto.getCustomerEmail())
                                       .customerName(mileageChargeRequestDto.getCustomerName())
                                       .payType(PayType.CARD)
                                       .orderName("마일리지 충전")
                                       .orderId(UUID.randomUUID()
                                                    .toString())
                                       .successUrl("http://localhost/api/v1/payment/success")
                                       .failUrl("http://localhost/api/v1/payment/fail")
                                       .build();
    }

    public Payment toEntity() {
        return Payment.builder()
                      .memberId(this.memberId)
                      .amount(this.amount)
                      .customerName(this.customerName)
                      .customerEmail(this.customerEmail)
                      .orderId(this.orderId)
                      .orderName(this.orderName)
                      .payType(this.payType)
                      .build();
    }
}
