package com.ssafy.enjoytrip.transaction.model.service;

import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.payment.model.entity.Payment;
import com.ssafy.enjoytrip.transaction.model.dto.response.TransactionResponseDto;
import com.ssafy.enjoytrip.transaction.model.entity.Transaction;
import com.ssafy.enjoytrip.transaction.model.entity.TransactionType;
import com.ssafy.enjoytrip.transaction.model.mapper.TransactionMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionMapper transactionMapper;

    @Transactional(readOnly = true)
    @Override
    public List<TransactionResponseDto> findAllByMemberId(Long memberId) {
        return transactionMapper.findAllByMemberId(memberId);
    }

    @Transactional
    @Override
    public void save(Transaction transaction) {
        transactionMapper.save(transaction);
    }

    @Override
    public void savePayments(Payment payment, Member member) {
        Transaction transaction = Transaction.builder()
                                             .memberId(member.getId())
                                             .amount(payment.getAmount())
                                             .transactionType(TransactionType.MILEAGE_DEPOSIT)
                                             .description(payment.getOrderName())
                                             .build();

        save(transaction);
    }
}
