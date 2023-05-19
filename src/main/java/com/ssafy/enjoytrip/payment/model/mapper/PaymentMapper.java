package com.ssafy.enjoytrip.payment.model.mapper;

import com.ssafy.enjoytrip.payment.model.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

    void save(Payment payment);
}
