package com.ssafy.enjoytrip.transaction.controller;

import com.ssafy.enjoytrip.member.model.entity.Member;
import com.ssafy.enjoytrip.transaction.model.dto.response.TransactionResponseDto;
import com.ssafy.enjoytrip.transaction.model.service.TransactionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/transaction")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/")
    public ResponseEntity<List<TransactionResponseDto>> showTransactionList(@AuthenticationPrincipal Member member) {
        log.info("showTransactionList - GET : '{}' 요청", member.getLoginId());

        return ResponseEntity.ok().body(transactionService.findAllByMemberId(member.getId()));
    }
}
