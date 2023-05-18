package com.ssafy.enjoytrip.transaction.model.entity;

import com.ssafy.enjoytrip.reservation.model.dto.request.ReservationSaveRequestDto;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Getter
@Builder
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

    /*
    1. 예약할 때 저장하기 위한 용도, 따라서 TranscationType = ROOM_RESERVATION
     */
    public static Transaction from(ReservationSaveRequestDto reservationSaveRequestDto) {

        return Transaction.builder()
                          .memberId(reservationSaveRequestDto.getCustomerId())
                          .transactionType(TransactionType.ROOM_RESERVATION)
                          .description(TransactionType.ROOM_RESERVATION.getDescription())
                          .amount(getReservationFee(reservationSaveRequestDto.getTotalPrice()))
                          .build();
    }

    private static Long getReservationFee(Long amount) {
        return (long) (amount / 10.0) * -1;
    }
}
