package com.ssafy.enjoytrip.reservation.model.dto.request;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservationSaveRequestDto {

    private Long id;
    private Long customerId;
    private Long roomId;
    private Long totalPrice;
    private boolean isPaid;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

}
