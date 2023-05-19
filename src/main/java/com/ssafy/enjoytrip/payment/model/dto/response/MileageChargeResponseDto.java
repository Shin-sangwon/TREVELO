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
    private String orderId;
    private String orderName;
    private String customerEmail;
    private String customerName;

    public static MileageChargeResponseDto fromRequestToCard(
        MileageChargeRequestDto mileageChargeRequestDto) {
        return MileageChargeResponseDto.builder()
                                       .amount(mileageChargeRequestDto.getAmount())
                                       .customerEmail(mileageChargeRequestDto.getCustomerEmail())
                                       .customerName(mileageChargeRequestDto.getCustomerName())
                                       .payType(PayType.CARD)
                                       .orderName("마일리지 충전")
                                       .orderId(UUID.randomUUID()
                                                    .toString())
                                       .build();
    }

    public Payment toEntity() {
        return Payment.builder()
                      .amount(this.amount)
                      .customerName(this.customerName)
                      .customerEmail(this.customerEmail)
                      .orderId(this.orderId)
                      .orderName(this.orderName)
                      .payType(this.payType)
                      .build();
    }
}
