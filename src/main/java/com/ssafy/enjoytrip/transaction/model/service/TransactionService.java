package com.ssafy.enjoytrip.transaction.model.service;

import com.ssafy.enjoytrip.transaction.model.dto.response.TransactionResponseDto;
import com.ssafy.enjoytrip.transaction.model.entity.Transaction;
import java.util.List;

public interface TransactionService {

    List<TransactionResponseDto> findAllByMemberId(Long id);

    void save(Transaction transaction);
}
