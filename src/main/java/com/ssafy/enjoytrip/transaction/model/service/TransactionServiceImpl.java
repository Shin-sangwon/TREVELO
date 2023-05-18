package com.ssafy.enjoytrip.transaction.model.service;

import com.ssafy.enjoytrip.transaction.model.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService{

    private final TransactionMapper transactionMapper;
}
