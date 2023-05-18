package com.ssafy.enjoytrip.transaction.model.dto.response;

import com.ssafy.enjoytrip.transaction.model.entity.TransactionType;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionResponseDto {

    private Long id;
    private Long memberId;
    private Long amount;
    private TransactionType transactionType;
    private String description;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;
}
