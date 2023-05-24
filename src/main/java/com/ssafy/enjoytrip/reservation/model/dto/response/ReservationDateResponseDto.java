package com.ssafy.enjoytrip.reservation.model.dto.response;

import com.ssafy.enjoytrip.reservation.model.entity.ReservationDate;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationDateResponseDto {

    private LocalDate reservationDate;

    public static ReservationDateResponseDto from(ReservationDate entity) {
        return ReservationDateResponseDto.builder()
                                         .reservationDate(entity.getReservationDate())
                                         .build();
    }
}
