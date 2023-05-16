package com.ssafy.enjoytrip.reservation.model.dto.request;

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
public class ReservationDateSaveRequestDto {

    private Long roomId;
    private LocalDate date;

    public static ReservationDateSaveRequestDto of(Long roomId, LocalDate date) {
        return ReservationDateSaveRequestDto.builder()
                                            .roomId(roomId)
                                            .date(date)
                                            .build();
    }
}
