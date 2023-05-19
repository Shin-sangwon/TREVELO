package com.ssafy.enjoytrip.transaction.model.service;

import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.payment.model.entity.Payment;
import com.ssafy.enjoytrip.transaction.model.dto.response.TransactionResponseDto;
import com.ssafy.enjoytrip.transaction.model.entity.Transaction;
import java.util.List;

public interface TransactionService {

    List<TransactionResponseDto> findAllByMemberId(Long id);

    void save(Transaction transaction);

    void savePayments(Payment payment, Member member);
}
