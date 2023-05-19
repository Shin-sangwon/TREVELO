package com.ssafy.enjoytrip.payment.model.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MileageChargeRequestDto {

    private Long amount;
    private Long memberId;
    private String customerEmail;
    private String customerName;
}
