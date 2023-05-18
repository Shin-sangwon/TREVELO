package com.ssafy.enjoytrip.transaction.model.service;

import com.ssafy.enjoytrip.transaction.model.dto.response.TransactionResponseDto;
import com.ssafy.enjoytrip.transaction.model.mapper.TransactionMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{

    private final TransactionMapper transactionMapper;

    @Override
    public List<TransactionResponseDto> findAllByMemberId(Long memberId) {
        return transactionMapper.findAllByMemberId(memberId);
    }
}
