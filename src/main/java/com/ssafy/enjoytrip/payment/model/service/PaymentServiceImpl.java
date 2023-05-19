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

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;
    @Override
    public MileageChargeResponseDto verifyRequest(Member member,
        MileageChargeRequestDto mileageChargeRequestDto) {

        if(!member.getEmail().equals(mileageChargeRequestDto.getCustomerEmail()) || !member.getName().equals(mileageChargeRequestDto.getCustomerName())) {
            throw new PaymentException(ErrorCode.PAYMENT_INFORMATION_MISMATCH, ErrorCode.PAYMENT_INFORMATION_MISMATCH.getMessage());
        }

        MileageChargeResponseDto mileageChargeResponseDto = MileageChargeResponseDto.fromRequestToCard(mileageChargeRequestDto);

        save(mileageChargeResponseDto.toEntity());

        return mileageChargeResponseDto;
    }

    @Override
    public void save(Payment payment) {

        paymentMapper.save(payment);
    }


}
