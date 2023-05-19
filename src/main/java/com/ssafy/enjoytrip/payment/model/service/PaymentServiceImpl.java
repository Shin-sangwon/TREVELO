package com.ssafy.enjoytrip.payment.model.service;

import com.ssafy.enjoytrip.global.exception.ErrorCode;
import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.payment.exception.PaymentException;
import com.ssafy.enjoytrip.payment.model.dto.request.MileageChargeRequestDto;
import com.ssafy.enjoytrip.payment.model.dto.response.MileageChargeResponseDto;
import com.ssafy.enjoytrip.payment.model.entity.Payment;
import com.ssafy.enjoytrip.payment.model.mapper.PaymentMapper;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;

    @Value("${toss.test.client-key}")
    private String testClientKey;
    @Value("${toss.test.secret-key}")
    private String testSecretKey;
    private final String tossUrl = "https://api.tosspayments.com/v1/payments/";

    @Override
    public MileageChargeResponseDto verifyMember(Member member,
        MileageChargeRequestDto mileageChargeRequestDto) {

        if (!member.getEmail()
                   .equals(mileageChargeRequestDto.getCustomerEmail()) || !member.getName()
                                                                                 .equals(
                                                                                     mileageChargeRequestDto.getCustomerName())) {
            throw new PaymentException(ErrorCode.PAYMENT_INFORMATION_MISMATCH,
                ErrorCode.PAYMENT_INFORMATION_MISMATCH.getMessage());
        }

        MileageChargeResponseDto mileageChargeResponseDto = MileageChargeResponseDto.fromRequestToCard(
            mileageChargeRequestDto);

        save(mileageChargeResponseDto.toEntity());

        return mileageChargeResponseDto;
    }

    @Transactional
    @Override
    public void save(Payment payment) {

        paymentMapper.save(payment);
    }

    @Transactional
    @Override
    public void verifyRequest(String paymentKey, String orderId, Long amount) {

        Payment payment = paymentMapper.findByOrderId(orderId)
                                       .orElseThrow(
                                           () -> new PaymentException(ErrorCode.ORDER_ID_NOT_FOUNT,
                                               ErrorCode.ORDER_ID_NOT_FOUNT.getMessage()));

        if (!Objects.equals(amount, payment.getAmount())) {
            throw new PaymentException(ErrorCode.PAYMENT_INFORMATION_MISMATCH,
                ErrorCode.PAYMENT_INFORMATION_MISMATCH.getMessage());
        }

        payment.mapPaymentKey(paymentKey);
        paymentMapper.updatePaymentKey(payment);


    }

    @Override
    public String approveRequestToPayments(String paymentKey, String orderId, Long amount) {
        WebClient webClient = WebClient.create();
        HttpHeaders httpHeaders = new HttpHeaders();
        String testSecretKeyWithColon = testSecretKey + ":";
        String encodedKey = new String(Base64.getEncoder()
                                             .encode(testSecretKeyWithColon.getBytes(
                                                 StandardCharsets.UTF_8)));

        httpHeaders.setBasicAuth(encodedKey);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderId", orderId);
        jsonObject.put("amount", amount);

        Mono<String> response = webClient.post()
                                         .uri(tossUrl + paymentKey)
                                         .headers(headers -> headers.putAll(httpHeaders))
                                         .body(BodyInserters.fromValue(jsonObject.toString()))
                                         .retrieve()
                                         .bodyToMono(String.class);

        return response.block(); // Blocking call, you might want to handle it differently in production
    }

    @Override
    public Payment findByOrderId(String orderId) {

        return paymentMapper.findByOrderId(orderId)
                            .orElseThrow(() -> new PaymentException(ErrorCode.ORDER_ID_NOT_FOUNT,
                                           ErrorCode.ORDER_ID_NOT_FOUNT.getMessage()));
    }


}
