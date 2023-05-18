package com.ssafy.enjoytrip.transaction.model.service;

import com.ssafy.enjoytrip.transaction.model.dto.response.TransactionResponseDto;
import java.util.List;

public interface TransactionService {

    List<TransactionResponseDto> findAllByMemberId(Long id);
}
