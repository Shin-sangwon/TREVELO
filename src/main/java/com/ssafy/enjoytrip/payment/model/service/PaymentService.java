package com.ssafy.enjoytrip.payment.model.service;

import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.payment.model.dto.request.MileageChargeRequestDto;
import com.ssafy.enjoytrip.payment.model.dto.response.MileageChargeResponseDto;
import com.ssafy.enjoytrip.payment.model.entity.Payment;

public interface PaymentService {

    MileageChargeResponseDto verifyMember(Member member, MileageChargeRequestDto mileageChargeRequestDto);

    void save(Payment payment);

    void verifyRequest(String paymentKey, String orderId, Long amount);

    String approveRequestToPayments(String paymentKey, String orderId, Long amount);

    Payment findByOrderId(String orderId);
}
