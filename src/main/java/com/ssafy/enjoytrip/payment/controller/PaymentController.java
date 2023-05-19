package com.ssafy.enjoytrip.payment.controller;

import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.member.model.service.MemberService;
import com.ssafy.enjoytrip.payment.model.dto.request.MileageChargeRequestDto;
import com.ssafy.enjoytrip.payment.model.dto.response.MileageChargeResponseDto;
import com.ssafy.enjoytrip.payment.model.entity.Payment;
import com.ssafy.enjoytrip.payment.model.service.PaymentService;
import com.ssafy.enjoytrip.transaction.model.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
@RestController
public class PaymentController {

    private final PaymentService paymentService;
    private final TransactionService transactionService;
    private final MemberService memberService;

    @PostMapping("/")
    public ResponseEntity<MileageChargeResponseDto> mileageChargeRequest(
        @AuthenticationPrincipal Member member,
        @RequestBody MileageChargeRequestDto mileageChargeRequestDto) {
        log.info("'{}'회원 마일리지 '{}' 원충전 요청 - POST", member.getLoginId(),
            mileageChargeRequestDto.getAmount());
        MileageChargeResponseDto mileageChargeResponseDto = paymentService.verifyMember(member,
            mileageChargeRequestDto);

        return ResponseEntity.ok()
                             .body(mileageChargeResponseDto);
    }

    @GetMapping("/success")
    public ResponseEntity<String> paymentSuccess(
                                                 @RequestParam String paymentKey,
                                                 @RequestParam String orderId,
                                                 @RequestParam Long amount) {

        log.info("토스에 webClient로 최종 승인 요청");
        paymentService.verifyRequest(paymentKey, orderId, amount);
        // webClient 요청 보내기
        String result = paymentService.approveRequestToPayments(paymentKey, orderId, amount);

        Payment payment = paymentService.findByOrderId(orderId);
        // 트랜잭션 테이블에 추가해주기

        // 회원 마일리지 올려주기
        Member member = memberService.findByLoginId(payment.getMemberId());
        memberService.updateMileage();

        return ResponseEntity.ok().body(result);
    }
}
