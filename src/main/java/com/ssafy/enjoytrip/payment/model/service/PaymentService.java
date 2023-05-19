package com.ssafy.enjoytrip.payment.model.service;

import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.payment.model.dto.request.MileageChargeRequestDto;
import com.ssafy.enjoytrip.payment.model.dto.response.MileageChargeResponseDto;
import com.ssafy.enjoytrip.payment.model.entity.Payment;

public interface PaymentService {

    MileageChargeResponseDto verifyRequest(Member member, MileageChargeRequestDto mileageChargeRequestDto);

    void save(Payment payment);
}
