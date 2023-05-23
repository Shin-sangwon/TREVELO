package com.ssafy.enjoytrip.payment.model.mapper;

import com.ssafy.enjoytrip.payment.model.entity.Payment;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

    void save(Payment payment);

    Optional<Payment> findByOrderId(String orderId);

    void updatePaymentKey(Payment payment);
}
