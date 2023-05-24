package com.ssafy.enjoytrip.reservation.model.entity;

import com.ssafy.enjoytrip.reservation.model.dto.response.ReservationResponseDto;
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
@Alias("reservation")
public class Reservation {

    private Long id;
    private Long customerId;
    private Long roomId;
    private Long totalPrice;
    private boolean isPaid;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;

    public static Reservation from(ReservationResponseDto reservationResponseDto) {
        return Reservation.builder()
                          .id(reservationResponseDto.getReservation_id())
                          .customerId(reservationResponseDto.getCustomerId())
                          .roomId(reservationResponseDto.getRoomId())
                          .totalPrice(reservationResponseDto.getTotalPrice())
                          .isPaid(reservationResponseDto.isPaid())
                          .checkInDate(reservationResponseDto.getCheckInDate())
                          .checkOutDate(reservationResponseDto.getCheckOutDate())
                          .createdat(reservationResponseDto.getCreatedat())
                          .updatedat(reservationResponseDto.getUpdatedat())
                          .build();
    }
}
