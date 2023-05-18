package com.ssafy.enjoytrip.transaction.model.entity;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Alias("transaction")
public class Transaction {

    private Long id;
    private Long memberId;
    private Long amount;
    private TransactionType transactionType;
    private String description;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;
}
