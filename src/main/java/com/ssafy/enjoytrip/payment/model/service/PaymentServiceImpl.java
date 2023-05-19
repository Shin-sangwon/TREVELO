package com.ssafy.enjoytrip.payment.model.service;

import com.ssafy.enjoytrip.global.exception.ErrorCode;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.payment.exception.PaymentException;
import com.ssafy.enjoytrip.payment.model.dto.request.MileageChargeRequestDto;
import com.ssafy.enjoytrip.payment.model.dto.response.MileageChargeResponseDto;
import com.ssafy.enjoytrip.payment.model.entity.Payment;
import com.ssafy.enjoytrip.payment.model.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;
    @Override
    public MileageChargeResponseDto verifyMember(Member member,
        MileageChargeRequestDto mileageChargeRequestDto) {

        if(!member.getEmail().equals(mileageChargeRequestDto.getCustomerEmail()) || !member.getName().equals(mileageChargeRequestDto.getCustomerName())) {
            throw new PaymentException(ErrorCode.PAYMENT_INFORMATION_MISMATCH, ErrorCode.PAYMENT_INFORMATION_MISMATCH.getMessage());
        }

        MileageChargeResponseDto mileageChargeResponseDto = MileageChargeResponseDto.fromRequestToCard(mileageChargeRequestDto);

        save(mileageChargeResponseDto.toEntity());

        return mileageChargeResponseDto;
    }

    @Transactional
    @Override
    public void save(Payment payment) {

        paymentMapper.save(payment);
    }

    @Override
    public void verifyRequest(String paymentKey, String orderId, Long amount) {

        Payment payment = paymentMapper.findByOrderId(orderId)
            .orElseThrow(() -> new PaymentException(ErrorCode.ORDER_ID_NOT_FOUNT, ErrorCode.ORDER_ID_NOT_FOUNT.getMessage()));

        payment.mapPaymentKey(paymentKey);
        paymentMapper.updatePaymentKey(payment);


    }


}
