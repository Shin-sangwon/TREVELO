package com.ssafy.enjoytrip.reservation.model.entity;

import java.time.LocalDate;
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
@Alias("reservation_date")
public class ReservationDate {

    private Long id;
    private Long roomId;
    private Long reservationId;
    private LocalDate reservationDate;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;

}
